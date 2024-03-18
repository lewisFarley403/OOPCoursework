package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CyclingPortalImpl implements MiniCyclingPortal{ //take out abstract later, change mini to regular when done with mini
    RMS rms;
    List<Race> races;

    int nextStageID; // ensure each stageID unique across whole application
    public CyclingPortalImpl(){
        this.rms = new RMS();
        this.races = new ArrayList<>();
        this.nextStageID = 0;
    }
    // Race methods

    private void performRiderIDChecks(int riderID)throws IDNotRecognisedException{
        if(this.rms.hasRiderRegistered(riderID)){
            throw new IDNotRecognisedException("This rider isnt recognised in the system");
        }

    }
    private Race getRaceByID(int raceId) throws IDNotRecognisedException{
        for (Race race:this.races){
            if(race.getId() == raceId){
                return race;
            }
        }
        throw new IDNotRecognisedException("Race ID not recognised");
    }
    @Override
    public int[] getRaceIds() {
        return races.stream().mapToInt(Race::getId).toArray();
    }

    @Override
    public int createRace(String name, String description) throws IllegalNameException,InvalidNameException {
        Race race = new Race(races.size() + 1, name, description);
        races.add(race);
        return race.getId();
    }
    @Override
    public String viewRaceDetails(int raceId) throws IDNotRecognisedException{
        Race raceToDetail = this.getRaceByID(raceId);

        return "Race ID : "+raceToDetail.getId()+"Race Name : "+raceToDetail.getName() + "Race Description : "+raceToDetail.getDescription();
    }

    @Override
    public int getNumberOfStages(int raceId) throws IDNotRecognisedException{
        Race race = this.getRaceByID(raceId);
        return race.getStages().length;
    }
    @Override
    public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type)
            throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException{
        Race race = this.getRaceByID(raceId);

        switch (type){
            case StageType.FLAT:
                race.addStage(this.nextStageID++,new FlatStage(type,this.rms.getRiderIds(),description,length,startTime));
                break;

            case StageType.MEDIUM_MOUNTAIN:
                race.addStage(this.nextStageID++,new MediumMountainStage(type,this.rms.getRiderIds(),description,length,startTime));
                break;

            case StageType.HIGH_MOUNTAIN:
                race.addStage(this.nextStageID++,new HighMountainStage(type,this.rms.getRiderIds(),description,length,startTime));
                break;
        }


        return this.nextStageID-1;
    }
    @Override
    public void removeRaceById(int raceID) throws IDNotRecognisedException {
        Race raceToRemove = this.getRaceByID(raceID);
        races.remove(raceToRemove);
    }
    public void removeRaceByName(String name){

    }
    private Race getRaceWithStage(int id) throws IDNotRecognisedException{
        for (Race race : this.races){

            Stage s = race.getStageByID(id);
            if(s!=null){
                return race;
            }
        }
        throw new IDNotRecognisedException("This stage ID isn't registered in the system");
    }
private Stage getStageByID(int id) throws IDNotRecognisedException{
        return this.getRaceWithStage(id).getStageByID(id);

}
    public double getStageLength(int stageId) throws IDNotRecognisedException{
        return this.getStageByID(stageId).getLength();
    }
    public void removeStageById(int stageId) throws IDNotRecognisedException{
        Race r = this.getRaceWithStage(stageId);
        r.removeStage(stageId);
    }


    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        Race race = this.getRaceByID(raceId);
        return race.getStages();
    }

    // RMS methods

    @Override
    public int createTeam(String name, String description) throws IllegalNameException,InvalidNameException{
        return this.rms.createTeam(name,description);
    }
    @Override
    public void removeTeam(int teamID) throws IDNotRecognisedException{
        this.rms.removeTeam(teamID);
    }
    @Override
    public int[] getTeams(){
        return this.rms.getTeams();
    }
    @Override
    public int[] getTeamRiders(int teamID)throws IDNotRecognisedException{
        return this.rms.getTeamRiders(teamID) ;
    }

    public int createRider(int teamID,String name, int yearOfBirth) throws IDNotRecognisedException{
        //test this because of Integer to let things be null

        return this.rms.createRider(teamID,name,yearOfBirth);


    }
    @Override
    public void removeRider(int riderID) throws IDNotRecognisedException{
        this.rms.removeRider(riderID);
    }

    private static StageWithCheckpoints stageToStageWithCheckpoint(Stage s) throws InvalidStageTypeException{

        StageWithCheckpoints stage;
        if (s instanceof StageWithCheckpoints){
            stage = (StageWithCheckpoints) s; // this ensures the type cast is correct
        }
        else{
            throw new InvalidStageTypeException("This stage cannot have checkpoints");
        }
        return stage;

    }
    @Override
    public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
                                   Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException{

        Stage s = this.getStageByID(stageId);
        StageWithCheckpoints stage = stageToStageWithCheckpoint(s);
        CC cc = new CC(location,type,averageGradient,length,this.rms.getRiderIds());
        stage.addCategorizedClimbToStage(location,type,averageGradient,length,this.nextStageID);
        this.nextStageID++;
        return this.nextStageID-1;


    }
    public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,InvalidLocationException, InvalidStageStateException, InvalidStageTypeException{
        Stage s = this.getStageByID(stageId);
        StageWithCheckpoints stage = stageToStageWithCheckpoint(s);


        stage.addIntermediateSprintToStage(location,this.nextStageID);
        this.nextStageID++;
        return this.nextStageID-1;

    }

    public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException{
        for(Race race : this.races){
            for(int stageID : race.getStages()){
                Stage stage = this.getStageByID(stageID);
                if(stage.hasCheckpoint(checkpointId)){
                    StageWithCheckpoints stage1 = (StageWithCheckpoints) stage; // we know this is safe, as if stage wasnt a stage with checkpoints, stage.hasCheckpoints would always be false so this code is unreachable
                    stage1.removeCheckpoint(checkpointId);
                    return;
                }
            }
        }
        throw new IDNotRecognisedException("This checkpoint ID doesnt exist");
    }

    public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException{
        Stage s = this.getStageByID(stageId);
        s.concludePreparation();
    }
    public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException{
        Stage stage = this.getStageByID(stageId);
        return stage.getCheckpoints();
    }
    public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpointTimes)
            throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException, InvalidStageStateException{
        Stage stage = this.getStageByID(stageId);

        stage.registerRiderResults(riderId,checkpointTimes);

    }
    public LocalTime [] getRiderResultsInStage(int stageID,int riderID) throws IDNotRecognisedException{
        Stage stage = this.getStageByID(stageID);
        return stage.getRiderResults(riderID);

    }

    public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId)
            throws IDNotRecognisedException{
        return null;
    }

    public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException{
        Stage stage = this.getStageByID(stageId);
        //check rider in rms
        this.performRiderIDChecks(riderId);
        stage.removeRider(riderId);
    }

    public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException{
        Stage stage = this.getStageByID(stageId);
        return stage.getRaceOrder();
    }
    public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException{
        Stage stage = this.getStageByID(stageId);
        return stage.getRankedAdjustedElapsedTimes();
    }

    @Override
    public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
        Stage stage = this.getStageByID(stageId);

        return stage.getSprinterPointsArray();

    }

    @Override
    public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
        Stage stage = this.getStageByID(stageId);
        if(!(stage instanceof StageWithCheckpoints)){
            return new int[0];
        }
        StageWithCheckpoints s = (StageWithCheckpoints) stage;
        return s.getMountainPointsArray();
    }

    @Override
    public void eraseCyclingPortal() {
        this.races.clear();
        this.nextStageID=0;
        this.rms = new RMS();


    }

    @Override
    public void saveCyclingPortal(String filename) throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub

    }


}

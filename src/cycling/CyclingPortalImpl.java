package cycling;

import java.util.ArrayList;
import java.util.List;

public abstract class CyclingPortalImpl implements CyclingPortal{ //take out abstract later
    RMS rms;
    List<Race> races;
    public CyclingPortalImpl(){
        this.rms = new RMS();
        this.races = new ArrayList<>();
    }
    // Race methods


    public int createRace(String name, String description) throws IllegalNameException,InvalidNameException {
        Race race = new Race(races.size() + 1, name, description);
        races.add(race);
        return race.getId();
    }

    public void removeRaceById(int raceID) throws IDNotRecognisedException {
        Race raceToRemove = null;
        for (Race race : races) {
            if (race.getId() == raceID) {
                raceToRemove = race;
                break;
            }
        }
        if (raceToRemove == null) {
            throw new IDNotRecognisedException("Race ID not recognised");
        }
        races.remove(raceToRemove);
    }

    @Override
    public int[] getRaceIds() {
        return races.stream().mapToInt(Race::getId).toArray();
    }

    @Override
    public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
        for (Race race : races) {
            if (race.getId() == raceId) {
                return race.getStages().stream().mapToInt(Stage::getRaceIds).toArray();
            }
        }
        throw new IDNotRecognisedException("Race ID not recognised");
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
        //more to this function, must remove all of this riders time and stuff

    }

}

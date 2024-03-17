package cycling;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.time.LocalDateTime;


public abstract class StageWithCheckpoints extends Stage {
    HashMap<Integer,Checkpoint> checkpoints;
    int [] pointDistribution;
    public float [] pointsForStage; // points for intermediate sprint

    public int [] riderIDs;
    private int checkpointCounter;
    //    public Stage(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){
    public StageWithCheckpoints(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){
        super(stageType,riders,description,length, startTime); // Call the constructor of the super class

        this.checkpoints = new HashMap<>();

         this.checkpointCounter= 0;

    }

    public void addCategorizedClimbToStage(Double location, CheckpointType type, Double averageGradient,
                                   Double length,int checkpointCounter) throws InvalidLocationException, InvalidStageStateException{ //throw IDNotRecognisedException from race management, throw InvalidStageTypeException StageWithoutCheckpoint
        this.performStageChecks(location);
        Checkpoint checkpoint = new CC(location,type,averageGradient,length,this.getRiderIDs());


        this.addCheckpointToStages(checkpoint,checkpointCounter);

    }
    private void performStageChecks(double location) throws InvalidStageStateException,InvalidLocationException{
        if (this.waitingForResults){
            throw new InvalidStageStateException("this stage is currently awaiting results");
        }
        if (location > this.length){ //this might need to change "If the location is out of bounds of stage length"
            throw new InvalidLocationException("Location out of bound of stage length");
        }

    }
    public void addIntermediateSprintToStage(double location,int checkpointCounter) throws InvalidLocationException, InvalidStageStateException{
        this.performStageChecks(location);
        Checkpoint cp = new IS(location,CheckpointType.SPRINT,this.getRiderIDs());
        this.addCheckpointToStages(cp,checkpointCounter);
    }
    private void addCheckpointToStages(Checkpoint cp,int checkpointCounter){
        this.checkpoints.put(checkpointCounter,cp);

    }

    public HashMap<Integer,Float> removeCheckpoint(int ID) throws InvalidStageStateException{ // Remove the checkpoint with the given ID from the map
        // throw IDNotRecognisedException in race management
        if(this.waitingForResults){
            throw new InvalidStageStateException("this stage is currently waiting for results and can't be modified");
        }
        Checkpoint cp = this.checkpoints.get(ID);
        this.checkpoints.remove(ID);
        return cp.getResults();
    }
    private HashMap<Integer,Float> createEmptyHashmap(){
        HashMap<Integer,Float> empty = new HashMap<>();
        for (int rider:this.riderIDs){
            empty.put(rider,0.0f);
        }
        return empty;
    }
    public HashMap<Integer, Float> getMountainPoints() { // Create a new HashMap to store the points for mountain run
        HashMap<Integer, Float> mountainPoints = this.createEmptyHashmap();

        for (Map.Entry<Integer, Checkpoint> entry : this.checkpoints.entrySet()) {
            if (entry.getValue().getType() != CheckpointType.SPRINT) {
                //entry is the stage
//                mountainPoints.put(entry.getKey(), entry.getValue().getResults());
                for(int riderID:this.riderIDs){
                    float origional = mountainPoints.get(riderID);
                    mountainPoints.put(riderID,origional+1.0f);
                }

            }
        }
        return mountainPoints;
    }

    public float GetMountainPointsByID(int checkpointID){
        HashMap<Integer, Float> mountainPoints = getMountainPoints();
        if(mountainPoints.containsKey(checkpointID)) {
            return mountainPoints.get(checkpointID);
        }
        else{
            return 0;
        }
    }
    public void registerRiderResults(int riderID, LocalTime[] checkpointTimes) throws InvalidCheckpointTimesException,DuplicatedResultException,IDNotRecognisedException,InvalidStageStateException {
        if (checkpointTimes.length!= this.checkpoints.size()+2){
            throw new InvalidCheckpointTimesException("checkpointTimes has length "+checkpointTimes.length+ "but should have "+this.checkpoints.size()+2);
        }
        if (!this.waitingForResults){
            throw new InvalidStageStateException("This stage isn't waiting for results");
        }

        Duration timeForCheckpoint;
        for (int i=0;i< checkpointTimes.length-1;i++){
            timeForCheckpoint = Duration.between(checkpointTimes[i+1],checkpointTimes[i]);
            Checkpoint checkpoint = this.checkpoints.get(i);
            checkpoint.registerResult(riderID,timeForCheckpoint);
        }
        if(!this.results.containsKey(riderID)){ // translates to this.results.containsKey(riderID) == false
            throw new IDNotRecognisedException("This rider is not registered in the system");
        }
        Duration time = this.results.get(riderID); //Float here since it can be null
        if (time!=null){
            throw new DuplicatedResultException ("This rider already has a result registered to it");
        }
        this.results.put(riderID, Duration.between(checkpointTimes[0], checkpointTimes[checkpointTimes.length - 1]));
        //total time for the rider for the stage as a whole
    }
    public void removeRider(int ID){
        this.results.remove(ID);
        for (Checkpoint checkpoint:this.checkpoints.values()){
            checkpoint.removeRider(ID);
        }

    }
    public void addRider(int ID){
        this.results.put(ID,null);
        for (Checkpoint cp : this.checkpoints.values()){
            cp.addRider(ID);
        }
    }
    public HashMap<Integer,Duration>getGCPoints(){
        return this.results;// this just needs total time for each stage
    }

    public int [] getRiderIDs(){
        Set<Integer> keys = this.results.keySet();
        Integer[] keysArray = keys.toArray(new Integer[0]);
        int[] intArr = new int [keysArray.length];
        for(int i =0;i<intArr.length;i++){
            intArr[i] = keysArray[i];
        }
        return intArr;
    }

    public int[] getRaceOrder(){
        //returns array of riderIDs sorted by result
        //can be improved by swapping bubble sort for a better impl
        int [] keysArray = this.getRiderIDs();

        Duration [] times = new Duration[keysArray.length];
        for (int i =0;i<keysArray.length;i++){
            times[i] = this.results.get(keysArray[i]);
        }

        boolean change = true;
        while(change){
            change = false;
            for(int i = 0;i<times.length-1;i++){
//                if(times[i]<times[i+1]){
                if(times[i].compareTo(times[i+1])<0){
                    swap(i,i+1,keysArray,times);
                    change = true;
                }
            }
        }
        int [] ids = new int [keysArray.length];
        for(int i = 0;i<ids.length;i++){
            ids[i]=(int)keysArray[i];
        }
        return ids;

    }
    public static void swap(int i, int j, int[] keysArray, Duration[] times) {
        // Swap elements in keysArray
        int tempKey = keysArray[i];
        keysArray[i] = keysArray[j];
        keysArray[j] = tempKey;

        // Swap elements in times array
        Duration tempTime = times[i];
        times[i] = times[j];
        times[j] = tempTime;
    }
    public HashMap<Integer,Float>getSprinterPoints(){

        HashMap<Integer,Float> sprinterPoints = new HashMap<>();
        for(int riderID:this.getRiderIDs()) {
            sprinterPoints.put(riderID,0.0f);
        }//writes 0s to all riderIDs


        for (Checkpoint cp:this.checkpoints.values()){

            HashMap<Integer,Float> stagesPoints = cp.getSprinterPoints();

            for(int riderID:this.getRiderIDs()){
                Float ridersSprinterPoint = sprinterPoints.get(riderID);
                Float newRidersSprinterPoint = ridersSprinterPoint + stagesPoints.get(riderID);
                sprinterPoints.replace(riderID,newRidersSprinterPoint);
            }
        }
        int [] raceOrder = this.getRaceOrder();
        for (int i =0;i<raceOrder.length;i++){
            int id = raceOrder[i];
            if(i<=14){
                float oldPoints = sprinterPoints.get(id);
                sprinterPoints.replace(id,oldPoints+this.pointDistribution[i]);
            }
        }
        return sprinterPoints;
    }
    boolean hasCheckpoint(int id){
        return this.checkpoints.containsKey(id);


    }
}
class FlatStage extends StageWithCheckpoints{

    public FlatStage(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){

        super(stageType,riders,description,length,startTime);
        this.pointDistribution = new int[] {50,30,20,18,16,14,12,10,8,7,6,5,4,3,2};
    }
}

class MediumMountainStage extends StageWithCheckpoints{
    public MediumMountainStage(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){
        super(stageType,riders,description,length,startTime);
        this.pointDistribution = new int[] {30,25,22,19,17,15,13,11,9,7,6,5,4,3,2};
    }
}
class HighMountainStage extends StageWithCheckpoints{
    public HighMountainStage(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){
        super(stageType,riders,description,length,startTime);
        this.pointDistribution = new int[] {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};

    }
    int [] getCheckpoints(){
        int [] ids = new int [this.checkpoints.size()];
        int index = 0;
        for (int id : this.checkpoints.keySet()){
            ids[index] = id;
            index++;
        }
        return ids;
    }

}


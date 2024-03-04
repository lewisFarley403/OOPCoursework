package cycling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StageWithCheckpoints extends Stage {
    HashMap<Integer,Checkpoint> checkpoints;
    public float [] pointsForStage; // points for intermediate sprint

    public int [] riderIDs;
    private int checkpointCounter;
    public StageWithCheckpoints(int[] riders,StageType stageType){
        super(stageType, riders); // Call the constructor of the super class

        this.checkpoints = new HashMap<>();

         this.checkpointCounter= 0;

    }

    public int addCategorizedClimbToStage(Double location, CheckpointType type, Double averageGradient,
                                   Double length){
//        Checkpoint checkpoint = new Checkpoint(location,type,averageGradient,length,this.riderIDs); // change this when checkpoint impl

        Checkpoint checkpoint = new CC(location,type,averageGradient,length,this.riderIDs);

        this.checkpointCounter++;
        return this.checkpointCounter-1;

    }

    public HashMap<Integer,Float> removeCheckpoint(int ID){ // Remove the checkpoint with the given ID from the map
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
    public void registerRiderResults(int riderID,float[] checkpointTimes) throws InvalidCheckpointTimesException,DuplicatedResultException,IDNotRecognisedException,InvalidStageStateException {
        if (checkpointTimes.length!= this.checkpoints.size()+2){
            throw new InvalidCheckpointTimesException("checkpointTimes has length "+checkpointTimes.length+ "but should have "+this.checkpoints.size()+2);
        }
        if (!this.waitingForResults){
            throw new InvalidStageStateException("This stage isn't waiting for results");
        }

        float timeForCheckpoint;
        for (int i=0;i< checkpointTimes.length-1;i++){
            timeForCheckpoint = checkpointTimes[i+1]-checkpointTimes[i];
            Checkpoint checkpoint = this.checkpoints.get(i);
            checkpoint.registerResult(riderID,timeForCheckpoint);
        }
        if(!this.results.containsKey(riderID)){ // translates to this.results.containsKey(riderID) == false
            throw new IDNotRecognisedException("This rider is not registered in the system");
        }
        Float time = this.results.get(riderID); //Float here since it can be null
        if (time!=null){
            throw new DuplicatedResultException ("This rider already has a result registered to it");
        }
        this.results.put(riderID,checkpointTimes[checkpointTimes.length-1]-checkpointTimes[0]);  //total time for the rider for the stage as a whole
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
    public HashMap<Integer,Float>getGCPoints(){
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
        return sprinterPoints;
    }
}



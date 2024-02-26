package cycling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StageWithCheckpoints {
    HashMap<Integer,Checkpoint> checkpoints;
    public float [] pointsForStage; // points for intermediate sprint

    public int [] riderIDs;
    private int checkpointCounter;
    public StageWithCheckpoints(int[] riders){
        this.riderIDs = riders;
        this.checkpoints = new HashMap<>();

         this.checkpointCounter= 0;

    }

    public int addCategorizedClimbToStage(Double location, CheckpointType type, Double averageGradient,
                                   Double length){
        Checkpoint checkpoint = new Checkpoint(location,type,averageGradient,length,this.riderIDs); // change this when checkpoint impl
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
}
//https://code-with-me.global.jetbrains.com/ufoZSohKy_Ln1AWiD3mUJQ#p=IU&fp=BFED81890D9055F0B9FD4695954C8BF5074EAC961FF36C83D8F10F932913442D&newUi=true
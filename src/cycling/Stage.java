package cycling;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Stage {
    private StageType stageType;
    public boolean waitingForResults;
    public int[] riders;
    public HashMap<Integer, Float> results; //riderID as keys, times as values

    public Stage(StageType stageType,int[] riders){
        this.waitingForResults=true;
        this.stageType = stageType;
        this.riders = riders;
        this.results = new HashMap<>();
        for(int riderID:riders){
            //add riderID,0 key pair to this.
            this.addRider(riderID);
        }
    }
    public void toggleWaitingForResults(){
        this.waitingForResults = !this.waitingForResults;
    }
    public abstract void removeRider(int ID);//abstract needs to be implemented
    public abstract void addRider(int ID); //abstract, needs to be...
    public abstract HashMap<Integer,Float>getGCPoints();
    public abstract HashMap<Integer,Float>getSprinterPoints();
    public float getGCPointsByID(int ID){
        HashMap<Integer,Float> totalGCs= this.getGCPoints();
        return totalGCs.get(ID); //catch errors here
    }
    public float getSprinterPointsByID(int ID){
        HashMap<Integer,Float> totalSprinter = this.getSprinterPoints();
        return totalSprinter.get(ID);

    }
    public abstract void registerRiderResults(int riderID,float[] checkpointTimes) throws InvalidCheckpointTimesException,DuplicatedResultException,IDNotRecognisedException,InvalidStageStateException;
}

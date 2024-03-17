package cycling;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Stage {
    private StageType stageType;
    public boolean waitingForResults;
    public int[] riders;
    public HashMap<Integer, Duration> results; //riderID as keys, times as values
    double length;
    public Stage(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){
        this.waitingForResults=false;
        this.stageType = stageType;
        this.riders = riders;
        this.results = new HashMap<>();
        this.length = length;
        for(int riderID:riders){
            //add riderID,0 key pair to this.
            this.addRider(riderID);
        }
    }
    public void concludePreparation() throws InvalidStageStateException{
        if (this.waitingForResults){
            throw new InvalidStageStateException("This stage's preparation is already concluded");
        }
        this.waitingForResults = true;
    }
    public abstract void removeRider(int ID);//abstract needs to be implemented
    public abstract void addRider(int ID); //abstract, needs to be...
    public abstract HashMap<Integer,Duration>getGCPoints();
    public abstract HashMap<Integer,Float>getSprinterPoints();
    public Duration getGCPointsByID(int ID){
        HashMap<Integer,Duration> totalGCs= this.getGCPoints();
        return totalGCs.get(ID); //catch errors here
    }
    public float getSprinterPointsByID(int ID){
        HashMap<Integer,Float> totalSprinter = this.getSprinterPoints();
        return totalSprinter.get(ID);

    }
    double getLength (){
        return this.length;
    }
    boolean hasCheckpoint(int id){
        return false;
    }
    int [] getCheckpoints(){
        int [] x = {};
        return x;
    }
    public abstract void registerRiderResults(int riderID, LocalTime[] checkpointTimes) throws InvalidCheckpointTimesException,DuplicatedResultException,IDNotRecognisedException,InvalidStageStateException;
}

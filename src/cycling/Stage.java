package cycling;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a stage in a cycling race.
 * This class is abstract and should be extended by specific types of stages.
 */
public abstract class Stage {
    /**
     * The type of the stage.
     */
    private StageType stageType;
    /**
     * Indicates whether the stage is waiting for results.
     */
    public boolean waitingForResults;
    /**
     * The riders in the stage.
     */
    public int[] riders;
    /**
     * The results of the stage, with rider IDs as keys and times as values.
     */
    public HashMap<Integer, Duration> results; //riderID as keys, times as values
    /**
     * The length of the stage.
     */
    double length;
    /**
     * The start time of the stage.
     */
    LocalDateTime startTime;
    /**
     * Constructs a new Stage.
     *
     * @param stageType the type of the stage
     * @param riders the riders in the stage
     * @param description the description of the stage
     * @param length the length of the stage
     * @param startTime the start time of the stage
     */
    public Stage(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){
        this.waitingForResults=false;
        this.stageType = stageType;
        this.riders = riders;
        this.results = new HashMap<>();
        this.length = length;
        this.startTime = startTime;
//        for(int riderID:riders){
//            //add riderID,0 key pair to this.
//            this.addRider(riderID);
//        }
    }
    /**
     * Concludes the preparation for the stage.
     *
     * @throws InvalidStageStateException if the stage's preparation is already concluded
     */
    public void concludePreparation() throws InvalidStageStateException{
        if (this.waitingForResults){
            throw new InvalidStageStateException("This stage's preparation is already concluded");
        }
        this.waitingForResults = true;
    }
    /**
     * Removes a rider from the stage.
     *
     * @param ID the ID of the rider to remove
     */
    public abstract void removeRider(int ID);//abstract needs to be implemented
    /**
     * Adds a rider to the stage.
     *
     * @param ID the ID of the rider to add
     */
    public abstract void addRider(int ID); //abstract, needs to be...
    /**
     * Gets the general classification points for the stage.
     *
     * @return the general classification points for the stage
     */
    public abstract HashMap<Integer,Duration>getGCPoints();
    /**
     * Gets the sprinter points for the stage.
     *
     * @return the sprinter points for the stage
     */
    public abstract HashMap<Integer,Float> getSprinterPointsMap();
    /**
     * Gets the sprinter points for the stage as an array.
     *
     * @return the sprinter points for the stage as an array
     */
    public abstract int [] getSprinterPointsArray();
    /**
     * Gets the general classification points for a rider in the stage.
     *
     * @param ID the ID of the rider
     * @return the general classification points for the rider
     */
    public Duration getGCPointsByID(int ID){
        HashMap<Integer,Duration> totalGCs= this.getGCPoints();
        return totalGCs.get(ID); //catch errors here
    }

    /**
     * Gets the sprinter points for a rider in the stage.
     *
     * @param ID the ID of the rider
     * @return the sprinter points for the rider
     */
    public float getSprinterPointsByID(int ID){
        HashMap<Integer,Float> totalSprinter = this.getSprinterPointsMap();
        return totalSprinter.get(ID);

    }
    /**
     * Gets the length of the stage.
     *
     * @return the length of the stage
     */
    double getLength (){
        return this.length;
    }
    /**
     * Checks if the stage has a checkpoint.
     *
     * @param id the ID of the checkpoint
     * @return true if the stage has the checkpoint, false otherwise
     */
    boolean hasCheckpoint(int id){
        return false;
    }

    /**
     * Gets the checkpoints in the stage.
     *
     * @return the checkpoints in the stage
     */
    int [] getCheckpoints(){
        int [] x = {};
        return x;
    }
    /**
     * Gets the results of a rider in the stage.
     *
     * @param riderID the ID of the rider
     * @return the results of the rider
     * @throws IDNotRecognisedException if the rider ID is not recognised
     */
    public abstract LocalTime [] getRiderResults(int riderID) throws IDNotRecognisedException;
    /**
     * Registers the results of a rider in the stage.
     *
     * @param riderID the ID of the rider
     * @param checkpointTimes the times of the rider at the checkpoints
     * @throws InvalidCheckpointTimesException if the checkpoint times are invalid
     * @throws DuplicatedResultException if the result of the rider has already been registered
     * @throws IDNotRecognisedException if the rider ID is not recognised
     * @throws InvalidStageStateException if the stage state is invalid
     */
    public abstract void registerRiderResults(int riderID, LocalTime[] checkpointTimes) throws InvalidCheckpointTimesException,DuplicatedResultException,IDNotRecognisedException,InvalidStageStateException;
    /**
     * Gets the adjusted elapsed time of a rider in the stage.
     *
     * @param riderID the ID of the rider
     * @return the adjusted elapsed time of the rider
     */
    public abstract LocalTime getRiderAdjustedElapsedTimeInStage(int riderID);

    /**
     * Gets the race order in the stage.
     *
     * @return the race order
     */
    public abstract int [] getRaceOrder();

    /**
     * Gets the ranked adjusted elapsed times in the stage.
     *
     * @return the ranked adjusted elapsed times
     */
    public abstract LocalTime[] getRankedAdjustedElapsedTimes();
    /**
     * Swaps two riders in the order.
     *
     * @param i the index of the first rider
     * @param j the index of the second rider
     * @param keysArray the array of rider IDs
     * @param times the array of rider times
     */
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
}


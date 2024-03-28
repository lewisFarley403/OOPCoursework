package cycling;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;


/**
 * Represents a checkpoint in a cycling race.
 * This class is abstract and should be extended by specific types of checkpoints.
 */
public abstract class Checkpoint {

    /**
     * Distribution of points for riders at this checkpoint.
     */
    public int [] pointDistribution;

    /**
     * Location of the checkpoint.
     */
    Double location;

    /**
     * Type of the checkpoint.
     */
    CheckpointType type;

    /**
     * Average gradient of to checkpoint..
     */
    Double averageGradient;

    /**
     * Length of the course to the checkpoint.
     */
    Double length;

    /**
     * Results of the riders at this checkpoint.
     */
    HashMap<Integer,Duration> results;

    /**
     * Creates a new checkpoint.
     * @param location Location of the checkpoint.
     * @param type Type of the checkpoint.
     * @param riders Array of rider IDs.
     */
    public Checkpoint(Double location, CheckpointType type,int[] riders){
        this.location = location;
        this.type = type;
        this.averageGradient = averageGradient;
        this.length = length;
        this.results = new HashMap<>();
        this.pointDistribution = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        //creates empty results dict
//        for(int riderID:riders){
//            this.addRider(riderID);
//
//        }
    }

    /**
     * Removes a rider from the checkpoint.
     * @param ID ID of the rider to remove.
     */
    public void removeRider(int ID){
        this.results.remove(ID);
    }

    /**
     * Adds a rider to the checkpoint.
     * @param riderID ID of the rider to add.
     */
    public void addRider(int riderID){
        this.results.put(riderID,null);
    }//null implies that the rider has not had a result registered yet

    /**
     * Returns the results of the riders at this checkpoint.
     * @return Results of the riders at this checkpoint.
     */
    HashMap<Integer,Duration> getResults(){
        return this.results;
    }

    /**
     * Returns the type of the checkpoint.
     * @return Type of the checkpoint.
     */
    CheckpointType getType(){
        return this.type;
    }


    /**
     * Registers the result of a rider at this checkpoint.
     * @param riderID ID of the rider.
     * @param time Time of the rider.
     * @throws DuplicatedResultException If the rider has already a result registered at this checkpoint.
     * @throws IDNotRecognisedException If the rider is not registered at this checkpoint.
     */
    void registerResult(int riderID, Duration time) throws DuplicatedResultException,IDNotRecognisedException{
//        if (!this.results.containsKey(riderID)){
//            throw new IDNotRecognisedException("this rider is not registered in this stage");
//        }
        if (this.results.containsKey(riderID)){
            throw new DuplicatedResultException("This riders result has already been registered in this checkpoint");
        }
        this.results.put(riderID,time);
    }

    /**
     * Returns the IDs of the riders at this checkpoint.
     * @return IDs of the riders at this checkpoint.
     */
    public int [] getRiderIDs(){
        Set<Integer> keys = this.results.keySet();// this function returns set of all of the raceIDs


        // We're converting a Set of Integer keys to an array.
        // The toArray() method takes an array of the correct type as an argument.
        // Passing new Integer[0] ensures that the returned array is of type Integer.
        // If the set fits into the specified array, it's returned; otherwise, a new array is allocated.
        // Using new Integer[0] is just a placeholder; the method adjusts the array size as needed.
        Integer[] keysArray = keys.toArray(new Integer[0]);
        int[] intArr = new int [keysArray.length];
        for(int i =0;i<intArr.length;i++){
            intArr[i] = keysArray[i];
        }
        return intArr;
    }


    /**
     * Returns the order of the riders at this checkpoint.
     * @return Order of the riders at this checkpoint.
     */
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

    /**
     * Swaps two riders in the order.
     * @param i Index of the first element.
     * @param j Index of the second element.
     * @param keysArray Array of keys.
     * @param times Array of times.
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

    /**
     * Returns the sprinter points of the riders at this checkpoint.
     * @return Sprinter points of the riders at this checkpoint.
     */
    public HashMap<Integer,Float> getSprinterPoints(){
        HashMap<Integer,Float> points = new HashMap<>();
        for(Integer id : this.getRiderIDs()) {
            points.put(id,0.0f);
        }
        int [] raceOrder = this.getRaceOrder();
        for(int i =0;i<this.pointDistribution.length;i++){
            points.replace(raceOrder[i],(float)this.pointDistribution[i]);
        }
        return points;
    }

    /**
     * Gets the result of a rider at the checkpoint.
     *
     * @param riderID the ID of the rider
     * @return the result of the rider
     * @throws IDNotRecognisedException if the rider does not exist
     */
    Duration getRiderResult(int riderID) throws IDNotRecognisedException{
        if (!this.results.containsKey(riderID)){
            throw new IDNotRecognisedException("This riderID doesn't exist");
        }
        return this.results.get(riderID);
    }
}
class ITT extends Checkpoint{

    /**
     * Creates a new individual time trial stage.
     * @param location Location of the checkpoint.
     * @param type Type of the checkpoint.
     * @param averageGradient Average gradient of the course.
     * @param length Length of the course.
     * @param riders Array of rider IDs.
     */
    public ITT(Double location, CheckpointType type, Double averageGradient, Double length,int[] riders){
        super(location,type,riders);

        this.pointDistribution = new int[] {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
    }
} //Individual time trial stage

class IS extends Checkpoint{
    /**
     * Creates a new intermediate sprint.
     * @param location Location of the checkpoint.
     * @param type Type of the checkpoint.
     * @param riders Array of rider IDs.
     */
    public IS(Double location, CheckpointType type,int[] riders){
        super(location,type,riders);
        this.pointDistribution = new int[] {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
    }
} //Intermediate sprint
class CC extends Checkpoint{
    /**
     * Average gradient of the course to the checkpoint.
     */
    Double averageGradient;
    /**
     * Length of the course to the checkpoint.
     */
    Double length;
    /**
     * Creates a new checkpoint.
     * @param location Location of the checkpoint.
     * @param type Type of the checkpoint.
     * @param averageGradient Average gradient of the course.
     * @param length Length of the course.
     * @param riders Array of rider IDs.
     */
    public CC(Double location, CheckpointType type, Double averageGradient, Double length,int[] riders){
        super(location,type,riders);
        this.averageGradient = averageGradient;
        this.length = length;
        this.pointDistribution = new int [] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }
}

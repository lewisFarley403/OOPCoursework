package cycling;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public abstract class Checkpoint {
    public int [] pointDistribution;

    Double location;
    CheckpointType type;
    Double averageGradient;
    Double length;
    HashMap<Integer,Duration> results;
    public Checkpoint(Double location, CheckpointType type,int[] riders){
        this.location = location;
        this.type = type;
        this.averageGradient = averageGradient;
        this.length = length;
        this.results = new HashMap<>();
        this.pointDistribution = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        //creates empty results dict
        for(int riderID:riders){
            this.addRider(riderID);

        }
    }
    public void removeRider(int ID){
        this.results.remove(ID);
    }
    public void addRider(int riderID){
        this.results.put(riderID,null);
    }//null implies that the rider has not had a result registered yet
    HashMap<Integer,Float> getResults(){
        return this.results;
    }
    CheckpointType getType(){
        return this.type;
    }

    void registerResult(int riderID, Duration time) throws DuplicatedResultException,IDNotRecognisedException{
        if (!this.results.containsKey(riderID)){
            throw new IDNotRecognisedException("this rider is not registered in this stage");
        }
        if (this.results.get(riderID)==null){
            throw new DuplicatedResultException("This riders result has already been registered in this checkpoint");
        }
        this.results.put(riderID,time);
    }
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
}
class ITT extends Checkpoint{

    public ITT(Double location, CheckpointType type, Double averageGradient, Double length,int[] riders){
        super(location,type,riders);

        this.pointDistribution = new int[] {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
    }
} //Individual time trial stage

class IS extends Checkpoint{
    public IS(Double location, CheckpointType type,int[] riders){
        super(location,type,riders);
        this.pointDistribution = new int[] {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
    }
} //Intermediate sprint
class CC extends Checkpoint{
    Double averageGradient;
    Double length;
    public CC(Double location, CheckpointType type, Double averageGradient, Double length,int[] riders){
        super(location,type,riders);
        this.averageGradient = averageGradient;
        this.length = length;
        this.pointDistribution = new int [] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }
}

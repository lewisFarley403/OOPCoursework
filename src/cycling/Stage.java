package cycling;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class Stage {
    private StageType stageType;
    public boolean waitingForResults;
    public int[] riders;
    public HashMap<Integer, Duration> results; //riderID as keys, times as values
    double length;
    LocalDateTime startTime;

    public Stage(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime) {
        this.waitingForResults = false;
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

    public void concludePreparation() throws InvalidStageStateException {
        if (this.waitingForResults) {
            throw new InvalidStageStateException("This stage's preparation is already concluded");
        }
        this.waitingForResults = true;
    }

    public abstract HashMap<Integer, Float> getMountainPointsMap();

    public abstract void removeRider(int ID);//abstract needs to be implemented

    public abstract void addRider(int ID); //abstract, needs to be...

    public abstract HashMap<Integer, Duration> getGCPoints();

    public abstract HashMap<Integer, Float> getSprinterPointsMap();

    //    public abstract int [] getSprinterPointsArray();
    public Duration getGCPointsByID(int ID) {
        HashMap<Integer, Duration> totalGCs = this.getGCPoints();
        return totalGCs.get(ID); //catch errors here
    }

    public float getSprinterPointsByID(int ID) {
        HashMap<Integer, Float> totalSprinter = this.getSprinterPointsMap();
        return totalSprinter.get(ID);

    }

    double getLength() {
        return this.length;
    }

    boolean hasCheckpoint(int id) {
        return false;
    }

    int[] getCheckpoints() {
        int[] x = {};
        return x;
    }

    public abstract LocalTime[] getRiderResults(int riderID) throws IDNotRecognisedException;

    public abstract void registerRiderResults(int riderID, LocalTime[] checkpointTimes) throws InvalidCheckpointTimesException, DuplicatedResultException, IDNotRecognisedException, InvalidStageStateException;

    public abstract LocalTime getRiderAdjustedElapsedTimeInStage(int riderID);



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

    public int[] orderHashMapByRaceOrder(HashMap<Integer, Float> map) {
        int[] raceOrder = this.getRaceOrder();

        int[] orderedPoints = new int[raceOrder.length];

        int index = 0;
        for (int id : raceOrder) {
            orderedPoints[index] = map.get(id).intValue();//dealing with Float not float, so normal casting doesnt work
            index++;
        }
        return orderedPoints;
    }

    public int[] getSprinterPointsArray() {
        return this.orderHashMapByRaceOrder(this.getSprinterPointsMap());

    }

    public HashMap<Integer, Duration> adjustElapsedTime() {
        int[] raceOrder = this.getRaceOrder();
        HashMap<Integer, Duration> newResults = new HashMap<>();
        int currentPackCandidate = raceOrder[0]; //this variable will be used to track the fastest rider in each pack to then set their times to it
        int index = 1;
        Duration oneSecond = Duration.ofSeconds(1);
        for (int id : raceOrder) {
            if (index>raceOrder.length-1){
                break;
            }
            Duration r1 = this.results.get(id);
            Duration r2 = this.results.get(raceOrder[index]);
            System.out.println("r1 : "+r1.toString()+" r2 : "+r2);
            Duration delta = r2.minus(r1);
            System.out.println("delta " +delta);
            if (delta.compareTo(oneSecond) < 0) {
                newResults.put(raceOrder[index], this.results.get(currentPackCandidate));
            } else {
                currentPackCandidate = raceOrder[index];
                newResults.put(raceOrder[index],this.results.get(raceOrder[index]));
            }
            index++;
        }

        //check the last element
        //add the first rider, they always keep their time
        newResults.put(raceOrder[0],this.results.get(raceOrder[0]));
        return newResults;
    }
    public int[] getRaceOrder() {
        //returns array of riderIDs sorted by result
        //can be improved by swapping bubble sort for a better impl
        int[] keysArray = this.getRiderIDs();

        Duration[] times = new Duration[keysArray.length];
        for (int i = 0; i < keysArray.length; i++) {
            times[i] = this.results.get(keysArray[i]);
        }

        boolean change = true;
        while (change) {
            change = false;
            for (int i = 0; i < times.length - 1; i++) {
//                if(times[i]<times[i+1]){
                if (times[i].compareTo(times[i + 1]) > 0) {
                    swap(i, i + 1, keysArray, times);
                    change = true;
                }
            }
        }
        int[] ids = new int[keysArray.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = (int) keysArray[i];
        }
        return ids;

    }
    public int[] getRiderIDs() {
        Set<Integer> keys = this.results.keySet();
        Integer[] keysArray = keys.toArray(new Integer[0]);
        int[] intArr = new int[keysArray.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = keysArray[i];
        }
        return intArr;
    }
    public LocalTime[] getRankedAdjustedElapsedTimes() {
        HashMap<Integer, Duration> newResults = this.adjustElapsedTime();
        int[] raceOrder = this.getRaceOrder();
        LocalTime[] times = new LocalTime[newResults.size()];
        int index = 0;

        for (int id : raceOrder) {
            LocalTime midnight = LocalTime.of(0, 0, 0);
            LocalTime x = midnight.plus(newResults.get(id));
//            System.out.println("ln 171: "+x);
            times[index] = x;
//            System.out.println("indexing : "+times[index]);
            index++;
        }
        System.out.println(times[1]);
        return times;
    }
}


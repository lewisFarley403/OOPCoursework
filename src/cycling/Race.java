package cycling;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.*;

/**
 * Represents a race in a cycling competition.
 */
public class Race {
    /**
     * Unique id for the race
     */
    private int id;
    /**
     * Name of the race
     */
    private String name;
    /**
     * * Description of the race
     */
    private String description;
    /**
     * Stages in the race, mapped by their unique identifiers.
     */
    private HashMap<Integer,Stage> stages;
    /**
     * List of riders participating in the race.
     */
    private List<Rider> riders;

    /**
     * Constructs a new Race.
     *
     * @param id the unique identifier for the race
     * @param name the name of the race
     * @param description the description of the race
     */
    public Race(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
//        this.stages = new ArrayList<>(); changed to be a hashmap to better work with stageIDs
        this.stages = new HashMap<Integer,Stage>();
    }

    /**
     * Gets the unique identifier of the race.
     *
     * @return the unique identifier of the race
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the race.
     *
     * @return the name of the race
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the race.
     *
     * @return the description of the race
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the unique ID of the stages in the race.
     *
     * @return the unique identifiers of the stages in the race
     */
    public int[] getStages() {
        //return the keys of the hashmap, so it is only their IDs
        int i = 0;
        int [] keys = new int[this.stages.size()];
        for (Integer key : this.stages.keySet()) {
            keys[i] = key;
            i++;
        }
        return keys;
    }
//    public Stage getStageByID(int id){
//        return this.stages.get(id);
//    }

    /**
     * Adds a stage to the race.
     *
     * @param stageID the unique ID of the stage
     * @param stage the stage to add
     */
    public void addStage(int stageID,Stage stage) {
        this.stages.put(stageID,stage);
    }

    /**
     * Removes a stage from the race.
     *
     * @param stageID the unique identifier of the stage to remove
     */
    public void removeStage(int stageID) {
        this.stages.remove(stageID);
    }

    /**
     * Removes a rider from all stages in the race.
     *
     * @param riderID the unique identifier of the rider to remove
     */
    public void removeRider(int riderID){
        for (Stage stage: stages.values()){
            stage.removeRider(riderID);
        }
    }

    /**
     * Gets a stage in the race by its unique identifier.
     *
     * @param id the unique identifier of the stage
     * @return the stage with the given unique identifier
     * @throws IDNotRecognisedException if no stage with the given unique identifier exists in the race
     */
    public Stage getStageByID(int id) throws IDNotRecognisedException{

        return this.stages.get(id);

    }

    /**
     * Gets the general classification time map for the race.
     *
     * @return the general classification time map for the race
     */
    public HashMap<Integer,Duration> getGeneralClassificationTimeMap(){
        HashMap<Integer,Duration> overallPoints = new HashMap<>();
        for (Stage s : this.stages.values()){
            HashMap<Integer, Duration> stagePoints = s.getGCPoints();
            for(int riderID : stagePoints.keySet()){
                Duration temp = overallPoints.get(riderID);
                if (temp == null){
                    overallPoints.put(riderID,stagePoints.get(riderID));
                }
                else {
                    Duration points = stagePoints.get(riderID);
                    if (points != null) {
                        temp = temp.plus(points);

                    }
                    overallPoints.put(riderID, temp);
                }

            }



        }
        return overallPoints;

    }

    /**
     * Gets the general classification rank of the riders in the race.
     *
     * @return the general classification rank of the riders in the race
     */
    public int[] getRidersGeneralClassificationRank() {


        HashMap<Integer,Duration> overallPoints = this.getGeneralClassificationTimeMap();
        System.out.println("desperate now "+overallPoints);
        ArrayList<Integer> sortedIDs=new ArrayList<>(overallPoints.keySet());
        Collections.sort(sortedIDs,(k1, k2) -> {

                Duration d1 = overallPoints.get(k1);
                Duration d2 = overallPoints.get(k2);
                return d1.compareTo(d2);
            }
        );

        int[] sortedIDsArr = new int[sortedIDs.size()];
        for (int i = 0; i < sortedIDs.size(); i++) {
            sortedIDsArr[i] = sortedIDs.get(i);
        }
//        return overallPoints;
        return sortedIDsArr;
    }


    /**
     * Gets the riders participating in the race.
     *
     * @return the riders participating in the race
     */
    public List<Rider> getRiders() {
        return riders;
    }

    public ArrayList<Integer> getRace;
}
package cycling;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.*;

public class Race {
    private int id;
    private String name;
    private String description;
    private HashMap<Integer,Stage> stages;
    private List<Rider> riders;

    public Race(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
//        this.stages = new ArrayList<>(); changed to be a hashmap to better work with stageIDs
        this.stages = new HashMap<Integer,Stage>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

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
    public void addStage(int stageID,Stage stage) {
        this.stages.put(stageID,stage);
    }

    public void removeStage(int stageID) {
        this.stages.remove(stageID);
    }
    public void removeRider(int riderID){
        for (Stage stage: stages.values()){
            stage.removeRider(riderID);
        }
    }
    public Stage getStageByID(int id) throws IDNotRecognisedException{

        return this.stages.get(id);

    }

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

    public List<Rider> getRiders() {
        return riders;
    }

    public ArrayList<Integer> getRace;
}
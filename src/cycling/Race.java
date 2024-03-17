package cycling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Race {
    private int id;
    private String name;
    private String description;
    private HashMap<Integer,Stage> stages;

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
}
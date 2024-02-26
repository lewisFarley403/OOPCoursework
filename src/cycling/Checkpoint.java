package cycling;

import java.util.HashMap;

public class Checkpoint {
    Double location;
    CheckpointType type;
    Double averageGradient;
    Double length;
    HashMap<Integer,Float> results;
    public Checkpoint(Double location, CheckpointType type, Double averageGradient, Double length,int[] riders){
        this.location = location;
        this.type = type;
        this.averageGradient = averageGradient;
        this.length = length;
        this.results = new HashMap<>();

        //creates empty results dict
        for(int riderID:riders){
            this.results.put(riderID,0.0f);
        }
    }
    public void addRider(int riderID){
        this.results.put(riderID,0.0f);
    }
    HashMap<Integer,Float> getResults(){
        return this.results;
    }
    CheckpointType getType(){
        return this.type;
    }
}

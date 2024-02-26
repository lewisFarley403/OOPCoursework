package cycling;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Stage {
    private StageType stageType;
    public int[] riders;
    private HashMap<Integer, Float> results = new HashMap<>(); //riderID as keys, times as values

    public Stage(StageType stageType,int[] riders){
        this.stageType = stageType;
        this.riders = riders;
    }
    public void removeRider(int ID){

    }
    public void addRider(int ID){

    }
    public abstract HashMap<Integer,Float>getGCPoints();
    public abstract HashMap<Integer,Float>getSprinterPoints();
    public float getGCPointsByID(int ID){
        HashMap<Integer,Float> totalGCs= this.getGCPoints();
        return totalGCs.get(ID); //catch errors here
    }
    public float getSprinterPointsByID(int ID){
        HashMap<Integer,Float> totalSpitner = this.getSprinterPoints();


    }
}

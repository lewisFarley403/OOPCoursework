package cycling;


import cycling.StageType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

public class Timetrial extends Stage{
    HashMap<Integer,LocalTime> startTimeMap;
    int [] pointDistribution;
    public Timetrial(StageType stageType, int[] riders, String description, double length, LocalDateTime startTime){
        super(stageType,riders,description,length, startTime); // Call the constructor of the super class

        this.startTimeMap = new HashMap<>();
        this.pointDistribution = new int [] {0,0,0}; //placeholder
    }
    public HashMap<Integer,Float> getMountainPointsMap(){
        // cant have any, it doesnt have any cat climbs
        HashMap<Integer,Float> result = new HashMap<>();
        for (int id : this.results.keySet()){
            result.put(id,0.0f);
        }
        return result;
    }
    public void removeRider(int riderID){
        this.results.remove(riderID);
    }
    public void addRider(int riderID){
        this.results.put(riderID,null);
    }
    public HashMap<Integer, Duration> getGCPoints(){
        return this.results;
    }
    public HashMap<Integer,Float> getSprinterPointsMap(){
        HashMap<Integer,Float> points = new HashMap<>();
        for(int id : this.results.keySet()){
            points.put(id,0.0f);

        }

        int [] raceOrder=this.getRaceOrder();

        int i =0;
        for (int id:raceOrder){
            float tempPoints = points.get(id);
            tempPoints+=this.pointDistribution[i];
            points.put(id,tempPoints);

            i++;
        }

        return points;

    }
    public LocalTime[] getRiderResults(int riderID) throws IDNotRecognisedException{
        //idk how to calc start time

        LocalTime start =this.startTimeMap.get(riderID);
        Duration delta = this.results.get(riderID);
        LocalTime end = start.plus(delta);
        return new LocalTime[] {start,end}; // there are no checkpoints in this stage type

    }
    public void registerRiderResults(int riderID,LocalTime... times ){
        // throw exception for miss behaving times
        LocalTime startTime = times[0];
        LocalTime endTime = times[times.length-1];

        this.startTimeMap.put(riderID,times[0]);
        Duration duration = Duration.between(endTime,startTime);
        this.results.put(riderID,duration);
    }
    public LocalTime getRiderAdjustedElapsedTimeInStage(int riderID){
        LocalTime midNight = LocalTime.of(0,0,0);
        return midNight.plus(this.adjustElapsedTime().get(riderID));
    }



}

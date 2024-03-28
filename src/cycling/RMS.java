package cycling;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.IllegalArgumentException;

/**
 * The RMS (Rider Management System) class represents a system for managing riders and teams in the cycling domain.
 * It provides functionality to store information about riders and teams.
 */
public class RMS implements Serializable {
    public ArrayList<Rider> getRiders() {
        return riders;
    }
    public int [] getRiderIds(){
        int [] ids = new int [this.riders.size()];
        for(int i =0;i<ids.length;i++){
            ids[i] = this.riders.get(i).getRiderID();
        }
        return ids;
    }

//    public ArrayList<Team> getTeams() {
//        return teams;
//    }
    public int [] getTeams(){
        int [] teamIDs = new int[this.teams.size()];
        for (int i =0;i<this.teams.size();i++){
            Team t = this.teams.get(i);
            teamIDs[i] = t.getID();

        }
        return teamIDs;
    }
    private ArrayList<Rider> riders;
    private ArrayList<Team> teams;
    private int nextAvailableTeamID;
    private int nextAvailablePlayerID;
    public RMS(){
        this.riders = new ArrayList<>(); // type is infered
        this.teams = new ArrayList<>();
        this.nextAvailablePlayerID=0;
        this.nextAvailableTeamID=0;

    }

    /**
     * Creates a team with name and description.
     * <p>
     * The state of this MiniCyclingPortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param name        The identifier name of the team.
     * @param description A description of the team.
     * @return The ID of the created team.
     * @throws IllegalNameException If the name already exists in the platform.
     * @throws InvalidNameException If the name is null, empty, has more than 30
     *                              characters, or has white spaces.
     */
    //copied from the interface
    public int createTeam(String name,String description) throws IllegalNameException,InvalidNameException{
        Team team = new Team(name,description,this.nextAvailableTeamID);
        if (name == null){
            throw new InvalidNameException("Name should not be null");
        }
        if (name.length() > 30 || name.contains(" ")){
            throw new InvalidNameException("name should not be greater than 30 characters or contain white space");
        }
        for (Team t : this.teams){
            if (t.getName().equals(name)){
                throw new IllegalNameException("This name is already in use by another team");
            }
        }

        this.teams.add(team);
        this.nextAvailableTeamID++;
        return this.nextAvailableTeamID-1;
    }

    /**
     * Looks for a team with this team ID, and returns this index
     * @param ID
     * @return i of team by that ID. -1 if there is no team with that team ID
     */
    private int getTeamIndexByID(int ID){
        int i;
        for(i =0;i<this.teams.size();i++){
            Team t = this.teams.get(i);
            if(t.getID() == ID){
                return i;
            }
        }
        i=-1;
        return i;
    }

    /**
     * Deletes a team by ID, sets all riders with that ID to have a teamID to -1
     * @param ID
     * @throws IDNotRecognisedException if the team ID does not exist
     *
     */
    public void removeTeam(int ID) throws IDNotRecognisedException{
        int index = this.getTeamIndexByID(ID);
        if(index!=-1) {
            throw new IDNotRecognisedException("This team ID does not exist");
        }
        for(int i =0;i<this.riders.size();i++){
            Rider r = this.riders.get(i);
            if (r.getTeamID()==ID){
                r.setTeamID(-1);//has no team or team has been deleted
            }
        }

    }

    /**
     *
     * @param teamID
     * @param name
     * @param yearOfBirth
     * @return The ID of the new rider created
     * @throws IllegalArgumentException if the name of the rider is null or empty,
     *                                  or the year of birth is less than 1980
     * @throws IDNotRecognisedException if the team ID doesn't exist in the
     *                                  system
     */

    public int createRider(Integer teamID, String name, Integer yearOfBirth) throws IDNotRecognisedException{
        boolean teamIDExists = false;

        //check if the yearOfBirth satifies the conditions for the yearOfBirth
        if (yearOfBirth == null){
            throw new IllegalArgumentException("year of birth cannot be null or empty");
        }
        if (yearOfBirth < 1900){
            throw new IllegalArgumentException("Year of birth cannot be less than 1900");
        }

        //check if the teamID exists in any team in the teams
        for (Team t : this.teams){
            if(t.getID() == teamID) {
                teamIDExists = true;
            }
        }
        if(!teamIDExists){
            throw new IDNotRecognisedException("team ID does not match any team in the system");
        }

        Rider r = new Rider(name,yearOfBirth,this.nextAvailablePlayerID,teamID);
        this.riders.add(r);
        this.nextAvailablePlayerID++;// this needs to be incremented for the next one
        return this.nextAvailablePlayerID-1; // needs to -1 because we incremented it
    }

    /**
     * return the index of this riderID in the riders arrayList. return -1 if this riderID doesn't exist
     * @param ID
     * @return index of the riderID
     */
    private int getRiderByID(int ID){
        int i;
        for (i=0;i<this.riders.size();i++){
            Rider r = this.riders.get(i);
            if (r.getRiderID() == ID){
                return i;
            }
        }
        i = -1;
        return -1;
    }

    /**
     * remove the rider from the riders
     * @throws IDNotRecognisedException if this riderID doesn't exist
     *                                  in the system.
     * @param riderID
     */
    public void removeRider(int riderID) throws IDNotRecognisedException{
        //must remember to remove all of their racing results
        int indexOfRider = this.getRiderByID(riderID);
        if(indexOfRider ==-1){
            throw new IDNotRecognisedException("rider ID doesn't exist in the system");
        }
        this.riders.remove(riderID);
    }
    private int getTeamByID(int teamID){
        for(int i =0;i<this.teams.size();i++){
            if (this.teams.get(i).getID()==teamID){
                return i;
            }
        }
        return -1;
    }

    /**
     * gets all of the riders that have a teamID as their teamID
     * @param teamID
     * @return int[] of riders riderID
     * @throws IDNotRecognisedException if the teamID is not in system
     */


    public int[] getTeamRiders(int teamID) throws IDNotRecognisedException{
        if(this.getTeamByID(teamID)==-1){
            throw new IDNotRecognisedException("This team ID is not in the system");
        }
        ArrayList<Integer> ids; //use this because idk how long the array needs to be. At most it is riders.size(), but then theres lots of wasted space
        ids = new ArrayList<>();
        for (Rider r : this.riders){
            if (r.getTeamID() == teamID){
                ids.add(r.getRiderID());
            }
        }
        int[] converted; //used to convert arrayList into int []
        converted = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            converted[i] = ids.get(i);
        }
        return converted;
    }
    boolean hasRiderRegistered(int riderID){
        return !(this.getRiderByID(riderID) == -1);
    }

}

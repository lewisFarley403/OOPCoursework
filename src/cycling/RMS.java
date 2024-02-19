package cycling;
import java.util.ArrayList;

/**
 * The RMS (Rider Management System) class represents a system for managing riders and teams in the cycling domain.
 * It provides functionality to store information about riders and teams.
 */
public class RMS {
    public ArrayList<Rider> getRiders() {
        return riders;
    }

    public ArrayList<Team> getTeams() {
        return teams;
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

    public int createTeam(String name,String description){
        Team team = new Team(name,description,this.nextAvailableTeamID);
        if (name == null){
            throw new InvalidNameException("Name should not be null");
        }
        if (name.length() > 30 || name.contains(" ")){
            throw new InvalidNameException("name should not be greater than 30 characters or contain white space");
        }
        for (Team t : this.teams){
            if (t.getName() == name){
                throw new IllegalNameException("This name is already in use by another team");
            }
        }

        this.teams.add(team);
        this.nextAvailableTeamID++;
        return this.nextAvailableTeamID-1;
    }
    private int getTeamIndexByID(int ID){
        for(int i =0;i<this.teams.size();i++){
            Team t = this.teams.get(i);
            if(t.getID() == ID){
                return i;
            }
        }
        return -1;
    }
    public void removeTeam(int ID){
        //needs more work on this to remove this team ID from all the players that belong to that team
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
    public int createRider(int teamID, String name, int yearOfBirth){
        Rider r = new Rider(name,yearOfBirth,this.nextAvailablePlayerID,teamID);
        this.riders.add(r);
        this.nextAvailablePlayerID++;
        return this.nextAvailablePlayerID-1;
    }
}

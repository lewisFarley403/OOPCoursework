package cycling;

public class Rider {
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getRiderID() {
        return riderID;
    }

    public void setRiderID(int riderID) {
        this.riderID = riderID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    private String name;
    private int yearOfBirth;
    private int riderID;
    private int teamID;
    public Rider(String name,int yearOfBirth, int riderID,int teamID){
        this.name = name;
        this.yearOfBirth=yearOfBirth;
        this.riderID=riderID;
        this.teamID=teamID;


    }
}

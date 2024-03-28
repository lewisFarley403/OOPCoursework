package cycling;

/**
 * Represents a rider in a cycling competition.
 */
public class Rider {

    /**
     * Gets the name of the rider.
     *
     * @return the name of the rider
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Sets the name of the rider.
     *
     * @param name the new name of the rider
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * Gets the year of birth of the rider.
     *
     * @return the year of birth of the rider
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Sets the year of birth of the rider.
     *
     * @param yearOfBirth the new year of birth of the rider
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Gets the unique ID of the rider.
     *
     * @return the unique identifier of the rider
     */
    public int getRiderID() {
        return riderID;
    }

    /**
     * Sets the unique identifier of the rider.
     *
     * @param riderID the new unique identifier of the rider
     */
    public void setRiderID(int riderID) {
        this.riderID = riderID;
    }

    /**
     * Gets the unique identifier of the team the rider belongs to.
     *
     * @return the unique identifier of the team the rider belongs to
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * Sets the unique identifier of the team the rider belongs to.
     *
     * @param teamID the new unique identifier of the team the rider belongs to
     */
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
    /**
     * The total time of the rider.
     */
    private int totalTime;
    /**
     * The points of the rider.
     */
    private int points;

    /**
     * The name of the rider.
     */
    private String name;
    /**
     * The year of birth of the rider.
     */
    private int yearOfBirth;
    /**
     * The unique ID of the rider.
     */
    private int riderID;
    /**
     * The unique ID of the team the rider belongs to.
     */
    private int teamID;
    /**
     * The mountain points of the rider.
     */
    private int mountainPoints;
    /**
     * Constructs a new Rider.
     *
     * @param name the name of the rider
     * @param yearOfBirth the year of birth of the rider
     * @param riderID the unique ID of the rider
     * @param teamID the unique ID of the team the rider belongs to
     */
    public Rider(String name,int yearOfBirth, int riderID,int teamID){
        this.name = name;
        this.yearOfBirth=yearOfBirth;
        this.riderID=riderID;
        this.teamID=teamID;
        this.totalTime = 0; // Initialize totalTime to 0
        this.points = 0; // Initialize points to 0
    }
    /**
     * Gets the total time of the rider.
     *
     * @return the total time of the rider
     */
    public int getTotalTime() {
        return totalTime;
    }

    /**
     * Sets the total time of the rider.
     *
     * @param totalTime the new total time of the rider
     */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Gets the points of the rider.
     *
     * @return the points of the rider
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points of the rider.
     *
     * @param points the new points of the rider
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the mountain points of the rider.
     *
     * @return the mountain points of the rider
     */
    public int getMountainPoints() {
        return this.mountainPoints;
    }
}

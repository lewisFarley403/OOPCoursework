package cycling;
import java.lang.String;
/**
 * Represents a team in a cycling competition.
 */
public class Team {
    /**
     * The name of the team.
     */
    private String name;
    /**
     * The description of the team.
     */
    private String description;
    /**
     * The unique ID of the team.
     */
    private int ID;

    /**
     * Gets the name of the team.
     *
     * @return the name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the team.
     *
     * @return the description of the team
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the unique identifier of the team.
     *
     * @return the unique identifier of the team
     */
    public int getID() {
        return ID;
    }

    /**
     * Constructs a new Team.
     *
     * @param name the name of the team
     * @param description the description of the team
     * @param ID the unique identifier of the team
     */
    public Team(String name, String description, int ID){
        this.name=name;
        this.description = description;
        this.ID = ID ;
    }


}

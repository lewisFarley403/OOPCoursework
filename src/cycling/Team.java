package cycling;
import java.lang.String;
public class Team {
    private String name;
    private String description;
    private int ID;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return ID;
    }

    public Team(String name, String description, int ID){
        this.name=name;
        this.description = description;
        this.ID = ID ;
    }


}

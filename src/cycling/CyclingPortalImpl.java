package cycling;

public class CyclingPortalImpl implements CyclingPortal{
    RMS rms;
    public CyclingPortalImpl(){
        this.rms = new RMS();

    }

    @Override
    public int createTeam(String name, String description){
        return this.rms.createTeam(name,description);
    }
    @Override
    public void removeTeam(int teamID){
        this.rms.removeTeam(teamID);
    }
    @Override
    public int[] getTeams(){
        return this.rms.getTeams();
    }
    @Override
    public int[] getTeamRiders(int teamID){
        return this.rms.getTeamRiders(teamID);
    }

    public int createRider(int teamID,String name, int yearOfBirth){
        //test this because of Integer to let things be null
        return this.rms.createRider(teamID,name,yearOfBirth);
    }
    @Override
    public void removeRider(int riderID){
        this.rms.removeRider(riderID);
        //more to this function, must remove all of this riders time and stuff

    }


}

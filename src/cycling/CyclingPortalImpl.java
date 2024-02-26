package cycling;

public abstract class CyclingPortalImpl implements CyclingPortal{ //take out abstract later
    RMS rms;
    public CyclingPortalImpl(){
        this.rms = new RMS();

    }

    @Override
    public int createTeam(String name, String description) throws IllegalNameException,InvalidNameException{
        return this.rms.createTeam(name,description);
    }
    @Override
    public void removeTeam(int teamID) throws IDNotRecognisedException{
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

    public int createRider(int teamID,String name, int yearOfBirth) throws IDNotRecognisedException{
        //test this because of Integer to let things be null
        return this.rms.createRider(teamID,name,yearOfBirth);
    }
    @Override
    public void removeRider(int riderID) throws IDNotRecognisedException{
        this.rms.removeRider(riderID);
        //more to this function, must remove all of this riders time and stuff

    }


}

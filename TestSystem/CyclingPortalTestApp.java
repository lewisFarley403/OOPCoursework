import cycling.*;

import java.util.HashMap;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortal interface -- note you
 * will want to increase these checks, and run it on your CyclingPortalImpl class
 * (not the BadCyclingPortal class).
 *
 * 
 * @author Diogo Pacheco
 * @version 2.0
 */
public class CyclingPortalTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) throws IllegalNameException,InvalidNameException,IDNotRecognisedException{
		//RMS tests
		Team t = new Team("t1", "desc 1",11);
		RMS rms = new RMS();
		int teamid = rms.createTeam("t1","desc1");
		rms.createRider(teamid,"testguy",2004);
		rms.removeTeam(1);
		System.out.println(rms.getRiders().get(0).getTeamID());
		System.out.println("The system compiled and started the execution...");

		// Stage test
		int[] a = {1, 2, 3, 4, 5};
		StageWithCheckpoints s = new StageWithCheckpoints(a);
		int id =s.addCategorizedClimbToStage(10.0,CheckpointType.C1,0.8,100.0);
		System.out.println(id);
//		s.removeCheckpoint(0);
//		s.removeCheckpoint(0);


		HashMap<Integer,Float> mps=s.getMountainPoints();
		System.out.println(mps);




//		// TODO replace BadMiniCyclingPortalImpl by CyclingPortalImpl
//		MiniCyclingPortal portal1 = new BadMiniCyclingPortalImpl();
//		MiniCyclingPortal portal2 = new BadMiniCyclingPortalImpl();
//



//		assert (portal1.getRaceIds().length == 0)
//				: "Innitial Portal not empty as required or not returning an empty array.";
//		assert (portal1.getTeams().length == 0)
//				: "Innitial Portal not empty as required or not returning an empty array.";
//
//		try {
//			portal1.createTeam("TeamOne", "My favorite");
//			portal2.createTeam("TeamOne", "My favorite");
//		} catch (IllegalNameException e) {
//			e.printStackTrace();
//		} catch (InvalidNameException e) {
//			e.printStackTrace();
//		}
//
//		assert (portal1.getTeams().length == 1)
//				: "Portal1 should have one team.";
//
//		assert (portal2.getTeams().length == 1)
//				: "Portal2 should have one team.";

	}

}

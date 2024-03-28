import cycling.*;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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


	public static void main(String[] args) throws IllegalNameException, InvalidNameException, IDNotRecognisedException, InvalidLengthException, InvalidStageStateException, InvalidLocationException, InvalidStageTypeException, DuplicatedResultException, InvalidCheckpointTimesException {
		//RMS tests
//		Team t = new Team("t1", "desc 1", 11);
//		RMS rms = new RMS();
//		int teamid = rms.createTeam("t1", "desc1");
//		rms.createRider(teamid, "testguy", 2004);
//		rms.removeTeam(1);
//		System.out.println(rms.getRiders().get(0).getTeamID());
//		System.out.println("The system compiled and started the execution...");
//
//
//		CyclingPortalImpl c = new CyclingPortalImpl();
//		int idr = c.createRace("t1", "shit");
//		int ids = c.addStageToRace(1, "r1s1", "blah", 69, LocalDateTime.of(2000, 1, 1, 20, 0, 0), StageType.FLAT);
//		System.out.println("RaceID is " + idr);
//		System.out.println("stageID is " + ids);
//		int idc = c.addCategorizedClimbToStage(ids, 69.0, CheckpointType.C1, 0.5, 2.0);
//		int idt = c.createTeam("team1", " ");
//		int riderID = c.createRider(idt, "bob", 2000);
//		System.out.println("Climb ID = " + idc);
//		System.out.println("Team ID = " + idt);
//
//		System.out.println("riderID = " + riderID);
//
//
//		c.concludeStagePreparation(ids);
//		LocalTime[] timesIn = {LocalTime.of(20, 0, 0), LocalTime.of(20, 30, 0), LocalTime.of(21, 0, 0)};
//		c.registerRiderResultsInStage(ids, riderID, LocalTime.of(20, 0, 0), LocalTime.of(20, 30, 0), LocalTime.of(21, 0, 0));
//		LocalTime[] timesOut = c.getRiderResultsInStage(ids, riderID);
//		System.out.println("TimesIn[0] = " + timesIn[2] + " times out [0] = " + timesOut[2]);
		// Stage test
		int[] a = {1, 2, 3, 4, 5};
//		testCreateRace();
//		testViewRaceDetails();
//		testAddStageToRace();
//
//
//
//	main test suit
//
//
//		StageWithCheckpoints s = new StageWithCheckpoints(a);
//		int id = s.addCategorizedClimbToStage(10.0, CheckpointType.C1, 0.8, 100.0);
//		System.out.println(id);
//		s.removeCheckpoint(0);
//		s.removeCheckpoint(0);
//
//
//		HashMap<Integer, Float> mps = s.getMountainPoints();
//		System.out.println(mps);
//
//
//		// TODO replace BadMiniCyclingPortalImpl by CyclingPortalImpl
//		MiniCyclingPortal portal1 = new BadMiniCyclingPortalImpl();
//		MiniCyclingPortal portal2 = new BadMiniCyclingPortalImpl();
//
//
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
//
//
//		// Race tests
//		CyclingPortal portal = new CyclingPortalImpl() {
//			@Override
//			public void removeRaceByName(String name) throws NameNotRecognisedException {
//
//			}
//
//			@Override
//			public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
//				return new LocalTime[0];
//			}
//
//			@Override
//			public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
//				return null;
//			}
//
//			@Override
//			public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
//				return 0;
//			}
//
//			@Override
//			public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type) throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
//				return 0;
//			}
//
//			@Override
//			public double getStageLength(int stageId) throws IDNotRecognisedException {
//				return 0;
//			}
//
//			@Override
//			public void removeStageById(int stageId) throws IDNotRecognisedException {
//
//			}
//
//			@Override
//			public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient, Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
//				return 0;
//			}
//
//			@Override
//			public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
//				return 0;
//			}
//
//			@Override
//			public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
//
//			}
//
//			@Override
//			public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
//
//			}
//
//			@Override
//			public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpointTimes) throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException, InvalidStageStateException {
//
//			}
//
//			@Override
//			public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
//				return new LocalTime[0];
//			}
//
//			@Override
//			public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
//				return null;
//			}
//
//			@Override
//			public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
//
//			}
//
//			@Override
//			public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
//				return new LocalTime[0];
//			}
//
//			@Override
//			public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
//				return new int[0];
//			}
//
//			@Override
//			public void eraseCyclingPortal() {
//
//			}
//
//			@Override
//			public void saveCyclingPortal(String filename) throws IOException {
//
//			}
//
//			@Override
//			public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
//
//			}
//		};
//		try {
//			// Create a race
//			int raceId = portal.createRace("Race Name", "Race description");
//			System.out.println("Created race with ID: " + raceId);
//
//			// Get all race IDs
//			int[] raceIds = portal.getRaceIds();
//			System.out.println("Current race IDs: " + Arrays.toString(raceIds));
//
//			// Get stages of the created race
//			int[] stageIds = portal.getRaceStages(raceId);
//			System.out.println("Stages for race " + raceId + ": " + Arrays.toString(stageIds));
//
//			// Remove the created race
//			portal.removeRaceById(raceId);
//			System.out.println("Removed race with ID: " + raceId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


		// TODO: MORE TEST CASES TO BE ADDED
		//TODO: FIX THE TEST CASES
//		CyclingPortalImpl portal = new CyclingPortalImpl();
//
//		// Test for eraseCyclingPortal
//		portal.eraseCyclingPortal();
//		System.out.println(portal.getRaceIds().length == 0 ? "Pass 1" : "Fail 1");
//
//		// Test for saveCyclingPortal and loadCyclingPortal
//		try {
//			portal.saveCyclingPortal("testPortal.dat");
//			portal.loadCyclingPortal("testPortal.dat");
//			System.out.println("Pass 2");
//		} catch (
//				Exception e) {
//			System.out.println("Fail 2");
//			e.printStackTrace();
//		}
//
//		// Test for removeRaceByName
//		try {
//			portal.createRace("Test Race", "This is a test race");
//			portal.removeRaceByName("Test Race");
//			System.out.println("Pass 3");
//		} catch (
//				Exception e) {
//			System.out.println("Fail 3");
//			e.printStackTrace();
//		}
//
//		// Test for getRidersGeneralClassificationRank
//		try {
//			int raceId = portal.createRace("Test Race", "This is a test race");
//			int stageID = portal.addStageToRace(raceId, "Test1", "", 10.0, LocalDateTime.now(), StageType.FLAT);
//			portal.addIntermediateSprintToStage(stageID, 10);
//			System.out.println(portal.createTeam("", ""));
//
//			portal.createRider(0, "t1", 2000);
//			portal.createRider(0, "t2", 2001);
//			System.out.println(stageID);
//			portal.concludeStagePreparation(stageID);
//
//			portal.registerRiderResultsInStage(stageID, 0, LocalTime.of(1, 15, 0), LocalTime.of(2, 15, 0), LocalTime.of(3, 0, 0));
//			portal.registerRiderResultsInStage(stageID, 1, LocalTime.of(1, 15, 0), LocalTime.of(2, 15, 0), LocalTime.of(4, 22, 0));
//
////		portal.concludeStagePreparation(stageID);
//			int[] ranks = portal.getRidersGeneralClassificationRank(raceId);
//			System.out.println(Arrays.toString(ranks));
//			System.out.println("test 4");
//		} catch (
//				Exception e) {
//			System.out.println("Fail 4");
//			e.printStackTrace();
//		}
//
//		// Test for getGeneralClassificationTimesInRace
//		try {
////		int raceId = portal.createRace("Test Race", "This is a test race");
//			LocalTime[] times = portal.getGeneralClassificationTimesInRace(1);
//			System.out.println(Arrays.toString(times));
//			System.out.println("test 5");
//		} catch (
//				Exception e) {
//			System.out.println("Fail 5");
//			e.printStackTrace();
//		}
//
//		// Test for getRidersPointsInRace
//		try {
//			int raceId = portal.createRace("Test Race", "This is a test race");
//			int[] points = portal.getRidersPointsInRace(raceId);
//			System.out.println("Pass test 6");
//			System.out.println(Arrays.toString(points));
//		} catch (
//				Exception e) {
//			System.out.println("Fail 6");
//			e.printStackTrace();
//		}
//
//		// Test for getRidersMountainPointsInRace
//		int raceId = 1;
//		int[] result = portal.getRidersMountainPointClassificationRank(raceId);
//		int [] result2= portal.getRidersMountainPointsInRace(raceId);
//		System.out.println("results "+result2[1]);
		testRace();


}
public static void testCreateRace() {
	CyclingPortalImpl portal = new CyclingPortalImpl();
	try {
		int raceId = portal.createRace("Tour de France", "Annual cycling race in France");
		if (raceId != 1) {
			System.err.println("Test failed: Incorrect race ID returned.");
		} else {
			System.out.println("Test passed: Race created successfully.");
		}
	} catch (Exception e) {
		System.err.println("Test failed: Exception thrown: " + e.getMessage());
	}
}


public static void testViewRaceDetails() {
	CyclingPortalImpl portal = new CyclingPortalImpl();
	try {
		int raceId = portal.createRace("Tour de France", "Annual cycling race in France");
		String details = portal.viewRaceDetails(raceId);
		if (!details.contains("Tour de France")) {
			System.err.println("Test failed: Race details not as expected.");
		} else {
			System.out.println("Test passed: Race details viewed successfully.");
		}
	} catch (Exception e) {
		System.err.println("Test failed: Exception thrown: " + e.getMessage());
	}
}


public static void testAddStageToRace() {
	CyclingPortalImpl portal = new CyclingPortalImpl();
	try {
		int raceId = portal.createRace("Tour de France", "Annual cycling race in France");
		int stageId = portal.addStageToRace(raceId, "Mountain Stage 1", "Stage with mountains", 150.5,
				LocalDateTime.now(), StageType.HIGH_MOUNTAIN);
		if (stageId != 0) {
			System.err.println("Test failed: Incorrect stage ID returned.");
		} else {
			System.out.println("Test passed: Stage added to race successfully.");
		}
	} catch (Exception e) {
		System.err.println("Test failed: Exception thrown: " + e.getMessage());
	}
}
public static void testRace() throws IllegalNameException, InvalidNameException,IDNotRecognisedException,cycling.InvalidLocationException, cycling.InvalidStageStateException, cycling.InvalidStageTypeException, cycling.InvalidLengthException,cycling.IDNotRecognisedException, cycling.DuplicatedResultException, cycling.InvalidCheckpointTimesException, cycling.InvalidStageStateException{
	CyclingPortalImpl c = new CyclingPortalImpl();
	addRiders(c);
	addResults(c);
	System.out.println("setup complete");
	LocalTime[] x =c.getRankedAdjustedElapsedTimesInStage(0);
	System.out.println("sample "+x[1]);
	for(int i=0; i<x.length;i++){

		System.out.println(i+" "+x[i]);
	}


}
public static void addResults(CyclingPortalImpl c) throws cycling.IDNotRecognisedException, cycling.DuplicatedResultException, cycling.InvalidCheckpointTimesException, cycling.InvalidStageStateException{
	ArrayList<ArrayList<LocalTime>> raceData = new ArrayList<>();

	// Initialize each inner ArrayList separately and add them to raceData
	raceData.add(convertToTimeList("10:05:00.0", "10:06:20.2", "10:08:05.8", "10:09:50.6"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:19.800",  "10:08:05.900", "10:09:51.3"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:21.500", "10:08:06.500", "10:09:52.700"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:22.100", "10:08:07.100", "10:09:53.200"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:23.700", "10:08:07.800", "10:09:54.500"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:24.200", "10:08:08.300", "10:09:55.900"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:25.600", "10:08:09.100", "10:09:56.800"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:26.300", "10:08:09.600", "10:09:57.100"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:27.900", "10:08:10.200", "10:09:58.400"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:28.400", "10:08:10.700", "10:09:59.200"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:29.800", "10:08:11.300", "10:10:00.300"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:30.100", "10:08:11.900", "10:10:01.500"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:31.700", "10:08:12.400", "10:10:02.600"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:32.200", "10:08:13.100", "10:10:03.800"));
	raceData.add(convertToTimeList("10:05:00.0", "10:06:33.900", "10:08:13.700", "10:10:04.400"));

	int riderId=0;
	c.concludeStagePreparation(0);
	// Iterate over raceData and print each individual list
	for (ArrayList<LocalTime> riderData : raceData) {
		c.registerRiderResultsInStage(0,riderId,riderData.toArray(new LocalTime[0]));
		riderId++;
	}

	// Assume the race data is initialized here with the provided list



}
	private static ArrayList<LocalTime> convertToTimeList(String... times) {
		ArrayList<LocalTime> timeList = new ArrayList<>();
		for (String time : times) {
			timeList.add(LocalTime.parse(time));
		}
		return timeList;
	}
public static void addRiders(CyclingPortalImpl c) throws IllegalNameException, InvalidNameException,IDNotRecognisedException,cycling.InvalidLocationException, cycling.InvalidStageStateException, cycling.InvalidStageTypeException, cycling.InvalidLengthException{
		int teamID = c.createTeam("t","teammmmm");
		System.out.println("added team");


	// Names of riders
	String[] riderNames = {"alpha", "beta", "charlie", "delta", "echo", "foxtrot", "golf", "hotel", "india", "juliet", "kilo", "lema", "mike", "november", "oscar", "papa"};

	// Years of birth for riders
	int[] yearsOfBirth = {1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975};

	// Assuming 'c' is an instance of the object with the createRider method
	for (int i = 0; i < riderNames.length; i++) {
		c.createRider(teamID, riderNames[i], yearsOfBirth[i]);
	}
	System.out.println("added all of the riders");
	createSimpleRace(c);


}
static void createSimpleRace(CyclingPortalImpl c) throws IllegalNameException, InvalidNameException,IDNotRecognisedException, InvalidLocationException,InvalidStageStateException, InvalidStageTypeException,InvalidLengthException{
		int raceID = c.createRace("simple","simples race with 1 stage 2 checkpoints");
		System.out.println("created empty race with id "+raceID);
		int flatID=c.addStageToRace(raceID,"stage 1","",100.0,LocalDateTime.of(2024,1,1,5,0,0),StageType.FLAT);
		c.addCategorizedClimbToStage(flatID,50.0,CheckpointType.C1,19.0,10.0);
		c.addIntermediateSprintToStage(flatID,75.0);
		System.out.println("Successfully added all stages and checkpoints");

}

}


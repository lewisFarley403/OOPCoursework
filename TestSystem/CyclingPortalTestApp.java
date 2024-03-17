import cycling.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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


	public static void main(String[] args) throws IllegalNameException, InvalidNameException, IDNotRecognisedException {
		//RMS tests
		Team t = new Team("t1", "desc 1", 11);
		RMS rms = new RMS();
		int teamid = rms.createTeam("t1", "desc1");
		rms.createRider(teamid, "testguy", 2004);
		rms.removeTeam(1);
		System.out.println(rms.getRiders().get(0).getTeamID());
		System.out.println("The system compiled and started the execution...");

		// Stage test
		int[] a = {1, 2, 3, 4, 5};
//		StageWithCheckpoints s = new StageWithCheckpoints(a);
//		int id = s.addCategorizedClimbToStage(10.0, CheckpointType.C1, 0.8, 100.0);
//		System.out.println(id);
//		s.removeCheckpoint(0);
//		s.removeCheckpoint(0);


//		HashMap<Integer, Float> mps = s.getMountainPoints();
//		System.out.println(mps);


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
	}
}

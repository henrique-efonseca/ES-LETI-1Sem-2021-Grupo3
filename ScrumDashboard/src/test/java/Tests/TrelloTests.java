package Tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Backend.TrelloAPI;

public class TrelloTests {
	static String s1= "a04256995af78e5ea7bff424d82cf477";
	static String s2 = "47fd71497e5ffcb377ea49fd0302a42f66ba0a411829da35dac1ade25025e501";
	static String s3 = "60eae4ca19ea426cbba3021a";
	static TrelloAPI app;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 app = new TrelloAPI();
		 app.setUp(s1,s2,s3);
	}

	@Test
	public void testToCSV() {
		
		 assertNotNull(app.noArtifact());
	}

	@Test
	public void testNoArtifact() {

		 assertNotNull(app.noArtifact());
	}

	@Test
	public void testArtifact() {
		 assertNotNull(app.artifact());
	}
	
	@Test
	public void testGetSprints() {

		 assertNotNull(app.getSprints());
	}

	@Test
	public void testRecursosHumanos() {
	
		 assertNotNull(app.humanResourcesCost());
	}

	@Test
	public void testTempoSprints() {
	
		 assertNotNull(app.sprintTime());
	}

	@Test
	public void testFunctionDate() {
	
		 assertNotNull(app.functionDate());
	}

	@Test
	public void testSprintsText() {
	
		 assertNotNull(app.sprintsText());
	}

	@Test
	public void testProductBacklog() {
			
		 assertNotNull(app.productBacklog());
	}

	@Test
	public void testSprintDates() {

		 assertNotNull(app.getSprints());
	}

	@Test
	public void testProjectDate() {
	
		 assertNotNull(app.projectDate());
	}

	@Test
	public void testProjectID() {	
		 assertNotNull(app.projectID());
	}

	@Test
	public void testMembers() {
		assertNotNull(app.members());
		String m = app.members();
		assertTrue(m.contains("ricardonunosilvapaulo"));
		
	}


}

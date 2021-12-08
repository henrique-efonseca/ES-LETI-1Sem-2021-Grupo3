package Tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHTag;

import Backend.GitHubAPI;

class GithubTests {
	
	static GitHubAPI app,app2;	
	static String s1 ="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";
	static String s2= "henrique-efonseca/ES-LETI-1Sem-2021-Grupo3";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		app= new GitHubAPI();
		app2= new GitHubAPI();
		app.setUp(s1,s2);
		app2.setUp(s1,s2);
	}

	@Test
	void testCommits() throws IOException {
		String commits = app.commits();
		//Some of the commits from "commits"
		boolean statement=commits.contains("Testing connection to remote repositorie");
		assertTrue(statement);
		boolean statement2=commits.contains("Added trello API conection");
		assertTrue(statement2);
		boolean statement3=commits.contains("Worked on JavaDoc");
		assertTrue(statement3);
		boolean statement4=commits.contains("Tue Dec 07 23:02:33 WET 2021");
		assertTrue(statement4);
		boolean statement5=commits.contains("Added function to show Tags and respective dates");
		assertTrue(statement5);
		boolean statement6=commits.contains("SD-24");
		assertTrue(statement6);
	}

	@Test
	void testGetREADME() throws IOException {
		assertNotNull(app.getREADME());
		assertNotNull(app2.getREADME());
		assertEquals(app2.getREADME(),app.getREADME());
	}

	@Test
	void testGetRepoName() throws IOException {
		assertNotNull(app.getRepoName());
		assertEquals("ES-LETI-1Sem-2021-Grupo3",app.getRepoName());
	}
	
	@Test
	void testGetTags() throws IOException {	
		assertNotNull(app.getTags());
		assertFalse(app.getTags().isEmpty());
		HashMap<GHTag, String> tags_dates=app.getTags();
		String temp="";
		for (GHTag tag : tags_dates.keySet()) temp+=tag.getName() + "\n" + tags_dates.get(tag)+"\n";
		Assertions.assertTrue(temp.contains("v0.1.0"));
		Assertions.assertTrue(temp.contains("Sun Nov 21 23:36:16 WET 2021"));
		Assertions.assertTrue(temp.contains("v0.2.0"));
		Assertions.assertTrue(temp.contains("Tue Dec 07 23:42:08 WET 2021"));
		Assertions.assertTrue(temp.contains("v0.3.0"));
		Assertions.assertTrue(temp.contains("Wed Dec 08 00:06:50 WET 2021"));

		
	}

}

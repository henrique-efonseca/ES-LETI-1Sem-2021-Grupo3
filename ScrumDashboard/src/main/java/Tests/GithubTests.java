package Tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Backend.GitHubAPI;

class GithubTests {
	
	static GitHubAPI app;	
	static String s1 ="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";
	static String s2= "henrique-efonseca/ES-LETI-1Sem-2021-Grupo3";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		app= new GitHubAPI();
		app.setUp(s1,s2);
	}

	@Test
	void testCommits() throws IOException {
		assertNotNull(app.commits());
	}

	@Test
	void testGetREADME() throws IOException {
		assertNotNull(app.getREADME());
	}

	@Test
	void testGetRepoName() throws IOException {
		assertNotNull(app.getRepoName());
	}

}

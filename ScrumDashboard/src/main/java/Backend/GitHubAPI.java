package Backend;

import java.awt.EventQueue;
import java.beans.XMLDecoder;
import java.io.IOException;
import java.util.Base64;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;


import Frontend.GUI;

//"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"
//"henrique-efonseca/ES-LETI-1Sem-2021-Grupo3"

public class GitHubAPI {
//	private static final String authorization = "Bearer: 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";
//	private static final String baseUrl = "https://api.github.com/repos/henrique-efonseca/ES-LETI-1Sem-2021-Grupo3";
//	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static GitHub github;
	private static GHRepository repository;

	    
	  
	public void login(String authToken, String repositoryName) {
		try {
			this.github = new GitHubBuilder().withOAuthToken(authToken).build();
			this.repository =  github.getRepository(repositoryName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	public void convertToTxt() {
//		Document document = new Document("test.md");
//	}
	public String getReadMe() throws IOException {
		String readMe = repository.getReadme().getContent();
		return readMe;
	}
	

public static void main(String[] args) throws IOException {
	
	    }
	    }
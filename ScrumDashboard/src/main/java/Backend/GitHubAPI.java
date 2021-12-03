package Backend;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Base64;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;


import Frontend.GUI;



public class GitHubAPI {
	private static final String authorization = "Bearer: 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";
	private static final String baseUrl = "https://api.github.com/repos/henrique-efonseca/ES-LETI-1Sem-2021-Grupo3";
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static GitHub github;
	

	    
	  

public static void main(String[] args) throws IOException {
	
	github = new GitHubBuilder().withOAuthToken("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").build();
	GHRepository repository =  github.getRepository("henrique-efonseca/ES-LETI-1Sem-2021-Grupo3");
	System.out.println(repository.getFullName());
	System.out.println("meita: ");
	
	
	System.out.println(repository.getReadme().getEncodedContent());
	
	    	
	    }
	    }
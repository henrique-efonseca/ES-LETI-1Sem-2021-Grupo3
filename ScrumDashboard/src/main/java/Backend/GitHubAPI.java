package Backend;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Base64;
import java.util.Set;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;

import com.fasterxml.jackson.databind.ObjectMapper;


import Frontend.GUI;



	public class GitHubAPI {
		private static final String authorization = "Bearer: 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";
		private static final String baseUrl = "https://api.github.com/repos/henrique-efonseca/ES-LETI-1Sem-2021-Grupo3";
		private static final ObjectMapper objectMapper = new ObjectMapper();
		private static GHRepository repository; 
	
		private static GitHub github;
		
		
		public static void teste() throws IOException{
			Set<String> names = repository.getCollaboratorNames();
			PagedIterable<GHCommit> commits = repository.listCommits();
			for(String name : names) {		
				String s=name+"\n";
				for(GHCommit commit : commits){
					System.out.println(commit.getCommitter());
					if(commit.getAuthor().equals(s)) {
						System.out.println(commit.getCommitDate().toString());
						PagedIterable<GHCommitComment> comments=commit.listComments();
						for(GHCommitComment comment : comments) System.out.println(comment.getBody());
					}
				}
			}
			
		} 

		public static void main(String[] args) throws IOException {
		
			github = new GitHubBuilder().withOAuthToken("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").build();
			repository =  github.getRepository("henrique-efonseca/ES-LETI-1Sem-2021-Grupo3");
			//github = new GitHubBuilder().withOAuthToken("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").build();
			//GHRepository repository =  github.getRepository("pauletasm/TestePAULETA");
			System.out.println(repository.getFullName());			
			System.out.println(repository.getReadme().getEncodedContent());
			Set<String> names = repository.getCollaboratorNames();
			for(String s : names) System.out.println(s);
			
			String masterBranch = repository.getMasterBranch();
			GHBranch master = repository.getBranch(masterBranch);
	    	
	    }
	}
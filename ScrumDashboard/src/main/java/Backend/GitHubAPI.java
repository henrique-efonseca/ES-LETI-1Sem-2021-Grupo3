package Backend;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommit.ShortInfo;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.domain.Member;


	public class GitHubAPI {
		private GHRepository repository; 
		private  GitHub github;
		
		
		public String commits() throws IOException{
			
			HashMap<String, String> commit_map = new HashMap<String, String>();
			Set<GHUser> names = repository.getCollaborators();
			PagedIterable<GHCommit> commits = repository.listCommits();
			String s = "COMMITS \n\r";
			
			for(GHUser m : names) commit_map.put(m.getName(),m.getName()+"\n");
	    	
		
				for(GHCommit commit : commits){
					if(commit.getCommitter().getName() != null && !commit.getCommitter().getName().equals("GitHub Web Flow")){
							String c= "";
							c+=commit.getCommitDate().toString()+"\n";
							c+=commit.getCommitShortInfo().getMessage()+"\n";
						
							PagedIterable<GHBranch> branch= commit.listBranchesWhereHead();
							for(GHBranch b : branch){
							c+=b.getName()+"\n";
							} 
							commit_map.put(commit.getCommitter().getName(), commit_map.get(commit.getCommitter().getName())+c+"\n");
						
					}
				}
				for(GHUser m : names) s+=commit_map.get(m.getName())+"\n\r";
				
			return s;
		} 

		public void getActiveBranch() throws IOException{
			Map<String, GHBranch> map=	repository.getBranches();
			for (Entry<String, GHBranch> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey());
			}
		}	
		
		
		public String getREADME() throws IOException{
			return repository.getReadme().getContent();
		}
		
		public String getRepoName() throws IOException{
			return repository.getName();
		}
		
		public void setUp(String token, String repo) throws IOException{
			github = new GitHubBuilder().withOAuthToken(token).build();
			repository =  github.getRepository(repo);
		}
		
		public static void main(String[] args) throws IOException {
		
			GitHubAPI api= new GitHubAPI();
			api.setUp("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","henrique-efonseca/ES-LETI-1Sem-2021-Grupo3");

			System.out.println(api.getREADME());
			api.commits();
			
	    	
	    }
	}
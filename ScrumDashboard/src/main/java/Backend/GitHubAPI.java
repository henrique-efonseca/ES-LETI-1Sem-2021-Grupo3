package Backend;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommit.ShortInfo;
import org.kohsuke.github.GHCommitComment;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTag;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.domain.Member;
/**
 * GitHub API
 * 
 * @author Bernardo Bento
 * @author Ricardo Paulo
 * @author Henrique Fonseca
 * @version 1.0 
 * 
 */

	public class GitHubAPI {
		private GHRepository repository; 
		private  GitHub github;
		
		/**
		 * Return all commits made by person and in chronological order as a String
		 * @return
		 * All commits as a String
		 * @throws IOException
		 */
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

		/**
		 * Prints all active Branches
		 * @throws IOException
		 */
		public void getActiveBranch() throws IOException{
			Map<String, GHBranch> map=	repository.getBranches();
			for (Entry<String, GHBranch> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey());
			}
		}	
		
		/**
		 * returns repository README.md file from GitHub as a String
		 * 
		 * @return 
		 * README.md file as a String
		 * @throws IOException
		 */
		
		public String getREADME() throws IOException{
			return repository.getReadme().getContent();
		}
		
		/** 
		 * returns the Repository name as a String
		 * @return
		 * Repository name as a String
		 * @throws IOException
		 */
		public String getRepoName() throws IOException{
			return repository.getName();
		}
		
		/**
		 * Connects with a GitHub repository
		 * 
		 * @param token
		 * GitHub personal Token
		 * @param repo
		 * GitHub repository name 
		 * @throws IOException
		 */
		public void setUp(String token, String repo) throws IOException{
			github = new GitHubBuilder().withOAuthToken(token).build();
			repository =  github.getRepository(repo);
		}
		
		
		/**
		 * @return
		 * Returns a HashMap with every tag on the main branch and it's release date
		 *
		 */
		public HashMap<GHTag, String> getTags() throws IOException {
			HashMap<GHTag, String> tags_dates = new HashMap<GHTag, String>();		
			PagedIterable<GHTag> tags = repository.listTags();		
			for (GHTag tag : tags) tags_dates.put(tag, tag.getCommit().getCommitDate().toString());
			return tags_dates;
			
		
			
		}
		
	
	}
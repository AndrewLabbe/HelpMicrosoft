import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.*;
import java.util.Scanner;
import git.tools.client.GitSubprocessClient;

public class Main {

	public static void main(String[] args) {
		
		//asks user for target repo's root folder
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the path name to the target repo's root folder");
		//String repoPath = input.nextLine();
		
		//creates new gitSubproccessClient with target repo's root folder
		GitSubprocessClient gitSubprocessClient = new GitSubprocessClient("C:/Users/labbe/Documents/Eclipse Workspace/CSC_111/Monster Project");
		String gitInit = gitSubprocessClient.gitInit();
		
		//asks user for username and token
		System.out.println("Please enter your username and token");
		String user = input.nextLine();
		String token = input.nextLine();
		
		//creates new gitHubApiClient with respective username and token 
		GitHubApiClient gitHubApiClient = new GitHubApiClient(user, token);
		
		//asks user what they want repo name to be
		System.out.println("Please enter desired Repo name");
		String name = input.nextLine();
		
		//creates new repo with the above name parameter
		RequestParams requestParams = new RequestParams();
		requestParams.addParam("name", name);
		
		//asks if user wants descripton for repo
		System.out.println("Would you like to set a description for the repo?");
		String res = input.nextLine();
		if(res.toLowerCase().equals("yes")) {
			System.out.println("What would you like the description to be?");
			String des = input.nextLine();
			requestParams.addParam("description", des);
		}
		
		//asks if user wants repo to be private or public
		System.out.println("Would you like to make the repo private?");
		res = input.nextLine();
		if(res.toLowerCase().equals("yes")) {
			requestParams.addParam("private", true);
		}
		
		CreateRepoResponse createRepoResponse = gitHubApiClient.createRepo(requestParams);
		
		//gets repo's url
		GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo(user, name);
		String url = repoInfo.getJson().get("html_url").getAsString();
		
		//adds origin master, adds code, commits as "initial commit" and pushes it to master
		String gitRemoteAdd = gitSubprocessClient.gitRemoteAdd("origin", url);
		String gitAddAll = gitSubprocessClient.gitAddAll();
		String commit = gitSubprocessClient.gitCommit("initial commit");
		String push = gitSubprocessClient.gitPush("master");
		
		System.out.println("Success, your repo has been successfully created, url: " + url);
	}
	
}

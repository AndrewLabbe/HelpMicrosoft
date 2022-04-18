import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.*;
import java.util.Scanner;
import git.tools.client.GitSubprocessClient;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the path name to the target repo's root folder");
		String repoPath = input.nextLine();
		
		GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(repoPath);
		
		System.out.println("Please enter your username and token");
		String user = input.nextLine();
		String token = input.nextLine();
		
		GitHubApiClient gitHubApiClient = new GitHubApiClient(user, token);
		
		System.out.println("Please enter desired Repo name");
		String name = input.nextLine();
		RequestParams requestParams = new RequestParams();
		requestParams.addParam("name", name);

		CreateRepoResponse createRepoResponse = gitHubApiClient.createRepo(requestParams);
	}
	
}


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.*;
import java.util.Scanner;
import git.tools.client.GitSubprocessClient;


public class DrawingPanel extends JPanel {
	private JButton repo;
	private JTextField rootFolder;
	private JButton rootSubmit;
	private JTextField userin;
	private JTextField tokenin;
	private JButton apiClient;
	private JTextField repoNameIn;
	private JButton params;
	private JRadioButton discription;
	private JRadioButton noDiscription;
	private JTextField discript;
	private JRadioButton priv;
	private JRadioButton publ;
	private JButton getUrl;
	private JButton createRepo;
	
	
	JLabel url1 = new JLabel("");
	JLabel url2 = new JLabel("");
	
	String repoPath = "";
	String user = "";
	String token = "";
	String name = "";
	String des = "";
	String url = "";
	GitHubApiClient gitHubApiClient;
	GitSubprocessClient gitSubprocessClient;
	
	
	
    public DrawingPanel() {
        super();
        this.setLayout(new FlowLayout());
        this.setSize(1000, 800);
        this.setBackground(Color.white); 
        
        JLabel folderLabel = new JLabel("Please enter the path name to the target repo's root folder:");
        rootFolder = new JTextField(20);
        folderLabel.setLabelFor(rootFolder);
        rootFolder.setSize(50, 30);
        rootFolder.setLocation(100,30);
        
        rootSubmit = new JButton();
        rootSubmit.setOpaque(true);
        rootSubmit.setText("Submit");
        rootSubmit.setSize(120,120);
        rootSubmit.setLocation(445,350);
        
        rootSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		repoPath= rootFolder.getText();
        	}
        	
        });
        
        this.add(folderLabel);
        this.add(rootFolder);
        this.add(rootSubmit);
        
        repo = new JButton();
        repo.setOpaque(true);
        repo.setText("Add Repo");
        repo.setSize(120,120);
        repo.setLocation(445,350);
        repo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gitSubprocessClient = new GitSubprocessClient(repoPath);
        		String gitInit = gitSubprocessClient.gitInit();
        	}
        	
        });
       this.add(repo);
       
       JLabel userLabel = new JLabel("Please enter your username:");
       userin = new JTextField(20);
       folderLabel.setLabelFor(userin);
       userin.setSize(50, 30);
       userin.setLocation(100,30);
       JLabel tokenLabel = new JLabel("Please enter your token:");
       tokenin = new JTextField(20);
       folderLabel.setLabelFor(tokenin);
       tokenin.setSize(50, 30);
       tokenin.setLocation(100,30);
       
       apiClient = new JButton();
       apiClient.setOpaque(true);
       apiClient.setText("Submit");
       apiClient.setSize(120,120);
       apiClient.setLocation(445,350);
       
       
       apiClient.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		user = userin.getText();
       		token = tokenin.getText();
       		gitHubApiClient = new GitHubApiClient(user, token);
       	}
       	
       });
       this.add(userLabel);
       this.add(userin);
       this.add(tokenLabel);
       this.add(tokenin);
       this.add(apiClient);
       
       JLabel repoNameLabel = new JLabel("Please enter the path name to the target repo's root folder:");
       repoNameIn = new JTextField(20);
       folderLabel.setLabelFor(repoNameIn);
       repoNameIn.setSize(50, 30);
       repoNameIn.setLocation(100,30);
       
       params = new JButton();
       params.setOpaque(true);
       params.setText("Submit");
       params.setSize(120,120);
       params.setLocation(445,350);
       
       RequestParams requestParams = new RequestParams();
       params.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		name = repoNameIn.getText();
       		
    		requestParams.addParam("name", name);
    		
          
       	}
       	
       });
       
       this.add(repoNameLabel);
       this.add(repoNameIn);
       this.add(params);
       
       
       
       JLabel discriptionOptionLabel = new JLabel("Would you like to set a description for the repo?");
       
       discription = new JRadioButton();
       discription.setOpaque(true);
       discription.setText("Yes");
       discription.setSize(120,120);
       discription.setLocation(445,350);
       discription.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   
    	   }
    	   });
      
       noDiscription = new JRadioButton();
       noDiscription.setOpaque(true);
       noDiscription.setText("No");
       noDiscription.setSize(120,120);
       noDiscription.setLocation(445,350);
       noDiscription.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   
    	   }
    	   });
       discriptionOptionLabel.setLabelFor(discription);
       discriptionOptionLabel.setLabelFor(noDiscription);
       
       ButtonGroup discriptionGroup = new ButtonGroup();
       discriptionGroup.add(discription);
       discriptionGroup.add(noDiscription);
       
       
       
       
       this.add(discriptionOptionLabel);
       this.add(discription);
       this.add(noDiscription);
      
       
       JLabel discriptionLabel = new JLabel("What would you like the description to be?");
       discript = new JTextField(20);
       discriptionLabel.setLabelFor(discript);
       discript.setSize(50, 30);
       discript.setLocation(100,30);
      
       des = discript.getText();
       requestParams.addParam("description",des );
       this.add(discriptionLabel);
       this.add(discript);
           
       
       
       
       JLabel privpublic  = new JLabel("Would you like to make the repo private?");
       
       priv = new JRadioButton();
       priv.setOpaque(true);
       priv.setText("Yes");
       priv.setSize(120,120);
       priv.setLocation(445,350);
       priv.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   requestParams.addParam("private", true);
    	   }
    	   });
      
       publ = new JRadioButton();
       publ.setOpaque(true);
       publ.setText("No");
       publ.setSize(120,120);
       publ.setLocation(445,350);
       publ.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   
    	   }
    	   });
       privpublic.setLabelFor(priv);
       privpublic.setLabelFor(publ);
       
       ButtonGroup privpubGroup = new ButtonGroup();
       privpubGroup.add(priv);
       privpubGroup.add(publ);
       
       this.add(privpublic);
       this.add(priv);
       this.add(publ);
       
       createRepo = new JButton();
       createRepo.setOpaque(true);
       createRepo.setText("Creat Repo");
       createRepo.setSize(120,120);
       createRepo.setLocation(445,350);
       createRepo.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   CreateRepoResponse createRepoResponse = gitHubApiClient.createRepo(requestParams);
    		   GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo(user, name);
    		   url = repoInfo.getJson().get("html_url").getAsString();
    		   String gitRemoteAdd = gitSubprocessClient.gitRemoteAdd("origin", url);
    			String gitAddAll = gitSubprocessClient.gitAddAll();
    			String commit = gitSubprocessClient.gitCommit("initial commit");
    			String push = gitSubprocessClient.gitPush("master");
    	   }
    	   });
       
       this.add(createRepo);
       
       url1.setText("Success, your repo has been successfully created:");
       getUrl = new JButton();
       getUrl.setOpaque(true);
       getUrl.setText("Get url");
       getUrl.setSize(120,120);
       getUrl.setLocation(445,350);
       getUrl.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   url2.setText(url);
    	   }
    	   });
       this.add(url1);
       this.add(url2);
       this.add(getUrl);
      
       
    }
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D brush = (Graphics2D) g;
    	
        
    }
    
}
        

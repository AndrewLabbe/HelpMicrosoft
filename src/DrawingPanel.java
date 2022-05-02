
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
	private JTextField tokenD;
	private JButton apiClient;
	private JTextField repoNameIn;
	private JButton params;
	private JRadioButton discription, tokenYes;
	private JRadioButton noDiscription, tokenNo;
	private JTextField discript;
	private JButton disSubmit;
	private JRadioButton priv;
	private JRadioButton publ;
	private JButton getUrl;
	private JButton createRepo;
	private Sprite Qu;
	private Sprite Micro;
	private Sprite Disclamer;
	
	JLabel url1 = new JLabel("");
	JLabel url2 = new JLabel("");
	JLabel discriptionLabel = new JLabel("");
	JLabel folderLabel  = new JLabel("");
	JLabel userLabel  = new JLabel("");
	JLabel tokenLabel  = new JLabel("");
	JLabel repoNameLabel  = new JLabel("");
	JLabel discriptionOptionLabel  = new JLabel("");
	JLabel privpublic = new JLabel("");
	JLabel tokenDecision = new JLabel("");
	
	
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
        
        this.setSize(1000, 800);
        this.setBackground(Color.white); 
        
        Qu = new Sprite("./images/QU.png"); //works like any shape with location size exc...
		Qu.setLocation(100, 400);
		Micro = new Sprite("./images/micro.png");
		Micro.setLocation(500, 400);
		Disclamer = new Sprite("./images/Prototype.png");
		Disclamer.setHeight(150);
		Disclamer.setWidth(500);
		Disclamer.setLocation(250, 580);
		
	
        
        folderLabel.setText("Please enter the path name to the target repo's root folder:");
        rootFolder = new JTextField(20);
        folderLabel.setLabelFor(rootFolder);
        rootFolder.setSize(50, 30);
        rootFolder.setLocation(100,30);
        
        rootSubmit = new JButton();
        rootSubmit.setOpaque(true);
        rootSubmit.setText("Submit");
        rootSubmit.setSize(120,120);
        rootSubmit.setLocation(445,350);
        
        folderLabel.setVisible(true);
        rootFolder.setVisible(true);
        rootSubmit.setVisible(true);
        
        rootSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		repoPath= rootFolder.getText();
        		folderLabel.setVisible(false);
                rootFolder.setVisible(false);
                rootSubmit.setVisible(false);
                repo.setVisible(true);
        		
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
        repo.setVisible(false);
        repo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gitSubprocessClient = new GitSubprocessClient(repoPath);
        		String gitInit = gitSubprocessClient.gitInit();
        		repo.setVisible(false);
        		userLabel.setVisible(true);
        		userin.setVisible(true);
        		tokenLabel.setVisible(false);
        		apiClient.setVisible(true);
        	}
        	
        });
       this.add(repo);
       
       userLabel.setText("Please enter your username:");
       userin = new JTextField(20);
       folderLabel.setLabelFor(userin);
       userin.setSize(50, 30);
       userin.setLocation(100,30);
     
      
       apiClient = new JButton();
       apiClient.setOpaque(true);
       apiClient.setText("Submit");
       apiClient.setSize(120,120);
       apiClient.setLocation(445,350);
      
       userLabel.setVisible(false);
       userin.setVisible(false);
       tokenLabel.setVisible(false);
       apiClient.setVisible(false);
       
       
       
       apiClient.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		user = userin.getText(); 
       		gitHubApiClient = new GitHubApiClient(user, token);
       		repoNameLabel.setVisible(true);
            repoNameIn.setVisible(true);
            params.setVisible(true);
       	}
       });
       
       this.add(userLabel);
       this.add(userin);
       this.add(tokenLabel);
       this.add(apiClient);
       
       repoNameLabel.setText("Please enter desired name for repo:");
       repoNameIn = new JTextField(20);
       folderLabel.setLabelFor(repoNameIn);
       repoNameIn.setSize(50, 30);
       repoNameIn.setLocation(100,30);
       
       params = new JButton();
       params.setOpaque(true);
       params.setText("Submit");
       params.setSize(120,120);
       params.setLocation(445,350);
       repoNameLabel.setVisible(false);
       repoNameIn.setVisible(false);
       params.setVisible(false);
       
       RequestParams requestParams = new RequestParams();
       params.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		name = repoNameIn.getText();
    		requestParams.addParam("name", name);
    		discriptionOptionLabel.setVisible(true);
    	    discription.setVisible(true);
    	    noDiscription.setVisible(true);
       	}
       	
       });
       
       this.add(repoNameLabel);
       this.add(repoNameIn);
       this.add(params);
       
       
       
       discriptionOptionLabel.setText("Would you like to set a description for the repo?");
       
       discription = new JRadioButton();
       discription.setOpaque(true);
       discription.setText("Yes");
       discription.setSize(120,120);
       discription.setLocation(445,350);
       discription.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		discriptionOptionLabel.setVisible(false);
       	    discription.setVisible(false);
       	    noDiscription.setVisible(false);
    		discript.setVisible(true);
    		discriptionLabel.setVisible(true);
    		disSubmit.setVisible(true);
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
    		discriptionOptionLabel.setVisible(false);
       	    discription.setVisible(false);
       	    noDiscription.setVisible(false);
       	    privpublic.setVisible(true);
       	    priv.setVisible(true);
       	    publ.setVisible(true);
    		   
    	   }
    	   });
       discriptionOptionLabel.setVisible(false);
	   discription.setVisible(false);
	   noDiscription.setVisible(false);
	   
       discriptionOptionLabel.setLabelFor(discription);
       discriptionOptionLabel.setLabelFor(noDiscription);
       
       ButtonGroup discriptionGroup = new ButtonGroup();
       discriptionGroup.add(discription);
       discriptionGroup.add(noDiscription);
       
       this.add(discriptionOptionLabel);
       this.add(discription);
       this.add(noDiscription);
      
       
       discriptionLabel.setText("What would you like the description to be?");
       discript = new JTextField(20);
       discriptionLabel.setLabelFor(discript);
       discript.setSize(50, 30);
       discript.setLocation(100,30);
       discript.setVisible(false);
       discriptionLabel.setVisible(false);
      
       
       disSubmit = new JButton();
       disSubmit.setOpaque(true);
       disSubmit.setText("Submit");
       disSubmit.setSize(120,120);
       disSubmit.setLocation(445,350);
       disSubmit.setVisible(false);
       disSubmit.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   des = discript.getText();
    	       requestParams.addParam("description",des );
    	       discript.setVisible(false);
    	       discriptionLabel.setVisible(false);
    	       disSubmit.setVisible(false);
    	       privpublic.setVisible(true);
          	   priv.setVisible(true);
          	   publ.setVisible(true);
    	   }
       });
    	  
       this.add(discriptionLabel);
       this.add(discript);
       this.add(disSubmit);
           
       
       
       
       privpublic.setText("Would you like to make the repo private?");
       
       priv = new JRadioButton();
       priv.setOpaque(true);
       priv.setText("Yes");
       priv.setSize(120,120);
       priv.setLocation(445,350);
       privpublic.setVisible(false);
  	   priv.setVisible(false);
  	  
       priv.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   requestParams.addParam("private", true);
    		   //this code is not working either
    		   priv.setVisible(false);
    		   privpublic.setVisible(false);
    		   publ.setVisible(false);
    		   tokenYes.setVisible(true);
    		   tokenNo.setVisible(true);
    		   tokenDecision.setVisible(true);
    	
    	   }
    	   });
      
       publ = new JRadioButton();
       publ.setOpaque(true);
       publ.setText("No");
       publ.setSize(120,120);
       publ.setLocation(445,350);
       publ.setVisible(false);
  	   
       
       publ.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		  // createRepo.setVisible(true);
    		   //this code is not working properly
    		   priv.setVisible(false);
    		   privpublic.setVisible(false);
    		   publ.setVisible(false);
    		   tokenYes.setVisible(true);
    		   tokenNo.setVisible(true);
    		   tokenDecision.setVisible(true);
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
       
       
       tokenDecision.setText("Would you like to enter your token?");
       JRadioButton tokenYes = new JRadioButton();
       tokenYes.setOpaque(true);
       tokenYes.setText("Yes");
       tokenYes.setSize(120,120);
       tokenYes.setLocation(445,350);
       tokenDecision.setVisible(false);
       tokenYes.setVisible(false);
       tokenYes.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		tokenDecision.setVisible(false);
    		tokenYes.setVisible(false);
    		tokenNo.setVisible(false);
    		createRepo.setVisible(true);
    		//code to take in text from JText File
       	    //code to show the next create repo button
    	   }
    	   });
       
       JRadioButton tokenNo = new JRadioButton();
       tokenNo.setOpaque(true);
       tokenNo.setText("No");
       tokenNo.setSize(120,120);
       tokenNo.setLocation(445,350);
       tokenNo.setVisible(false);
       tokenNo.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Token will be read from token.txt file");
			tokenYes.setVisible(false);
			tokenDecision.setVisible(false);
			tokenNo.setVisible(false);
			createRepo.setVisible(true);
	    	   try{
					FileInputStream inputStream = new FileInputStream("token.txt");
					Scanner scanner = new Scanner(inputStream);
					token = scanner.nextLine();
					scanner.close();
				}catch (FileNotFoundException ex){
					System.out.println("Could not find file");
				}
		}
       });
       
       tokenDecision.setLabelFor(tokenNo);
       tokenDecision.setLabelFor(tokenYes);
       
       ButtonGroup token = new ButtonGroup();
       token.add(tokenNo);
       token.add(tokenYes);
       
       this.add(tokenYes);
       this.add(tokenNo);
       this.add(tokenDecision);
      
       
      /*
       if(tokenD.getText().toUpperCase().equals("Y")) {
    	   tokenin.setVisible(true);
           folderLabel.setLabelFor(tokenin);
           tokenin.setSize(50, 30);
           tokenin.setLocation(300,30);
           token = tokenin.getText();
       }else {
    	   System.out.println("Token will be read from token.txt file");
    	   try{
				FileInputStream inputStream = new FileInputStream("token.txt");
				Scanner scanner = new Scanner(inputStream);
				token = scanner.nextLine();
				scanner.close();
			}catch (FileNotFoundException ex){
				System.out.println("Could not find file");
			}
       }
       */
       
       createRepo = new JButton();
       createRepo.setOpaque(true);
       createRepo.setText("Creat Repo");
       createRepo.setSize(120,120);
       createRepo.setLocation(445,350);
       createRepo.setVisible(false);
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
    			createRepo.setVisible(false);
    			url1.setVisible(true);
    			getUrl.setVisible(true);
    	   }
    	   });
       
       this.add(createRepo);
       
       url1.setText("Success, your repo has been successfully created:");
       getUrl = new JButton();
       getUrl.setOpaque(true);
       getUrl.setText("Get url");
       getUrl.setSize(120,120);
       getUrl.setLocation(445,350);
       url1.setVisible(false);
       getUrl.setVisible(false);
       url2.setVisible(false);
	
       getUrl.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
    		   
    		   url2.setText(url);
    		   url2.setVisible(true);
    	   }
    	   });
       this.add(url1);
       this.add(url2);
       this.add(getUrl);
      
       
    }
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D brush = (Graphics2D) g;
    	Qu.paint(brush);
    	Micro.paint(brush);
    	Disclamer.paint(brush);
    }
}
        

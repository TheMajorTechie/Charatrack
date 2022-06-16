package charatrack;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Charatrack extends JFrame implements ActionListener {
	
	public static final String baseDir = getProgramPath() + "\\CharaTrack\\";
	public static String workingDir = baseDir;
	
	static JTextField charNameField;
	static JTextArea charInventory;
	static JTextArea charLocHistory;
	static JTextArea charKnowHistory;
	private JButton save;
	private JButton load;
	private JButton about;
	static boolean charExists;
	static String CharaVer = "2022156"; 
	
	public Charatrack() {
		
	//build main window
		super("Charatrack " + CharaVer);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainWindow = new JPanel();
		this.setContentPane(mainWindow);
		GridBagLayout charaWindow = new GridBagLayout();
		GridBagConstraints gridConstraint = new GridBagConstraints();
		mainWindow.setLayout(charaWindow);
		
		JPanel mainPanel = new JPanel();
		GridBagLayout mainGrid = new GridBagLayout();
		gridConstraint.gridwidth = 6;
		mainPanel.setLayout(mainGrid);
		mainWindow.add(mainPanel, gridConstraint);
		
	//create app label
		JLabel appLabel = new JLabel("Charatrack Character Tracker, " + CharaVer);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		mainPanel.add(appLabel, gridConstraint);
		
	//create character name box 
		charNameField = new JTextField("Character Name",25);
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		mainPanel.add(charNameField, gridConstraint);
		
	//create menu panel
		JPanel menuPanel = new JPanel();
		GridBagLayout menuGrid = new GridBagLayout();
		menuPanel.setLayout(menuGrid);
		gridConstraint.gridx = 4;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		
		//make buttons
		save = new JButton("Save");
		save.addActionListener(this);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		menuPanel.add(save, gridConstraint);
		
		load = new JButton("Load");
		load.addActionListener(this);
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		menuPanel.add(load, gridConstraint);
		
		about = new JButton("About");
		about.addActionListener(this);
		gridConstraint.gridx = 4;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		menuPanel.add(about, gridConstraint);
		
		mainPanel.add(menuPanel, gridConstraint);
		
	//create labels
		JLabel invLabel = new JLabel("Inventory");
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 2;
		mainPanel.add(invLabel, gridConstraint);
		
		JLabel locLabel = new JLabel("Location History");
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 2;
		mainPanel.add(locLabel, gridConstraint);
		
		JLabel memLabel = new JLabel("Knowledge History");
		gridConstraint.gridx = 4;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 2;
		mainPanel.add(memLabel, gridConstraint);
	
	//create character inventory panel
		
		charInventory = new JTextArea("Character Inventory", 25,15);
		charInventory.setLineWrap(false);
		
		JScrollPane charInvBox = new JScrollPane(charInventory);
		charInvBox.setPreferredSize(new Dimension(275,300));
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 2;
		gridConstraint.gridwidth = 2;
		mainPanel.add(charInvBox, gridConstraint);
		
	//create character location history panel
		
		charLocHistory = new JTextArea("Character Location History", 25,15);
		charLocHistory.setLineWrap(false);
		
		JScrollPane charLocHistBox = new JScrollPane(charLocHistory);
		charLocHistBox.setPreferredSize(new Dimension(275,300));
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 2;
		gridConstraint.gridwidth = 2;
		mainPanel.add(charLocHistBox, gridConstraint);
		
	//create character knowledge history panel
			
		charKnowHistory = new JTextArea("Character Knowledge History", 25,15);
		charKnowHistory.setLineWrap(false);
		JScrollPane charKnowHistBox = new JScrollPane(charKnowHistory);
		charKnowHistBox.setPreferredSize(new Dimension(275,300));
		gridConstraint.gridx = 4;
		gridConstraint.gridy = 2;
		gridConstraint.gridwidth = 2;
		
		mainPanel.add(charKnowHistBox, gridConstraint);
		
		this.pack();
	}
	
	public static void main(String[] args) throws IOException {
		Charatrack app = new Charatrack();
		setupDir();
		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == save) {
			System.out.println("Character name is " + "\"" + charNameField.getText() + "\"");
			System.out.println("\"" + charNameField.getText() + "'s" + "\" " + "inventory: " + charInventory.getText());
			System.out.println("\"" + charNameField.getText() + "'s" + "\" " + "location history: " + charLocHistory.getText());
			System.out.println("\"" + charNameField.getText() + "'s" + "\" " + "knowledge history: " + charKnowHistory.getText());
			
			Character Char = new Character(charNameField.getText());
			Char.setInventory(charInventory.getText());
			Char.setLocHist(charLocHistory.getText());
			Char.setMemory(charKnowHistory.getText());
			
			workingDir = workingDir + charNameField.getText();	//change working directory to the character directory
			
			try {
				setupCharDir();
			} catch (IOException e1) {
				System.out.println("Unable to generate new directory!");
				e1.printStackTrace();
			}
			
			workingDir = baseDir;	//makes sure to reset the base directory because holy frick not doing this starts stacking stuff bad
			
			Char.writeToFiles();
			//save protection to keep things from being overwritten on accident
//			if(charExists = false) {
//				Char.writeToFiles();
//				System.out.println("Files saved");
//			}
//			else {
//				System.out.println("No files saved");
//			}
		}
		
		if(e.getSource() == load) {
			LoadWindow load = new LoadWindow();
			load.setVisible(true);
		}
		if(e.getSource() == about) {
			AboutWindow about = new AboutWindow();
			about.setVisible(true);
		}
	}
	
	public static String getProgramPath() {
		String programDir = System.getProperty("user.dir");
		return programDir;
	}
	
	public static void setupDir() throws IOException {	
		
		System.out.println("\"" + workingDir + "\" " + "is the program's current working directory.");
		
		File tempDirectory = new File(workingDir);
		if( !tempDirectory.exists() && !tempDirectory.isDirectory() ) { //run if the directory doesnt exist
			System.out.println("Directory does not exist. Making directory...");
			tempDirectory.mkdir();
		}	
		
	}
	
	public static void setupCharDir() throws IOException {	
		
		System.out.println("\"" + workingDir + "\" " + "is the program's current working directory.");
		
		File tempDirectory = new File(workingDir);
		if( !tempDirectory.exists() && !tempDirectory.isDirectory() ) { //run if the directory doesnt exist
			System.out.println("Directory does not exist. Making directory...");
			tempDirectory.mkdir();
		}	
		else {
			System.out.println("Directory already exists. Work at your own peril!");
//			WarningWindow warning = new WarningWindow();
//			warning.setVisible(true);
		}
		
	}

	public static void loadCharacter() {
		// TODO Auto-generated method stub
		workingDir = workingDir + charNameField.getText();
		System.out.println("\"" + workingDir + "\" " + "is the program's current working directory.");
		
		File tempDirectory = new File(workingDir);
		if( !tempDirectory.exists() && !tempDirectory.isDirectory() ) { 
			//do nothing
		}	
		else {
			System.out.println("Character found! Now loading data...");
			Character Char = new Character(charNameField.getText());
			Char.readFromFiles();
			charInventory.setText(Char.getInventory());
			charLocHistory.setText(Char.getLocHist());
			charKnowHistory.setText(Char.getMemory());
			workingDir = baseDir;	//reset working directory again
		}
	}

}

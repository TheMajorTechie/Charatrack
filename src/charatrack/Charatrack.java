package charatrack;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

@SuppressWarnings("serial")
public class Charatrack extends JFrame implements ActionListener {
	
	//saves the current path of the program in memory and appends on a program data directory to make Charatrack portable.
	public static final String baseDir = getProgramPath() + "\\CharaTrack\\";
	public static String workingDir = baseDir;
	
	static JTextField charNameField;
	static JTextArea charInventory;
	static JTextArea charLocHistory;
	static JTextArea charKnowHistory;
	private JButton save;
	private JButton load;
	private JButton about;
	static String CharaVer = "2022616"; 	//because version numbering systems are confusing. let's just use the date of compilation.
	
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
		
		//make buttons that go on the panel
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
		
	//create labels for the three panels
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
	
	/**
	 * puny main method does one thing and one thing only
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Charatrack app = new Charatrack();
		setupDir();
		app.setVisible(true);
	}
	
	/**
	 * Small helper method that just grabs the current path of the program.
	 * @return
	 */
	public static String getProgramPath() {
		String programDir = System.getProperty("user.dir");
		return programDir;
	}
	
	/**
	 * Another helper method that creates the associated CharaTrack directory 
	 * @throws IOException
	 */
	public static void setupDir() throws IOException {		
		System.out.println("\"" + workingDir + "\" " + "is the program's current working directory.");
		
		File tempDirectory = new File(workingDir);
		if( !tempDirectory.exists() && !tempDirectory.isDirectory() ) { //run if the directory doesnt exist
			System.out.println("Directory does not exist. Making directory...");
			tempDirectory.mkdir();
		}	
		
	}
	
	/**
	 * Yet another helper method that does the work in making a character's directory. Probably can combine this in with the plain
	 * setupDir() method but i'm keeping it separate since i plan on having an overwrite file dialogue box open if a character already exists. 
	 * @throws IOException
	 */
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

	/**
	 * calls the main logic for loading in a character.
	 */
	public static void loadCharacter() {
		workingDir = workingDir + charNameField.getText();												//set working directory to whatever the heck LoadWindow has set charNameField to
		System.out.println("\"" + workingDir + "\" " + "is the program's current working directory.");
		
		File tempDirectory = new File(workingDir);
		if( !tempDirectory.exists() && !tempDirectory.isDirectory() ) { 
			//do nothing. will probably eventually show a dialogue box telling the user that the character doesn't exist.
			workingDir = baseDir;	//reset working directory or else the program gets stuck in a loop and is unable to load new characters anymore
		}	
		else {
			System.out.println("Character found! Now loading data...");
			Character Char = new Character(charNameField.getText());
			
			//invokes the logic to start reading stuff in. also sets the text boxes to whatever has been loaded.
			Char.readFromFiles();
			charInventory.setText(Char.getInventory());
			charLocHistory.setText(Char.getLocHist());
			charKnowHistory.setText(Char.getMemory());
			workingDir = baseDir;	//reset working directory again in case if loading/saving is needed to be done again.
		}
	}
	
	/**
	 * the big brain logic of the program. works with the text fields and buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == save) {
			//debug code that'll dump text into the console. i have yet to set up any JUnit tests.
//			System.out.println("Character name is " + "\"" + charNameField.getText() + "\"");
//			System.out.println("\"" + charNameField.getText() + "'s" + "\" " + "inventory: " + charInventory.getText());
//			System.out.println("\"" + charNameField.getText() + "'s" + "\" " + "location history: " + charLocHistory.getText());
//			System.out.println("\"" + charNameField.getText() + "'s" + "\" " + "knowledge history: " + charKnowHistory.getText());
			
			//set up a new character in preparation for saving. autosaving will eventually be a thing btw.
			Character Char = new Character(charNameField.getText());
			Char.setInventory(charInventory.getText());
			Char.setLocHist(charLocHistory.getText());
			Char.setMemory(charKnowHistory.getText());
			
			workingDir = workingDir + charNameField.getText();	//change working directory to the character directory
			
			//try to create a new directory for the character to be saved in.
			try {
				setupCharDir();
			} catch (IOException e1) {
				System.out.println("Unable to generate new directory!");
				e1.printStackTrace();
			}
			
			workingDir = baseDir;	//makes sure to reset the base directory because holy frick not doing this starts stacking stuff bad
			
			Char.writeToFiles();
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

}

package charatrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Character {
	private String name;
	private String charDirectory;
	private String locationHistory;
	private String inventory; 
	private String memories;
	
	/**
	 * Constructor to create a new character in memory.
	 * 
	 * This method uses the name of the character to create a "base directory" for files 
	 * to be saved and loaded from.
	 * @param name
	 */
	public Character(String name) {
		this.name = name;
		this.charDirectory = Charatrack.baseDir + name;
	}
	
	//getters and setters for different aspects of a character. utilized in saving and
	//restoring characters to and from files.
	public String getName() {
		return this.name;
	}
	
	public void setLocHist(String location) {
		this.locationHistory = location;
	}
	
	public String getLocHist() {
		return this.locationHistory;
	}
	
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
	public String getInventory() {
		return this.inventory;
	}
	
	public void setMemory(String memories) {
		this.memories = memories;
	}
	
	public String getMemory() {
		return this.memories;
	}
	
	/**
	 * Writes a character ID to file.
	 * @return true if executed successfully
	 */
	public void writeToFiles() {
		//write character name and directory first to a separate "ID" file.
		try {
			
			File characterDirectory = new File(charDirectory);
			if( !characterDirectory.exists() && !characterDirectory.isDirectory() ) {	//run if the directory doesnt exist
				System.out.println("Directory does not exist. Making directory...");	//debug message sent to console. may eventually have a popup window.
				characterDirectory.mkdir();
			}	
			
			/*generate the character ID file. though it is unused in loading, it contains the name and base directory of a character to make sharing characters easier
			 * in the case where someone might want to send a character folder over the web. you never know if those folder names are gonna stay the same or not. :V
			 * 
			 * utilize PrintWriter to... uh, print data stored in character variables to their respective files.
			 */
			
			PrintWriter charID = new PrintWriter(new FileWriter(charDirectory + "//" + name + ".charID"));
			charID.print(name + "\n" + charDirectory);
			charID.close();
			
			PrintWriter charLocHist = new PrintWriter(new FileWriter(charDirectory + "//" + name + "Locations" + ".txt"));
			charLocHist.print(this.locationHistory);
			charLocHist.close();
			
			PrintWriter charInventory = new PrintWriter(new FileWriter(charDirectory + "//" + name + "Inventory" + ".txt"));
			charInventory.print(this.inventory);
			charInventory.close();
			
			PrintWriter charMemory = new PrintWriter(new FileWriter(charDirectory + "//" + name + "Memory" + ".txt"));
			charMemory.print(this.memories);
			charMemory.close();			
			
		}
		catch (IOException e) {
			System.out.println("Error in writing character ID information: " + e);
		}
	}
	
	/**
	 * A small caller method that sets up and invokes the larger readFile method with the relevant parameters.
	 */
	public void readFromFiles() {
		
		try {
			readFile("Locations");
			readFile("Inventory");
			readFile("Memory");
		}
		
		catch (Exception e) {
			System.out.println("Error reading file!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Main logic for reading files, now using Scanner instead of the convoluted mess of spaghetti code that was using Files.readAllLines to stuff
	 * information into their strings one line at a time! Code looks a lot cleaner now and I'm happy about that.
	 * @param item
	 * @throws FileNotFoundException
	 */
	public void readFile(String item) throws FileNotFoundException {
		String loadedMemory = "";																			//set up a temporary string to store data
		Scanner loadedFile = new Scanner(new File(charDirectory + "//" + name + item + ".txt"));			//create a new Scanner using the parameters passed
		loadedFile.useDelimiter("\\Z");																		//make SURE it reads 'til the end
		loadedMemory += loadedFile.next();
		
		//directs the data from the loadedMemory var to their relevant places.
		if(item.equals("Locations"))																		
			this.locationHistory = loadedMemory;
		if(item.equals("Inventory"))
			this.inventory = loadedMemory;
		if(item.equals("Memory"))
			this.memories = loadedMemory;
		
		loadedFile.close();
	}
}

package charatrack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Character {
	private String name;
	private String charDirectory;
	
	private String locationHistory;
	private Stack<String> tasks;
	private String inventory; 
	private String memories;
	private ArrayList<String> stats;
	
	public Character(String name) {
		this.name = name;
		this.charDirectory = Charatrack.baseDir + name;
	}
	
	public Character(String name, String locationHistory, String inventory, String memories) {
		//constructs a character using data loaded from files, effectively loading the character in
	}
	
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
	public boolean writeToFiles() {
		//write character name and directory first to a separate "ID" file.
		try {
			
			File characterDirectory = new File(charDirectory);
			if( !characterDirectory.exists() && !characterDirectory.isDirectory() ) { //run if the directory doesnt exist
				System.out.println("Directory does not exist. Making directory...");
				characterDirectory.mkdir();
			}	
			
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
			return false;
		}
		return true;
	}
	
	public boolean readFromFiles() {
			
		try {
			//load location history
			String loadedLocations = "";
			List<String> locFile = 
					Files.readAllLines(Paths.get(charDirectory + "//" + name + "Locations" + ".txt"));
			int readIterations = 1;
			for(String line:locFile) {
				loadedLocations += line;
				if(readIterations != locFile.size()) {
					loadedLocations += "\n";
				}
				readIterations++;
			}
			this.locationHistory = loadedLocations;
			
			//load inventory
			String loadedInventory = "";
			List<String> invFile = 
					Files.readAllLines(Paths.get(charDirectory + "//" + name + "Inventory" + ".txt"));
			readIterations = 1;
			for(String line:invFile) {
				loadedInventory += line;
				if(readIterations != invFile.size()) {
					loadedInventory += "\n";
				}
				readIterations++;
			}
			this.inventory = loadedInventory;
			
			//load memory
			String loadedMemory = "";
			List<String> memFile = 
					Files.readAllLines(Paths.get(charDirectory + "//" + name + "Memory" + ".txt"));
			readIterations = 1;
			for(String line:memFile) {
				loadedMemory += line;
				if(readIterations != memFile.size()) {
					loadedMemory += "\n";
				}
				readIterations++;
			}
			this.memories = loadedMemory;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return true;
	}
	
	public void saveCharData() {
		//use ObjectOutputStream to write raw data. Default option.
	}
}

package charatrack;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Charatrack extends JFrame implements ActionListener {
	
	private JTextField charNameField;
	private JTextArea charPhysDesc;
	private JTextArea charInventory;
	private JButton save;
	private JButton load;
	private JButton about;
	private JButton addItem;
	
	public Charatrack() {
		
	//build main window
		super("Charatrack");
		setResizable(true);
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
		
		
	//create menu panel ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		JPanel menuPanel = new JPanel();
		GridBagLayout menuGrid = new GridBagLayout();
		menuPanel.setLayout(menuGrid);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 1;
		
		//make buttons
		save = new JButton("Save");
		save.addActionListener(this);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		menuPanel.add(save, gridConstraint);
		
		load = new JButton("Load");
		load.addActionListener(this);
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 0;
		menuPanel.add(load, gridConstraint);
		
		about = new JButton("About");
		about.addActionListener(this);
		gridConstraint.gridx = 2;
		gridConstraint.gridy = 0;
		menuPanel.add(about, gridConstraint);
		
		mainPanel.add(menuPanel, gridConstraint);
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	//create character name box ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		charNameField = new JTextField("Character Name (hit enter to save)",25);
		charNameField.addActionListener(this);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 3;
		mainPanel.add(charNameField, gridConstraint);
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	//create character physical description field~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		charPhysDesc = new JTextArea("Character Physical Description",25,50);
		charPhysDesc.setLineWrap(true);
		charPhysDesc.setWrapStyleWord(true);
	//now make it scrollable
			JScrollPane physDescBox = new JScrollPane(charPhysDesc);
			physDescBox.setPreferredSize(new Dimension(275,300));
			gridConstraint.gridx = 0;
			gridConstraint.gridy = 2;
			gridConstraint.gridwidth = 3;
			
			mainPanel.add(physDescBox, gridConstraint);
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		JPanel imagePanel = new JPanel();
		GridBagLayout imageGrid = new GridBagLayout();
		imagePanel.setLayout(imageGrid);
		
		JLabel imageLabelTemp = new JLabel("Placeholder image label (replace with image)");
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		imagePanel.add(imageLabelTemp, gridConstraint);
		
		gridConstraint.gridx = 4;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 1;
		
		mainPanel.add(imagePanel, gridConstraint);
		
		
		
	//create character inventory panel
		JPanel invPanel = new JPanel();
		GridBagLayout invGrid = new GridBagLayout();
		invPanel.setLayout(invGrid);
		
		
		
			JLabel itemLabel = new JLabel("         Item         ");
			gridConstraint.gridx = 0;
			gridConstraint.gridy = 0;
			invPanel.add(itemLabel, gridConstraint);
			
			JLabel itemCountLabel = new JLabel("         Count         ");
			gridConstraint.gridx = 1;
			gridConstraint.gridy = 0;
			invPanel.add(itemCountLabel, gridConstraint);
			
			JLabel itemNotesLabel = new JLabel("         Notes         ");
			gridConstraint.gridx = 2;
			gridConstraint.gridy = 0;
			invPanel.add(itemNotesLabel, gridConstraint);
			
			JTextField item = new JTextField("test item",10);
			gridConstraint.gridx = 0;
			gridConstraint.gridy = 1;
			gridConstraint.gridwidth = 1;
			invPanel.add(item, gridConstraint);
			
			JTextField itemDesc = new JTextField("test item description",10);
			gridConstraint.gridx = 1;
			gridConstraint.gridy = 1;
			gridConstraint.gridwidth = 1;
			invPanel.add(itemDesc, gridConstraint);
			
			JTextField itemNotes = new JTextField("test item notes",10);
			gridConstraint.gridx = 2;
			gridConstraint.gridy = 1;
			gridConstraint.gridwidth = 1;
			invPanel.add(itemNotes, gridConstraint);
			
			addItem = new JButton("Add item to inventory");
			gridConstraint.gridx = 0;
			gridConstraint.gridy = 2;
			gridConstraint.gridwidth = 1;
			addItem.addActionListener(this);
			invPanel.add(addItem, gridConstraint);
			
			JButton removeItem = new JButton("Remove item from inventory");
			gridConstraint.gridx = 1;
			gridConstraint.gridy = 2;
			gridConstraint.gridwidth = 1;
			invPanel.add(removeItem, gridConstraint);
			
			JButton editItem = new JButton("Edit item");
			gridConstraint.gridx = 2;
			gridConstraint.gridy = 2;
			gridConstraint.gridwidth = 1;
			invPanel.add(editItem, gridConstraint);
			
		gridConstraint.gridx = 4;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 1;
		
		mainPanel.add(invPanel, gridConstraint);
		
		this.pack();
	}
	
	public static void main(String[] args) {
		Charatrack app = new Charatrack();
		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == charNameField) {
			System.out.println("Character name is " + "\"" + charNameField.getText() + "\"");
		}
		
		if(e.getSource() == save) {
			System.out.println("Character name is " + "\"" + charNameField.getText() + "\"");
			System.out.println("Character physical description is " + "\"" + charPhysDesc.getText() + "\"");
		}
		if(e.getSource() == load) {
			
		}
		if(e.getSource() == addItem) {

		}
	}

}

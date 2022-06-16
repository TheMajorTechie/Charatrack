package charatrack;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoadWindow extends JFrame implements ActionListener {
	
	private JButton loadButton;
	private JTextField loadCharNameField;

	/**
	 * This window has literally one purpose. ask for a name to load in, take the input, and yeet it back over to the main method.
	 */
	public LoadWindow() {
		super("Load character");
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel loadWindow = new JPanel();
		this.setContentPane(loadWindow);
		GridBagLayout charaWindow = new GridBagLayout();
		GridBagConstraints gridConstraint = new GridBagConstraints();
		loadWindow.setLayout(charaWindow);
		
		JPanel mainPanel = new JPanel();
		GridBagLayout mainGrid = new GridBagLayout();
		gridConstraint.gridwidth = 6;
		mainPanel.setLayout(mainGrid);
		mainPanel.setSize(new Dimension(250,250));
		
		JLabel appLabel = new JLabel("Please enter a character name to load.");
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		mainPanel.add(appLabel, gridConstraint);
		
		loadCharNameField = new JTextField("Character Name",25);
		loadCharNameField.addActionListener(this);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 2;
		mainPanel.add(loadCharNameField, gridConstraint);

		loadButton = new JButton("Load");
		loadButton.addActionListener(this);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 2;
		gridConstraint.gridwidth = 1;
		mainPanel.add(loadButton, gridConstraint);
		
		loadWindow.add(mainPanel);
		
		this.pack();
		
	}

	/**
	 * Give the user two ways to send text back over for loading.
	 * Invokes the loadCharacter method back in the main class because that way I don't have to do any fancy
	 * logic to have the main class wait for input to finish.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loadCharNameField) {
			Charatrack.charNameField.setText(loadCharNameField.getText());
			Charatrack.loadCharacter();
			dispose();
		}
		
		if(e.getSource() == loadButton) {
			Charatrack.charNameField.setText(loadCharNameField.getText());
			Charatrack.loadCharacter();
			dispose();
		}
	}
}

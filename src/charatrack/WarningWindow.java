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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WarningWindow extends JFrame implements ActionListener {
	
	private JButton yesSave;
	private JButton noSave;

	public WarningWindow() {
		super("Charatrack: about");
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel aboutWindow = new JPanel();
		this.setContentPane(aboutWindow);
		GridBagLayout charaWindow = new GridBagLayout();
		GridBagConstraints gridConstraint = new GridBagConstraints();
		aboutWindow.setLayout(charaWindow);
		
		JPanel mainPanel = new JPanel();
		GridBagLayout mainGrid = new GridBagLayout();
		gridConstraint.gridwidth = 6;
		mainPanel.setLayout(mainGrid);
		mainPanel.setSize(new Dimension(250,250));
		
		JLabel appLabel = new JLabel("This character already exists! Are you sure you want to save?");
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 0;
		gridConstraint.gridwidth = 2;
		mainPanel.add(appLabel, gridConstraint);

		yesSave = new JButton("Yes");
		yesSave.addActionListener(this);
		gridConstraint.gridx = 0;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 1;
		mainPanel.add(yesSave, gridConstraint);
		
		noSave = new JButton("No");
		yesSave.addActionListener(this);
		gridConstraint.gridx = 1;
		gridConstraint.gridy = 1;
		gridConstraint.gridwidth = 1;
		mainPanel.add(noSave, gridConstraint);
		
		aboutWindow.add(mainPanel, gridConstraint);
		this.pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == yesSave) {
			Charatrack.charExists = false;
			dispose();
		}
		if(e.getSource() == noSave) {
			Charatrack.charExists = true;
			dispose();
		}
	}
}

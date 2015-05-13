package edu.westga.nim.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.westga.nim.model.Game;
import edu.westga.nim.model.Player;

/**
 * Defines the panel in which the user sets the size of the pile
 * and selects which Player plays first.
 * 
 * 
 * @author CS 6910 MARINA USHAKOVA
 * @version Summer 2014
 */
public class NewGamePanel extends JPanel {

	private static final Integer[] INITIAL_PILE_SIZES = {21, 16, 9};
	
	private static final long serialVersionUID = 1L;
	
	private JRadioButton humanPlayerButton;
	private JRadioButton computerPlayerButton;
	private JRadioButton randomPlayerButton;
	
	private JComboBox<Integer> pileSizeComboBox;
	
	private Player theHuman;
	private Player theComputer;
	private Gui theGui;
	private Game theGame;
	private ButtonGroup buttons;

	/**
	 * Creates a new NewGamePanel.
	 * 
	 * @param gui	the Gui object from which this panel gets its data
	 * 
	 * @requires 	gui != null
	 */
	public NewGamePanel(Gui gui) {
		if (gui == null) {
			throw new IllegalArgumentException("Null gui");
		}
		this.theGui = gui;
		this.theGame = this.theGui.getGame();
		this.theHuman = this.theGame.humanPlayer();
		this.theComputer = this.theGame.computerPlayer();
		
		
		this.buildPanel();
	}
	
	/** 
	 * Sets whether or not this panel and its components are enabled.
	 * 
	 * @param  enabled true if this component should be enabled, false otherwise	
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 * 
	 * @ensures enabled() == enabled &&		<br />
	 * 			for each Component c in getComponents(), c.enabled() == enabled
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		for (Component aComponent: this.getComponents()) {
			aComponent.setEnabled(!aComponent.isEnabled());
		}
		
		this.buttons.clearSelection();
	}
	
	
	//***********************Private helper methods****************************
	
	/*
	 * Added components to the NewGamePanel
	 */
	private void buildPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Start new game"));	
		
		this.addPileSizeComboBox();
		this.add(Box.createHorizontalStrut(25));
		this.addPlayerButtons();
	}

	/*
	 * Creates and addes comboBox component that allows to choose pile size
	 */
	private void addPileSizeComboBox() {
					
		JLabel pileSizeLabel = new JLabel("Initial pile size:");
		this.add(pileSizeLabel);
		
		this.pileSizeComboBox = new JComboBox<Integer>(INITIAL_PILE_SIZES);
		this.pileSizeComboBox.setEditable(false);
		this.pileSizeComboBox.addActionListener(new PileSizeListener());
		this.add(this.pileSizeComboBox);
	}

	/*
	 * Creates radioButtons, adds them to the group and then to the panel
	 */
	private void addPlayerButtons() {
		JLabel firstPlayerLabel = new JLabel("Who plays first?");
		this.add(firstPlayerLabel);
		
		this.humanPlayerButton = new JRadioButton("Human");	
		this.humanPlayerButton.addActionListener(new HumanFirstListener());
		
		this.computerPlayerButton = new JRadioButton("Computer");
		this.computerPlayerButton.addActionListener(new ComputerFirstListener());
		
		this.randomPlayerButton = new JRadioButton("Random player");
		this.randomPlayerButton.addActionListener(new RandomFirstListener());
		
		this.buttons = new ButtonGroup();
		buttons.add(this.humanPlayerButton);
		buttons.add(this.computerPlayerButton);
		buttons.add(this.randomPlayerButton);			
		
		this.add(this.humanPlayerButton);
		this.add(this.computerPlayerButton);
		this.add(this.randomPlayerButton);
	}
	
	
	
//****************************Private inner classes**********************************
	
	/* 
	 * Defines the listener for computerPlayerButton.
	 */		
	private class ComputerFirstListener implements ActionListener {
		
		/* 
		 * Enables the ComputerPlayerPanel and starts a new game. 
		 * Event handler for a click in the computerPlayerButton.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
				
			theGui.getComputerPlayerPanel().setEnabled(true);
			theGui.getHumanPlayerPanel().setEnabled(true);
			setEnabled(false);
			theGame.startNewGame(theComputer, theHuman, 
					(int) pileSizeComboBox.getSelectedItem());
			theComputer.setPileForThisTurn(theGame.pile());
			theComputer.setNumberSticksToTake();
			theGame.play();
		}
	}

	
	/* 
	 * Defines the listener for humanPlayerButton.
	 */	
	private class HumanFirstListener implements ActionListener {

		/* 
		 * Enables the HumanPlayerPanel and starts a new game. 
		 * Event handler for a click in the humanPlayerButton.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			NewGamePanel.this.theGui.getHumanPlayerPanel().setEnabled(true);
			NewGamePanel.this.theGui.getComputerPlayerPanel().setEnabled(true);
			setEnabled(false);
			theGame.startNewGame(theHuman, theComputer, 
					(int)pileSizeComboBox.getSelectedItem());
		}
	}
	
	
	/* 
	 * Defines the listener for randomPlayerButton.
	 */	
	private class RandomFirstListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Random generator = new Random();
			boolean computerFirst = (generator.nextInt(2) == 0);
			
			NewGamePanel.this.theGui.getHumanPlayerPanel().setEnabled(true);
			NewGamePanel.this.theGui.getComputerPlayerPanel().setEnabled(true);
			setEnabled(false);
			
			if (computerFirst) {
				theGame.startNewGame(
								theComputer, theHuman, 
								(int)pileSizeComboBox.getSelectedItem());
				theComputer.setPileForThisTurn(theGame.pile());
				theComputer.setNumberSticksToTake();
				theGame.play();
			} else {
				theGame.startNewGame(
								theHuman, theComputer, 
								(int)pileSizeComboBox.getSelectedItem());
			}
		}

	}
	
	/* 
	 * Defines the listener for pileSizeComboBox.
	 */	
	private class PileSizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theGame.setPileSize((int)pileSizeComboBox.getSelectedItem());

		}

	}
	
}


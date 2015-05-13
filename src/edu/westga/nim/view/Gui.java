package edu.westga.nim.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.westga.nim.model.Game;

/**
 * Defines the GUI for the 1-pile Nim game.
 *  
 * @author CS 6910 MARINA USHAKOVA
 * @version Summer 2014
 */
public class Gui {

	private JFrame theFrame;
	private Container contentPane;	
	private Game theGame;
	private NimMenuBar menuBar;
	private NimHelpDialog helpDialog;
	private NimContentPanel contentPanel;
	private boolean shouldShowHelpDialog;	
	
	
	/**
	 * Creates a Gui object to provide the view for the specified
	 * Game model object.
	 * 
	 * @param theGame	the domain model of the Nim game
	 * 
	 * @requires theGame != null
	 * @ensures	 the GUI is displayed
	 */
	public Gui(Game theGame) {
		this.theGame = theGame;
		this.shouldShowHelpDialog = true;
		this.createAndShowGUI();
	}
	
	/**
	 * Returns HumanPlayerPanel used in the game
	 * 
	 * @return	HumanPlayerPanel that lets user take turn
	 */
	public HumanPlayerPanel getHumanPlayerPanel() {
		return this.contentPanel.getHumanPlayerPanel();
	}
	
	/**
	 * Returns ComputerPlayerPanel used in the game
	 * 
	 * @return	ComputerPlayerPanel that tracks computer player moves
	 */
	public ComputerPlayerPanel getComputerPlayerPanel() {
		return this.contentPanel.getComputerPlayerPanel();
	}
	
	/**
	 * Returns NewGamePanel used in the game
	 * 
	 * @return	NewGamePanel with game settings
	 */
	public JPanel getNewGamePanel() {
		return this.contentPanel.getNewGamePanel();
	}
	
	/**
	 * Returns HelpDialog used in the game
	 * 
	 * @return	HelpDialog with game description
	 */
	public NimHelpDialog getHelpDialog() {
		return this.helpDialog;
	}
	
	/**
	 * Returns true if user chose to show help dialog
	 * the last time he viewed it. Returns false otherwise.
	 * 
	 * @return	value save in shouldShowHelpDialog instance variable
	 */
	public boolean getShouldShowHelpDialog() {
		return this.shouldShowHelpDialog;
	}
	
	/**
	 * Sets value of shouldShowHelpDialog instance variable
	 * 
	 * @param	isShowing	value being assigned to shouldSHowHelpDialog
	 */
	public void setShouldShowHelpDialog(boolean isShowing) {
		this.shouldShowHelpDialog = isShowing;
	}
	
	/**
	 * Returns the main frame
	 * 
	 * @return	the main frame of the game
	 */
	public JFrame getFrame() {
		return this.theFrame;
	}
	
	/**
	 * The game being played
	 * 
	 * @return	current Game object
	 */
	public Game getGame() {
		return this.theGame;
	}
	
	
	//****************************** private helper methods *******************

	/*
	 * Creates frame, adds NimMenuBar, ContentPanel. 
	 * Creates and shows NimHelpDialog.
	 */
	private void createAndShowGUI() {
		this.theFrame = new JFrame("Simple Nim");
		this.theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menuBar = new NimMenuBar(this);
		this.theFrame.setJMenuBar(this.menuBar);

		this.contentPanel = new NimContentPanel(this);
		this.contentPane = this.theFrame.getContentPane();
		this.contentPane.add(this.contentPanel);

		this.theFrame.pack();
		this.theFrame.setVisible(true);
		
		this.helpDialog = new NimHelpDialog(this);
		this.shouldShowHelpDialog = this.helpDialog.showHelpDialog();
	}


}

package edu.westga.nim.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
/**
 * Defines the panel that contains four other panels: 
 * HumanPlayerPanel, ComputerPlayerPanel, GameStatusPanel and NewGamePanel.
 * 
 * 
 * @author CS 6910 MARINA USHAKOVA
 * @version Summer 2014
 */
public class NimContentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private HumanPlayerPanel theHumanPlayerPanel;
	private ComputerPlayerPanel theComputerPlayerPanel;
	private GameStatusPanel theGameInfoPanel;
	private NewGamePanel theNewGamePanel;

	private Gui theGui;
	/**
	 * Creates a new NimContentPanel.
	 * 
	 * @param gui	the Gui object from which this panel gets its data
	 * 
	 * @requires 	gui != null
	 */
	public NimContentPanel(Gui gui) {
		if (gui == null) {
			throw new IllegalArgumentException("Null gui");
		}
		this.theGui = gui;
		this.buildContentPanel();
	}

	/**
	 * Returns HumanPlayerPanel used in the game
	 * 
	 * @return	HumanPlayerPanel that lets user take turn
	 */
	public HumanPlayerPanel getHumanPlayerPanel() {
		return this.theHumanPlayerPanel;
	}

	/**
	 * Returns ComputerPlayerPanel used in the game
	 * 
	 * @return	ComputerPlayerPanel that tracks computer player moves
	 */
	public ComputerPlayerPanel getComputerPlayerPanel() {
		return this.theComputerPlayerPanel;
	}

	/**
	 * Returns NewGamePanel used in the game
	 * 
	 * @return	NewGamePanel with game settings
	 */
	public JPanel getNewGamePanel() {
		return this.theNewGamePanel;
	}

	
	
	//*************************Private Helper Methods*********************************
	
	/*
	 * Adds 4 panels to the NimContentPanel
	 */
	private void buildContentPanel() {
		this.setLayout(new BorderLayout());
		this.theNewGamePanel = new NewGamePanel(this.theGui);
		this.add(this.theNewGamePanel, BorderLayout.NORTH);

		this.theHumanPlayerPanel = new HumanPlayerPanel(this.theGui.getGame());
		this.add(this.theHumanPlayerPanel, BorderLayout.WEST);
		this.theHumanPlayerPanel.setEnabled(false);

		this.theGameInfoPanel = new GameStatusPanel(this.theGui.getGame());
		this.add(this.theGameInfoPanel, BorderLayout.SOUTH);

		this.theComputerPlayerPanel = new ComputerPlayerPanel(this.theGui.getGame());
		this.add(this.theComputerPlayerPanel, BorderLayout.CENTER);
		this.theComputerPlayerPanel.setEnabled(false);
	}
}

/**
 * 
 */
package edu.westga.nim.controllers;

import edu.westga.nim.model.ComputerPlayer;
import edu.westga.nim.model.Game;
import edu.westga.nim.model.HumanPlayer;
import edu.westga.nim.model.strategies.CautiousStrategy;
import edu.westga.nim.view.Gui;

/**
 * Driver for the application. Creates the GUI in its
 * own thread and shows it.
 * 
 * @author CS6410
 * @version Summer, 2012
 *
 */
public class ApplicationController {

	/**
	 * Creates the game and the players, and runs the GUI in its
	 * own thread so it can run independently.
	 * 
	 * @param args	not used
	 */
	public static void main(String[] args) {		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Gui(new Game(new HumanPlayer("Human"), 
								 new ComputerPlayer(new CautiousStrategy())));
			}
		});
	}

}

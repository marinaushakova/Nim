package edu.westga.nim.view;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.westga.nim.model.ComputerPlayer;
import edu.westga.nim.model.Game;

/**
 * Defines the panel that lets the user tell the computer player to
 * take its turn and that displays the number of sticks the computer
 * player took on its turn.
 * 
 * @author CS 6910
 * @version Summer 2013
 */
public class ComputerPlayerPanel extends JPanel implements Observer {

	private Game theGame;
	private static final long serialVersionUID = 1L;
	private JLabel numberTaken;
	private ComputerPlayer theComputer;

	/**
	 * Creates a new ComputerPlayerPanel that observes the specified game. 
	 * 
	 * @param theGame	the model object from which this panel gets its data
	 * 
	 * @requires 	theGame != null
	 * @ensures		theGame.countObservers() = theGame.countObservers()@prev + 1
	 */
	public ComputerPlayerPanel(Game theGame) {
		this.theGame = theGame;
		this.theGame.addObserver(this);
		
		this.theComputer = this.theGame.computerPlayer();
		
		this.setBorder(BorderFactory.createTitledBorder("Computer player"));

		JLabel numberTakenLabel = new JLabel("Number of sticks taken:");
		this.add(numberTakenLabel);
		
		this.numberTaken = new JLabel("0");
		this.add(numberTaken);		
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
			aComponent.setEnabled(enabled);
		}
	}
	

	
	/**
	 * Sets this Panel's text to show the number of sticks this Player removed
	 * in its most recent turn and enables or disables this Panel and it
	 * components. Called when the observed Game object notifies this Panel
	 * that a Player took a turn. 
	 * 
	 * @param arg0	not used
	 * @param arg1	not used
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * 
	 * @ensures the panel's text is changed && isEnabled() == !isEnabled@prev
	 */
	@Override
	public void update(Observable arg0, Object arg1) {		
		this.numberTaken.setText("" + this.theComputer.sticksOnThisTurn());
	}

	public void resetNumberTaken() {
		this.numberTaken.setText("0");
		
	}

}

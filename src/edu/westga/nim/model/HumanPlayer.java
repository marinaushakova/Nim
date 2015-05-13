package edu.westga.nim.model;

/**
 * HumanPlayer represents a human player in the game Nim.
 * 
 * @author CS 6910
 * @version Summer 2013
 */
public class HumanPlayer extends AbstractPlayer implements Player {

	/**
	 * Creates a new ComputerPlayer with the specified name.
	 * 
	 * @param name
	 *            this Player's name
	 * 
	 * @requires name != null
	 * @ensure name().equals(name) && sticksRemoved() == 0
	 */
	public HumanPlayer(String name) {
		super(name);
	}

	// ************************** mutator methods ********************************
	
	/**
	 * Implements Player's setNumberSticksToTake() to set the number
	 * of sticks to the maximum allowed for this turn.
	 * 
	 * @ensure  sticksOnThisTurn() == 
	 * 					Math.min(pileForThisTurn.sticksLeft()-1, 
	 * 							 Game.MAX_STICKS_PER_TURN)
	 * 
	 * @see Player#setNumberSticksToTake()
	 */
	@Override
	public void setNumberSticksToTake() {
		this.setNumberSticksToTake(
				Math.min(this.pileForThisTurn().sticksLeft() - 1, Game.MAX_STICKS_PER_TURN));
		
	}
	

}

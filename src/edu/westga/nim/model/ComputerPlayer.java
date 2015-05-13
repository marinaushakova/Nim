package edu.westga.nim.model;

import edu.westga.nim.model.strategies.NumberOfSticksStrategy;

/**
 * ComputerPlayer represents a very simple automated player in the game Nim.
 * It removes 1 stick at a time.
 * 
 * @author CS 6910
 * @version Summer 2013
 */
public class ComputerPlayer extends AbstractPlayer implements Player {
	
	private static final String NAME = "Computer";
	
	private NumberOfSticksStrategy theStrategy;
	
	/**
	 * Creates a new ComputerPlayer with the specified strategy.
	 * 
	 * @requires theStrategy != null
	 * @ensures: the specified strategy will determine how many sticks to take
	 * 
	 * @ensure this.name().equals(name)
	 */
	public ComputerPlayer(NumberOfSticksStrategy theStrategy) {
		super(NAME);
		this.theStrategy = theStrategy;
	}
	
	
	//*************************** mutator methods ****************************
	
	/**
	 * Implements Player's setNumberSticksToTake() to set the number
	 * of sticks to 1.
	 * 
	 * @ensure  sticksOnThisTurn() >= 1 && 
	 * 			sticksOnThisTurn() <= Math.min(pileForThisTurn.sticksLeft()-1, 
	 * 							 			   Game.MAX_STICKS_PER_TURN)
	 * 
	 * @see Player#setNumberSticksToTake()
	 */
	@Override
	public void setNumberSticksToTake() {
		super.setNumberSticksToTake(
				this.theStrategy.howManySticks(
						super.pileForThisTurn().sticksLeft()));
	}

	
	/**
	 * Sets this ComputerPlayer’s strategy to the specified value.
	 * 
	 * @requires theStrategy != null
	 * @ensures: the specified strategy will determine how many sticks to take
	 * @param theStrategy	the strategy to use
	 */
	public void setStrategy(NumberOfSticksStrategy theStrategy) {
		this.theStrategy = theStrategy;
	}
		
}

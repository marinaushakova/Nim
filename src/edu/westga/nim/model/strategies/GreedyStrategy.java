/**
 * 
 */
package edu.westga.nim.model.strategies;

import edu.westga.nim.model.Game;


/**
 * GreedyStrategy implements the game-play algorithm which
 * returns the maximum valid number as the number of sticks
 * to be taken from the pile.
 * 
 * @author CS 6910
 * @version Summer 2013
 *
 */
public class GreedyStrategy implements NumberOfSticksStrategy {

	/**
	 * Implements {@link NumberOfSticksStrategy#howManySticks(int)} to
	 * return the maximum valid number of sticks.
	 * 
	 * @requires pileSize > 0 
	 * 
	 * @return 	Math.min(pileSize-1, Game.MAX_STICKS_PER_TURN)
	 * 
	 * @see edu.westga.nim.model.strategies.NumberOfSticksStrategy#howManySticks(int)
	 */
	@Override
	public int howManySticks(int pileSize) {
		if (pileSize <= 0) {
			throw new IllegalArgumentException("Pile is empty");
		}
		
		return Math.min(pileSize, Game.MAX_STICKS_PER_TURN);
	}

}

package edu.westga.nim.model.strategies;

/**
 * NumberOfSticksStrategy defines the common interface for all the game-play
 * algorithms for the 1-pile version of Nim.
 * 
 * @author CS 6910
 * @version Summer 2013
 */
public interface NumberOfSticksStrategy {

	/**
	 * Returns the number of sticks to be taken from the pile.
	 * 
	 * @precondition pileSize > 0
	 * 
	 * @param pileSize the number of sticks on the pile
	 * @return the number of sticks to take, > 0 and <= pileSize
	 */
	public int howManySticks(int pileSize);
}

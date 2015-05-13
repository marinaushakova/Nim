package edu.westga.nim.model.strategies;


/**
 * CautiousStrategy implements the game-play algorithm which
 * always returns 1 as the number of sticks to be taken from
 * the pile.
 * 
 * @author CS 6910
 * @version Summer 2013
 *
 */
public class CautiousStrategy implements NumberOfSticksStrategy {

	/** 
	 * Implements NumberOfSticksStrategy#howManySticks(int) to return 1,
	 * whatever the size of the pile.
	 * 
	 * @see edu.westga.nim.model.strategies.NumberOfSticksStrategy#howManySticks(int)
	 * @precondition pileSize > 0
	 * 
	 * @param pileSize the number of sticks on the pile
	 * @return 1
	 */
	@Override
	public int howManySticks(int pileSize) {
		if (pileSize < 1) {
			throw new IllegalArgumentException("Empty pile");
		}
		
		return 1;
	}

}

package edu.westga.nim.model.strategies;

import java.util.Random;

import edu.westga.nim.model.Game;

/**
 * RandomStrategy implements the game-play algorithm which
 * returns a random number as the number of sticks to be taken from
 * the pile.
 * 
 * @author CS 6910
 * @version Summer 2013
 *
 */
public class RandomStrategy implements NumberOfSticksStrategy {
	
	private Random generator;

	/**
	 * Creates a new RandomStrategy object with a new random number generator.
	 * 
	 */
	public RandomStrategy() {
		this.generator = new Random();
	}

	/** 
	 * Implements NumberOfSticksStrategy’s howManySticks() to return a 
	 * random number of sticks to be taken from the pile.
	 * 
	 * @requires pileSize > 0 
	 * 
	 * @return 	a random number between 1 and Game.MAX_STICKS_PER_TURN, inclusive
	 * 
	 * @see edu.westga.nim.model.strategies.NumberOfSticksStrategy#howManySticks(int)
	 */
	@Override
	public int howManySticks(int pileSize) {
		if (pileSize <= 0) {
			throw new IllegalArgumentException("Pile is empty");
		}
		
		return this.generator.nextInt(
				Math.min(pileSize, Game.MAX_STICKS_PER_TURN)) + 1;
	}

}

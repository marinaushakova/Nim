package edu.westga.nim.model;

import java.util.Observable;

/**
 * A pile of sticks for playing Nim.
 */
public class Pile extends Observable {

	private int sticks;

	/**
	 * Creates a new Pile with the specified number of sticks.
	 * 
	 * @ensure sticksLeft() == sticks
	 */
	public Pile(int sticks) {
		this.sticks = sticks;
		
		super.setChanged();
		super.notifyObservers("constructor");
	}

	//************************** mutator methods *****************************

	/**
	 * Reduces the number of sticks by the specified amount.
	 * 
	 * @require number > 0 && number <= sticksLeft()
	 * @ensure this.sticksLeft() == sticksLeft() - number
	 */
	public void remove(int number) {
		if (number < 1) {
			throw new IllegalArgumentException("Must remove at least 1 stick");
		}
		if (number > this.sticksLeft()) {
			throw new IllegalArgumentException("Can't remove that many sticks");
		}
		
		this.sticks -= number;
		
		super.setChanged();
		super.notifyObservers("remove");
	}
	
	/**
	 * Sets the size of this Pile to the specified number.
	 * 
	 * @require newSize > 1
	 * @ensure sticksLeft() == newSize
	 * 
	 * @param newSize	how big to make the pile
	 */
	public void setSize(int newSize) {		
		this.sticks = newSize;
		
		super.setChanged();
		super.notifyObservers("set size");
	}
	
	

	//************************** accessor methods ****************************

	/**
	 * Returns the number of sticks remaining in this Pile.
	 * 
	 * @return the size of this Pile
	 */
	public int sticksLeft() {
		return this.sticks;
	}

	/**
	 * Returns a String representation of this Pile.
	 * 
	 * @return "Pile size: n", where n is the number of sticks in this Pile
	 */
	public String toString() {
		return "Sticks left: " + this.sticks;
	}

	
}

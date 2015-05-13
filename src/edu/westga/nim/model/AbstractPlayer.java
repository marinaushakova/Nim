package edu.westga.nim.model;

public abstract class AbstractPlayer implements Player {

	private String theName;
	private int sticksToTake;
	private Pile thePile;

	/**
	 * Creates a new AbstractPlayer with the specified name.
	 * 
	 * @requires: aName != null
	 * @ensures name().equals(aName)
	 * 
	 * @param aName
	 */
	public AbstractPlayer(String aName) {
		if (aName == null) {
			throw new IllegalArgumentException("Null name");
		}
		this.theName = aName;
	}

	/**
	 * @see Player#takeTurn(Pile) 
	 */
	public void takeTurn() {				
		this.thePile.remove(this.sticksOnThisTurn());
	}

	/**
	 * @see Player#setPileForThisTurn(Pile)
	 */
	@Override
	public void setPileForThisTurn(Pile aPile) {
		this.thePile = aPile;
		
	}

	/**
	 * Implements Player's setNumberSticksToTake(int), but is not
	 * normally called by clients objects. Instead, clients usually
	 * call the 0-parameter version.
	 * 
	 * @see Player#setNumberSticksToTake(int)
	 */
	@Override
	public void setNumberSticksToTake(int number) {
		this.sticksToTake = number;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.theName = name;
	}

	/**
	 * @see Player#name()
	 */
	@Override
	public String name() {
		return this.theName;
	}

	/**
	 * @see Player#sticksOnThisTurn()
	 */
	@Override
	public int sticksOnThisTurn() {
		return this.sticksToTake;
	}

	/**
	 * @see Player#pileForThisTurn()
	 */
	@Override
	public Pile pileForThisTurn() {
		return this.thePile;
	}

	
	
	// ************************************************************************
	// ************ abstract method for subclasses to implement ***************
	// ************************************************************************
	
	/**
	 * @see Player#setNumberSticksToTake()
	 */
	@Override
	public abstract void setNumberSticksToTake();



}
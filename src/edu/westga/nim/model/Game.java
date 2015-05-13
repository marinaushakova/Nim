package edu.westga.nim.model;

import java.util.Observable;

import edu.westga.nim.model.strategies.CautiousStrategy;

/**
 * Game represents a Nim game with 1 pile of sticks.
 * 
 * @author MARINA USHAKOVA
 * @version Summer 2014
 */
public class Game extends Observable {

	public static final int DEFAULT_INITIAL_PILE_SIZE = 9;
	public static final int MAX_STICKS_PER_TURN = 3;

	private Player currentPlayer;
	private Player otherPlayer;

	private HumanPlayer theHuman;
	private ComputerPlayer theComputer;

	private Pile thePile;

	/**
	 * Creates a nim Game with the specified Players and a pile
	 * of the specified size.
	 * 
	 * @require theHuman != null && theComputer != null 
	 * @ensure humanPlayer().equals(theHuman) &&
	 *         computerPlayer.equals(theComputer) &&
	 *         sticksLeft() == -1
	 * 
	 * @param theComputer
	 *            the automated player
	 * @param theHuman
	 *            the human player
	 */
	public Game(HumanPlayer theHuman, ComputerPlayer theComputer) {
		if (theHuman == null) {
			throw new IllegalArgumentException("Null human");
		}
		if (theComputer == null) {
			throw new IllegalArgumentException("Null computer");
		}
		
		
		this.theHuman = theHuman;
		this.theComputer = theComputer;
		this.theComputer.setStrategy(new CautiousStrategy());
		
		this.currentPlayer = null;
		this.otherPlayer = null;
		
		this.thePile = new Pile(-1);
	}
	
	

	
	// *********************** mutator methods *************************

	/**
	 * Initializes the game for play.
	 * 
	 * @param firstPlayer 	the Player who takes the first turn
	 * @param secondPlayer	the Player who takes the second turn
	 * 
	 * @require firstPlayer != null && 
	 * 			secondPlayer != null &&
	 * 			!firstPlayer.equals(secondPlayer) &&
	 * 			pileSize > 1
	 * 
	 * @ensures whoseTurn().equals(firstPlayer) &&
	 * 			sticksLeft() == INITIAL_PILE_SIZE
	 */
	public void startNewGame(Player firstPlayer, 
						     Player secondPlayer,
						     int pileSize) {
		if (firstPlayer == null) {
			throw new IllegalArgumentException("Null first player");
		}
		if (secondPlayer == null) {
			throw new IllegalArgumentException("Null second player");
		}
		if (firstPlayer.equals(secondPlayer)) {
			throw new IllegalArgumentException("The players are the same");
		}
		
		if (pileSize <= 1) {
			throw new IllegalArgumentException("Pile size too small");
		}
		
		this.currentPlayer = firstPlayer;
		this.otherPlayer = secondPlayer;
		this.thePile.setSize(pileSize);
	}

	/**
	 * Conducts a move in the game, allowing the appropriate Player to
	 * take a turn. Has no effect if the game is over.
	 * 
	 * @requires !isGameOver()
	 * 
	 * @ensures !whoseTurn().equals(whoseTurn()@prev) &&
	 * 			sticksLeft() < sticksLeft()@prev
	 */
	public void play() {
		if (this.isGameOver()) {
			throw new IllegalArgumentException("Tried to play when game over");
		}
		
		this.currentPlayer.takeTurn();
		super.setChanged();
		super.notifyObservers(null);
		
		this.swapPlayers();
				
	}
	
	
	/**
	 * Sets the number of sticks on the pile to the specified number.
	 * 
	 * @require pileSize > 1
	 * @ensure sticksLeft() == pileSize
	 * 
	 * @param pileSize	the number of sticks
	 */
	public void setPileSize(int pileSize) {
		if (pileSize <= 1) {
			throw new IllegalArgumentException("Pile size too small");
		}
		
		this.pile().setSize(pileSize);
	}
	
	
	
	// *********************** accessor methods *************************

	/**
	 * Returns the human Player object.
	 * 
	 * @return the human Player
	 */
	public HumanPlayer humanPlayer() {
		return this.theHuman;
	}

	/**
	 * Returns the computer Player object.
	 * 
	 * @return the computer Player
	 */
	public ComputerPlayer computerPlayer() {
		return this.theComputer;
	}
	
	/**
	 * Returns the Player whose turn it is.
	 * 
	 * @return	the current Player
	 */
	public Player whoseTurn() {
		return this.currentPlayer;
	}

	/**
	 * Returns the number of sticks remaining in the pile.
	 * 
	 * @return how many sticks are left in the pile
	 */
	public int sticksLeft() {
		return this.thePile.sticksLeft();
	}

	/**
	 * Return whether the game is over.
	 * 
	 * @return true iff sticksLeft() == 0
	 */
	public boolean isGameOver() {
		return this.thePile.sticksLeft() == 0;
	}
	
	/**
	 * Returns the Pile used in this Game.
	 * 
	 * @return	the Pile
	 */
	public Pile pile() {
		return this.thePile;
	}


	/**
	 * Returns a String representing the pile size, or, if
	 * the game is over, the name of the winner.
	 * 
	 * @return a String representation of this Game
	 */
	public String toString() {
		if (this.isWaitingToStart()) {
			return "Start new game";
		}
		
		if (this.isGameOver()) {
			return "Game over! Winner: " + this.otherPlayer.name();
		}

		return this.thePile.toString();
	}

	
	
	//***************************** private helper methods *******************
	
	private boolean isWaitingToStart() {
		return this.pile().sticksLeft() == -1;
	}
	
	private void swapPlayers() {
		Player temp = this.currentPlayer;
		this.currentPlayer = this.otherPlayer;
		this.otherPlayer = temp;
	}
	
}

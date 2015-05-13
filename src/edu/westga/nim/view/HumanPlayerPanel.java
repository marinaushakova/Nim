package edu.westga.nim.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.westga.nim.model.ComputerPlayer;
import edu.westga.nim.model.Game;
import edu.westga.nim.model.HumanPlayer;


/**
 * Defines the panel that lets the user indicate the number of sticks
 * to take from the pile and to take the turn.
 * 
 * @author CS 6910
 * @version Summer 2013
 */
public class HumanPlayerPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Integer> numberToTakeComboBox;
	private JButton takeSticksButton;
	
	private HumanPlayer theHuman;
	private ComputerPlayer theComputer;
	private Game theGame;
	

	/**
	 * Creates a new HumanPlayerPanel that observes the specified game. 
	 * 
	 * @param theGame	the model object from which this panel gets its data
	 * 
	 * @requires 	theGame != null
	 * @ensures		theGame.countObservers() = theGame.countObservers()@prev + 1
	 */
	public HumanPlayerPanel(Game theGame) {
		this.theGame = theGame;
		this.theGame.addObserver(this);
		this.theHuman = this.theGame.humanPlayer();
		this.theComputer = this.theGame.computerPlayer();
		
		this.buildPanel();
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
	 * Sets this Panel's combo box to the correct list of numbers and 
	 * enables or disables this Panel and it components. Called when the 
	 * observed Game object notifies this Panel that a Player took a turn. 
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
		this.resetNumberToTakeComboBox();
	}

	
	/**
	 * Clears the numbers in the combo box, then adds back in all the
	 * valid numbers so the user can't try to take more sticks than allowed.
	 * 
	 * @ensures the combo box contains 1 through 
	 * 			Math.min(numberSticks, Game.MAX_STICKS_PER_TURN)
	 * 
	 * @param numberSticks  upper limit set by the size of the pile
	 */
	public void resetNumberToTakeComboBox(int numberSticks) {		
		this.numberToTakeComboBox.removeAllItems();
		int max = Math.min(numberSticks,  
				 		   Game.MAX_STICKS_PER_TURN);
		for (int i = 0; i < max; i++) {
			this.numberToTakeComboBox.addItem(i + 1);
		}		
	}
	
	

	
	//************************ private helper methods ************************

	private void resetNumberToTakeComboBox() {
		this.resetNumberToTakeComboBox(this.theGame.sticksLeft());
	}
	
	
	private void buildPanel() {
		this.setBorder(BorderFactory.createTitledBorder(this.theHuman.name()));		
		
		JLabel numberToTakeLabel = new JLabel("Number of sticks to take:");
		this.add(numberToTakeLabel);
		
		Integer[] numbers = {1, 2, 3};
		this.numberToTakeComboBox = new JComboBox<Integer>(numbers);
		this.numberToTakeComboBox.setEditable(false);
		
		this.add(this.numberToTakeComboBox, BorderLayout.SOUTH);
		
		this.takeSticksButton = new JButton("Take sticks");
		this.takeSticksButton.addActionListener(new TakeTurnListener());
		this.add(this.takeSticksButton);
	}


	//************************ private inner class ****************************

	/* 
	 * Defines the listener for takeSticksButton.
	 */
	private class TakeTurnListener implements ActionListener {

		/* 
		 * Tells the Game to have its current player (i.e., the human Player)
		 * take its turn.	
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!theGame.isGameOver()) {
				theHuman.setPileForThisTurn(theGame.pile());
				theHuman.setNumberSticksToTake(
								(int)numberToTakeComboBox.getSelectedItem());
				theGame.play();
			}
			
			if (!theGame.isGameOver()) {
				theComputer.setPileForThisTurn(theGame.pile());
				theComputer.setNumberSticksToTake();
				theGame.play();				
			}
		}

	}

	
}

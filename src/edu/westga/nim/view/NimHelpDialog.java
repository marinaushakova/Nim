package edu.westga.nim.view;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Defines help dialog that lets user view game rules and choose 
 * the option to show this dialog again at the beginning of the game.
 * 
 * @author CS 6910 MARINA USHAKOVA
 * @version Summer 2014
 */
public class NimHelpDialog {

	private Gui theGui;
	/**
	 * Creates a new NimHelpDialog. 
	 * 
	 * @param gui	the Gui object from which this menuBar gets its data
	 * 
	 * @requires 	theGui != null
	 */
	public NimHelpDialog(Gui gui) {
		if (gui == null) {
			throw new IllegalArgumentException("Null gui");
		}
		this.theGui = gui;
	}

	/**
	 * Shows help dialog if user previously checks shouldShowCheckBox
	 * and returns new value of shouldShowCheckBox
	 * 
	 * @return	false if checkBox was not checked and true otherwise
	 */
	public boolean showHelpDialog() {
		if (!this.theGui.getShouldShowHelpDialog()) {
			return false;
		}

		JCheckBox shouldShowCheckBox = new JCheckBox(
				"Show this message when starting a new game.", false);

		Object[] msgContent = { this.buildHelpPane(), shouldShowCheckBox };

		JOptionPane.showMessageDialog(this.theGui.getFrame(), msgContent, "Title",
				JOptionPane.INFORMATION_MESSAGE);

		return shouldShowCheckBox.isSelected();

	}
	
	
	//*******************Private helper methods*********************************
	
	/*
	 * Creates and return helpPane that contains info on how to play the game
	 */
	private JScrollPane buildHelpPane() {
		String helpMessage = "Nim rules: Play against the computer. "
				+ "Alternate taking turns, removing 1 to 3 sticks per turn. "
				+ "The player who takes the last stick loses. "
				+ "You may set the number of sticks on the pile at the start "
				+ "of each game, and switch what strategy the computer uses "
				+ "at any time.";

		JTextArea helpTextArea = new JTextArea(helpMessage);
		helpTextArea.setRows(4);
		helpTextArea.setColumns(40);
		helpTextArea.setLineWrap(true);
		helpTextArea.setWrapStyleWord(true);
		helpTextArea.setEditable(false);
		helpTextArea.setOpaque(false);

		JScrollPane helpPane = new JScrollPane(helpTextArea);
		return helpPane;
	}
}

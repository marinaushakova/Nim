package edu.westga.nim.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import edu.westga.nim.model.Game;
import edu.westga.nim.model.strategies.CautiousStrategy;
import edu.westga.nim.model.strategies.GreedyStrategy;
import edu.westga.nim.model.strategies.NumberOfSticksStrategy;
import edu.westga.nim.model.strategies.RandomStrategy;
/**
 * Defines the menu bar that lets user start new game, exit the game, 
 * choose computer player strategy and show game info.
 * 
 * @author CS 6910 MARINA USHAKOVA
 * @version Summer 2014
 */
public class NimMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	private Gui theGui;
	private JMenu menu;
	private JMenu computerPlayerMenu;

	/**
	 * Creates a new NimMenuBar. 
	 * 
	 * @param gui	the Gui object from which this menuBar gets its data
	 * 
	 * @requires 	theGui != null
	 */
	public NimMenuBar(Gui gui) {
		if (gui == null) {
			throw new IllegalArgumentException("Null gui");
		}
		this.theGui = gui;
		this.buildMenuBar();
	}

	//****************************** private helper methods *******************
	
	/*
	 * Creates 3 menus and adds them to the menuBar
	 */
	private void buildMenuBar() {

		this.buildFileMenu();
		this.buildSettingsMenu();
		this.buildHelpMenu();

	}

	/*
	 * Creates settings menu and adds it to the menu bar
	 */
	private void buildSettingsMenu() {
		this.menu = new JMenu("Settings");
		this.menu.setMnemonic(KeyEvent.VK_S);
		this.menu.getAccessibleContext().setAccessibleDescription(
				"Settings menu");

		this.buildComputerPlayerMenu();

		this.add(this.menu);
	}

	/*
	 * Creates ComputerPlayer submenu and adds it to the Settings menu
	 */
	private void buildComputerPlayerMenu() {
		this.computerPlayerMenu = new JMenu("Computer Player");
		computerPlayerMenu.setMnemonic(KeyEvent.VK_P);

		this.addStrategyItem("Cautious", KeyEvent.VK_C, new CautiousStrategy());
		this.addStrategyItem("Greedy", KeyEvent.VK_G, new GreedyStrategy());
		this.addStrategyItem("Random", KeyEvent.VK_R, new RandomStrategy());

		this.menu.add(computerPlayerMenu);
	}

	/*
	 * Creates strategy item and adds it to the ComputerPlayer submenu
	 */
	private void addStrategyItem(String itemName, int keyEvent,
			NumberOfSticksStrategy theStrategy) {
		final NumberOfSticksStrategy strategy = theStrategy;
		JMenuItem menuItem = new JMenuItem(itemName, keyEvent);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NimMenuBar.this.theGui.getGame().computerPlayer().setStrategy(strategy);
			}
		});
		this.computerPlayerMenu.add(menuItem);
	}

	/*
	 * Creates File menu and adds it the menu bar
	 */
	private void buildFileMenu() {
		this.menu = new JMenu("File");
		this.menu.setMnemonic(KeyEvent.VK_F);
		this.menu.getAccessibleContext().setAccessibleDescription("File menu");

		JMenuItem menuItem = new JMenuItem("New game", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.ALT_MASK));
		menuItem.addActionListener(new StartNewGameListener());
		this.menu.add(menuItem);

		this.menu.addSeparator();

		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		this.menu.add(menuItem);

		this.add(this.menu);
	}

	/*
	 * Creates Help menu and adds it to the menu bar
	 */
	private void buildHelpMenu() {
		this.menu = new JMenu("Help");
		this.menu.setMnemonic(KeyEvent.VK_H);
		this.menu.getAccessibleContext().setAccessibleDescription("Help menu");

		JMenuItem menuItem = new JMenuItem("Help", KeyEvent.VK_E);
		menuItem.addActionListener(new ActionListener() {
			// Shows helpDialog
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NimMenuBar.this.theGui.setShouldShowHelpDialog(true);
				NimMenuBar.this.theGui.setShouldShowHelpDialog(NimMenuBar.this.theGui.getHelpDialog().showHelpDialog());
			}

		});

		this.menu.add(menuItem);

		menuItem = new JMenuItem("About", KeyEvent.VK_A);
		menuItem.addActionListener(new ActionListener() {
			// Shows game info
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String aboutMessage = "The Game of Nim\nVersion: 0.5 (Summer 2014)"
						+ "\nContact info: mushako@my.westga.edu";
				JOptionPane.showMessageDialog(
						NimMenuBar.this.theGui.getFrame(), aboutMessage,
						"About", JOptionPane.INFORMATION_MESSAGE);
			}

		});

		this.menu.add(menuItem);
		this.add(this.menu);
	}

	/*
	 * Defines the listener for takeSticksButton.
	 */
	private class StartNewGameListener implements ActionListener {

		/*
		 * Tells the Game to have its current player (i.e., the human Player)
		 * take its turn.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			NimMenuBar.this.theGui.getNewGamePanel().setEnabled(true);
			NimMenuBar.this.theGui.getHumanPlayerPanel().setEnabled(false);
			NimMenuBar.this.theGui.getComputerPlayerPanel().resetNumberTaken();
			NimMenuBar.this.theGui.getHumanPlayerPanel()
					.resetNumberToTakeComboBox(Game.MAX_STICKS_PER_TURN);
			if (NimMenuBar.this.theGui.getShouldShowHelpDialog()) {
				NimMenuBar.this.theGui
						.setShouldShowHelpDialog(NimMenuBar.this.theGui
								.getHelpDialog().showHelpDialog());
			}
		}
	}

}
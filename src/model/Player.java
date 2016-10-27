package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import ui.Driver;

/**
 * Stores the players name and score.
 * 
 * @author Jimmy
 * 
 */
public class Player {
	public JDialog dialog;
	private String _player;
	public int _score = 0;

	/**
	 * Keeps the players name.
	 * @param p The name of the player.
	 */
	public Player(String p) {
		_player = p;
	}

	/**
	 * Increases the score by one.
	 */
	public void addScore() {
		_score++;
	}

	/**
	 * Gets the players name.
	 * @return Returns the players name.
	 */
	public String getName() {
		return _player;
	}

	/**
	 * Checks if a player has won by checking their score.
	 * @return Returns true or false if a player has won.
	 */
	public boolean win() {
		//Checks the amount of players.
		if (Driver.getState() < 4) {
			if (_score >= 10) {
				return true;
			}
		} else {
			if (_score >= 6) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Announces the winner then exits the game after 5000ms.
	 */
	public void gameWin() {
		JOptionPane pane = new JOptionPane(_player + " is the Winner!!!!");
		dialog = pane.createDialog("The Game Is Over");
		//Closes the JDialog after 5000ms.
		Timer timer = new Timer(5000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		timer.setRepeats(false);
		timer.start();
		dialog.setVisible(true);
	}
}

package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Board;
/**
 * EventHandler for the skip button.
 * @author Jimmy
 *
 */
public class Skip implements ActionListener {
	private Board _board;
	/**
	 * Assigns the board to use.
	 * @param board which board to use.
	 */
	public Skip(Board board) {
		_board = board;
	}

	/**
	 * Calls changeGameState when the button is pressed.
	 */
	@Override public void actionPerformed(ActionEvent e) {
		_board.changeGameState();
		_board.notifyOfChange();
	}

}

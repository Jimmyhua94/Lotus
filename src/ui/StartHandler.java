package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.StartPosition;

/**
 * EventHandler for the starting button.
 * 
 * @author Jimmy
 * 
 */
public class StartHandler implements ActionListener {
	private StartPosition _pos;

	/**
	 * Assigns the position of the button.
	 * 
	 * @param pos Which position is the piece in?
	 */
	public StartHandler(StartPosition pos) {
		_pos = pos;
	}

	/**
	 * Moves the piece.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (!_pos.isEmpty()) {
			_pos.moveTopLeft();
		}
	}

}

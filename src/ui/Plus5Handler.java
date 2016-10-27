package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Position;

/**
 * EventHandler for the plus5 button.
 * 
 * @author Jimmy
 * 
 */
public class Plus5Handler implements ActionListener {
	private Position _pos;

	/**
	 * Assigns the position of the button.
	 * 
	 * @param pos Which position is the piece in?
	 */
	public Plus5Handler(Position pos) {
		_pos = pos;
	}

	/**
	 * Moves the piece.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!_pos.isEmpty()) {
			_pos.movePlus5();
		}
	}
}

package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Position;

/**
 * EventHandler for the reverse button.
 * 
 * @author Jimmy
 * 
 */
public class ReverseHandler implements ActionListener {
	private Position _pos;

	/**
	 * Assigns the position of the button.
	 * 
	 * @param pos Which position is the piece in?
	 */
	public ReverseHandler(Position pos) {
		_pos = pos;
	}

	/**
	 * Moves the piece.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!_pos.isEmpty()) {
			_pos.reverseTop();
		}
	}

}

package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Board;

/**
 * EventHanadler for the left JRadioButton
 * 
 * @author Jimmy
 * 
 */
public class LeftRadioHandler implements ActionListener {
	private Board _b;

	/**
	 * Assigns the EventHandler to the board.
	 * 
	 * @param bd
	 *            Which board is used.
	 */
	public LeftRadioHandler(Board bd) {
		_b = bd;
	}

	/**
	 * Makes sure only one JRadioButton is selected at a time.
	 */
	public void actionPerformed(ActionEvent e) {
		if (_b.getLeftRB().isSelected()) {
			_b.getRightRB().setSelected(false);
			_b.getMiddleRB().setSelected(false);
		}

	}
}

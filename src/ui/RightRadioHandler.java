package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Board;

/**
 * EventHanadler for the right JRadioButton
 * 
 * @author Jimmy
 * 
 */
public class RightRadioHandler implements ActionListener {
	private Board _b;

	/**
	 * Assigns the EventHandler to the board.
	 * 
	 * @param bd
	 *            Which board is used.
	 */
	public RightRadioHandler(Board bd) {
		_b = bd;
	}

	/**
	 * Makes sure only one JRadioButton is selected at a time.
	 */
	public void actionPerformed(ActionEvent e) {

		if (_b.getRightRB().isSelected()) {
			_b.getLeftRB().setSelected(false);
			_b.getMiddleRB().setSelected(false);
		}

	}
}
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
/**
 * Controls where the piece is to be moved from its starting stack.
 * <p>
 * StartPosition extends Position 
 * @author Jimmy
 *
 */
public class StartPosition extends Position {
	/**
	 * Creates the starting stack for a player on the board.
	 * @param offset How much of an offset is required.
	 * @param index Which position is this stack.
	 * @param b Which board is being used.
	 */
	public StartPosition(int offset, int index, Board b) {
		super(offset, index, b);
	}
	
	/**
	 * moveTop can not be used for the starting stacks
	 */
	@Override public void moveTop() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Moves the top piece of the stack to its appropriate location based on the JRadioButton selected as long as the stack is not empty.
	 */
	public void moveTopLeft() {
		//Checks if a JRadioButton has been selected and if hte stack is empty and moves the piece.
		if (_board.getLeftRB().isSelected() || _board.getMiddleRB().isSelected() || _board.getRightRB().isSelected()){	
			if(!_stack.isEmpty()){
				int distanceToMove = height()-1;
				Piece p = _stack.pop();
				p.setStart(_stack);
				if(_board.getLeftRB().isSelected()){
					p.setTrack(_board.leftTrack());
					p.getTrack()[distanceToMove].putOn(p);
				}else if (_board.getMiddleRB().isSelected()){
					p.setTrack(_board.middleTrack());
					p.getTrack()[distanceToMove].putOn(p);
				}else if (_board.getRightRB().isSelected()){
					p.setTrack(_board.rightTrack());
					p.getTrack()[distanceToMove].putOn(p);
				}
			}
		}
		//No JRadioButton is selected so an error is created.
		else {
			JOptionPane pane = new JOptionPane("You must choose a path to start with the radio buttons.");
			final JDialog dialog = pane.createDialog("Radio Button");
			//Closes the JDialog after 5000ms.
			Timer timer = new Timer(3000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.dispose();
				}
			});
			timer.setRepeats(false);
			timer.start();
			dialog.setVisible(true);
		}
	}
}

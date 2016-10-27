package model;

import java.util.Stack;

/**
 * Stores the methods for moving the piece on the board.
 * @author Jimmy
 */
public class Position {
	private int _offset;
	private int _index;
	public Stack<Piece> _stack;
	protected Board _board;

	/**
	 * Creates the stack for a button on the board.
	 * @param offset How much of an offset is required.
	 * @param index Which position is this stack.
	 * @param b Which board is being used.
	 */
	public Position(int offset, int index, Board b) {
		_offset = offset;
		_index = index;
		_board = b;
		_stack = new Stack<Piece>();
	}

	/**
	 * Gets the height of a stack
	 * @return Returns the size of the stack and its offset.
	 */
	public int height() {
		return _stack.size() + _offset;
	}

	/**
	 * Pushes a pieces on the stack, and changes to the next player
	 * @param p Which stack is the piece pushed on to.
	 */
	public void putOn(Piece p) {
		_stack.push(p);
		_board.changeGameState();
		_board.notifyOfChange();
	}

	/**
	 * Gets the first pieces of a stack.
	 * @return Returns the first piece of a stack.
	 */
	public Piece peekTop() {
		return _stack.peek();
	}

	/**
	 * Pushes the top piece to its next stack
	 * <p>
	 * Also checks if the height of a stack is greater then 15 increase the players score. 
	 */
	public void moveTop() {
		//Checks if the stack is high enough to push a piece off the board and score a point.
		if (_index + height() >= 14) {
			Piece p = _stack.pop();
			_board.score(p);
			_board.changeGameState();
			_board.notifyOfChange();
		} else {
			int distanceToMove = height();
			Piece p = _stack.pop();
			p.getTrack()[_index + distanceToMove].putOn(p);
			_board.notifyOfChange();
		}
	}

	/**
	 * Gets the stack and returns a string.
	 * @return Returns a string of all the pieces in the stack.
	 */
	@Override
	public String toString() {
		return _stack.toString();
	}

	/**
	 * Checks if a stack is empty.
	 * @return Returns true or false if a stack is empty.
	 */
	public boolean isEmpty() {
		return _stack.isEmpty();
	}

	/**
	 * Pushes the top piece of the reverse stack to its correct stack.
	 */
	public void reverseTop() {
		Piece p = _stack.pop();
		int distanceToMove = height() + 1;
		//Checks where the piece last came from.
		if (distanceToMove == 1) {
			if (_index + distanceToMove >= 14) {
				_board.score(p);
				_board.changeGameState();
				_board.notifyOfChange();
			} else {
				p.getTrack()[_index + distanceToMove].putOn(p);
				_board.notifyOfChange();
			}

		} else if (_index < distanceToMove) {
			p.getStart().push(p);
			_board.changeGameState();
			_board.notifyOfChange();
		} else {
			p.getTrack()[_index - distanceToMove].putOn(p);
			_board.notifyOfChange();
		}
	}

	/**
	 * Pushes the top piece of the plus2 stack to its correct stack.
	 */
	public void movePlus2() {
		//Checks if the stack is high enough to push a piece off the board and score a point.
		if (_index + height() + 2 >= 14) {
			Piece p = _stack.pop();
			_board.score(p);
			_board.changeGameState();
			_board.notifyOfChange();
		} else {
			int distanceToMove = height() + 2;
			Piece p = _stack.pop();
			p.getTrack()[_index + distanceToMove].putOn(p);
			_board.notifyOfChange();
		}
	}

	/**
	 * Pushes the top piece of the plus5 stack to its correct stack.
	 */

	public void movePlus5() {
		//Checks if the stack is high enough to push a piece off the board and score a point.
		if (_index + height() + 5 >= 14) {
			Piece p = _stack.pop();
			_board.score(p);
			_board.changeGameState();
			_board.notifyOfChange();
		} else {
			int distanceToMove = height() + 5;
			Piece p = _stack.pop();
			p.getTrack()[_index + distanceToMove].putOn(p);
			_board.notifyOfChange();
		}
	}

}

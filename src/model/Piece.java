package model;

import java.util.Stack;
/**
 * Keeps track of the pieces 
 * @author Jimmy
 *
 */
public class Piece {
	private Stack<Piece> _start;
	private String _string;
	private Position[] _track;
	private int _position;
	
	/**
	 * Keeps track of the piece
	 * @param s
	 */
	public Piece(String s) {
		_string = s;
	}
	
	/**
	 * Sets the position of the piece.
	 * @param i The position of the piece.
	 */
	public void setPosition(int i) {
		_position = i;
	}
	
	/**
	 * Sets the track of the piece.
	 * @param track The track of the piece.
	 */
	public void setTrack(Position[] track) {
		_track = track;
	}
	
	/**
	 * Sets the stack the piece started from.
	 * @param start Which starting stack.
	 */
	public void setStart(Stack<Piece> start){
		_start = start;
	}
	
	/**
	 * Gets the piece and returns a string.
	 * @return Returns the name of a piece.
	 */
	@Override public String toString() {
		return _string;
	}
	
	/**
	 * Gets the track for the piece.
	 * @return Returns the track for the piece.
	 */
	public Position[] getTrack() {
		return _track;
	}
	
	/**
	 * Gets the current position of the piece.
	 * @return Returns the position of the piece.
	 */
	public int getPosition() {
		return _position;
	}
	
	/**
	 * Gets the stack the piece is using.
	 * @return Returns the stack of the piece.
	 */
	public Stack<Piece> getStart() {
		return _start;
	}
	
	/**
	 * Gets the player for the piece.
	 * @return Returns the symbol for a player of a piece.
	 */
	public String getPlayer(){
		return _string;
	}

}

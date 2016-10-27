package model;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JRadioButton;

import ui.Driver;

/**
 * Stores all the functionality of the game board.
 * 
 * @author Jimmy
 * 
 */
public class Board {
	private ActionListener _listener;
	private JRadioButton _leftRB = new JRadioButton();
	private JRadioButton _middleRB = new JRadioButton();
	private JRadioButton _rightRB = new JRadioButton();
	private Position[] _left;
	private Position[] _middle;
	private Position[] _right;
	private StartPosition[] _greenStart;
	private StartPosition[] _yellowStart;
	private StartPosition[] _whiteStart;
	private StartPosition[] _blackStart;
	private StartPosition[] _redStart;
	private StartPosition[] _blueStart;
	private int _reverse;
	private int _plus5;
	private int _plus2;
	private int _track;
	private int _turn = 1;
	public int _skip;
	
	/**
	 * Sets the ActionListener for the whole board.
	 * @param al passes in the board.
	 */
	public void setActionListener(ActionListener al) {
		_listener = al;
	}

	/**
	 * Resets the ActionListener.
	 */
	public void notifyOfChange() {
		if (_listener != null) {
			_listener.actionPerformed(null);
		}
	}

	/**
	 * Creates the stacks for each button
	 */
	public Board() {
		// Makes sure there is a default track selected.
		_leftRB.setSelected(true);
		//sets the text of the JRadioButtons
		_leftRB.setText("Left");
		_middleRB.setText("Middle");
		_rightRB.setText("Right");
		
		// Creates an array for the track stacks.
		_left = new Position[14];
		_middle = new Position[14];
		_right = new Position[14];
		
		// Creates the stacks for the fork
		for (int i=0; i<3; i++) {
			_left[i] = (new Position(0,i,this));
			_middle[i]=(new Position(0,i,this));
			_right[i] = (new Position(0,i,this));
		}
		
		// Creates the stacks for the center straight away.
		for (int i=3; i<14; i++) {
			_left[i] = (new Position(0,i,this));
			_middle[i] = _left[i];
			_right[i] = _left[i];
		}
		
		// Creates an array for each player's stacks
		_redStart = new StartPosition[4];
		_blueStart = new StartPosition[4];
		_greenStart = new StartPosition[4];
		_whiteStart = new StartPosition[4];
		_blackStart = new StartPosition[4];
		_yellowStart = new StartPosition[4];
		
		// Creates the stacks for the starting position and pushes in each piece.
		for (int i=0; i<4; i++) {
			_redStart[i] = (new StartPosition(0,i,this));
			populate(_redStart[i], "R", i+1);
			_blueStart[i] = (new StartPosition(0,i,this));
			populate(_blueStart[i], "B", i+1);
			_greenStart[i] = (new StartPosition(0,i,this));
			populate(_greenStart[i], "G", i+1);
			_whiteStart[i] = (new StartPosition(0,i,this));
			populate(_whiteStart[i], "W", i+1);
			_blackStart[i] = (new StartPosition(0,i,this));
			populate(_blackStart[i], "K", i+1);
			_yellowStart[i] = (new StartPosition(0,i,this));
			populate(_yellowStart[i], "Y", i+1);
		}
		
		// Generates a random int for for the special button positions.
		Random randomGenerator = new Random();
		_reverse = randomGenerator.nextInt(14);
		_plus2 = randomGenerator.nextInt(14);
		_plus5 = randomGenerator.nextInt(14);
		// Ensures that the random int do not conflict.
		while(_reverse == _plus2 || _reverse == _plus5 || _plus2 == _plus5 ){
			_plus2 = randomGenerator.nextInt(14);
			_plus5 = randomGenerator.nextInt(14);
			_track = new Random().nextInt(3);
		}
	}

	/**
	 * Creates the pieces for each stack in the starting position.
	 * 
	 * @param pos Which track does it use.
	 * @param s	Name to give the pieces.
	 * @param howMany How many pieces should be created.
	 */
	public void populate(Position pos, String s, int howMany) {
		for (int i=0; i<howMany; i++){
			Piece temp = new Piece(s);
			pos.putOn(temp);
		}
	}
	
	/**
	 * Creates an array that stores the text of each button.
	 * 
	 * @return Returns the array of button text.
	 */
	public ArrayList<String> getBoard() {
		ArrayList<String> temp = new ArrayList<String>();
		if(Driver.getState()<4){
			for (int i=0; i<4; i++) {
				temp.add(_redStart[i].toString());
				temp.add(_blueStart[i].toString());
				temp.add(_greenStart[i].toString());
				temp.add(_whiteStart[i].toString());
				temp.add(_blackStart[i].toString());
				temp.add(_yellowStart[i].toString());
			}
		}else {
			for (int i=0; i<3; i++) {
				temp.add(_redStart[i].toString());
				temp.add(_blueStart[i].toString());
				temp.add(_greenStart[i].toString());
				temp.add(_whiteStart[i].toString());
				temp.add(_blackStart[i].toString());
				temp.add(_yellowStart[i].toString());
			}
		}
		for (int i=0; i<3; i++) {

			if(i == _reverse){
				if(_track == 0){
					temp.add('-'+_left[i].toString());
					temp.add(_middle[i].toString());
					temp.add(_right[i].toString());
				}
				else if(_track == 1){
					temp.add(_left[i].toString());
					temp.add('-'+_middle[i].toString());
					temp.add(_right[i].toString());
				}
				else if(_track == 2){
					temp.add(_left[i].toString());
					temp.add(_middle[i].toString());
					temp.add('-'+_right[i].toString());
				}
			}
			else if(i == _plus2){
				if(_track == 0){
					temp.add("+2"+_left[i].toString());
					temp.add(_middle[i].toString());
					temp.add(_right[i].toString());
				}
				else if(_track == 1){
					temp.add(_left[i].toString());
					temp.add("+2"+_middle[i].toString());
					temp.add(_right[i].toString());
				}
				else if(_track == 2){
					temp.add(_left[i].toString());
					temp.add(_middle[i].toString());
					temp.add("+2"+_right[i].toString());
				}
			}	
			else if(i == _plus5){
				if(_track == 0){
					temp.add("+5"+_left[i].toString());
					temp.add(_middle[i].toString());
					temp.add(_right[i].toString());
				}
				else if(_track == 1){
					temp.add(_left[i].toString());
					temp.add("+5"+_middle[i].toString());
					temp.add(_right[i].toString());
				}
				else if(_track == 2){
					temp.add(_left[i].toString());
					temp.add(_middle[i].toString());
					temp.add("+5"+_right[i].toString());
				}
			}	
			else{
				temp.add(_left[i].toString());
				temp.add(_middle[i].toString());
				temp.add(_right[i].toString());
			}
		}
		for (int i=3; i<14; i++) {
			if(i == _reverse){
				temp.add("-"+_right[i].toString());
			}
			else if(i == _plus2){
				temp.add("+2"+_right[i].toString());
			}
			else if(i == _plus5){
				temp.add("+5"+_right[i].toString());
			}
			else{
				temp.add(_right[i].toString());
			}
		}
		return temp;
	}
	
	/**
	 * Increase the score for a player
	 * 
	 * @param p Which player has scored a point.
	 */
	public void score(Piece p) {
		if(p.getPlayer().equals("R")){
			Driver._player1.addScore();
		}else if(p.getPlayer().equals("B")){
			Driver._player2.addScore();
		}else if(p.getPlayer().equals("G")){
			Driver._player3.addScore();
		}else if(p.getPlayer().equals("W")){
			Driver._player4.addScore();
		}else if(p.getPlayer().equals("K")){
			Driver._player5.addScore();
		}else if(p.getPlayer().equals("Y")){
			Driver._player6.addScore();
		}
	}
	
	/**
	 * Sets the turn for the players
	 * 
	 * @param turn who should the turn be set to?
	 */
	public void setTurn(int turn) {	_turn = turn;}
	
	/**
	 * Gets the starting track for red.
	 * @param i which starting stack to use.
	 * @return	Returns the position for the red piece.
	 */
	public StartPosition getRedStart(int i) { return _redStart[i]; }
	/**
	 * Gets the starting track for blue.
	 * @param i which starting stack to use.
	 * @return	Returns the position for the blue piece.
	 */
	public StartPosition getBlueStart(int i) { return _blueStart[i]; }
	/**
	 * Gets the starting track for green.
	 * @param i which starting stack to use.
	 * @return	Returns the position for the green piece.
	 */
	public StartPosition getGreenStart(int i) { return _greenStart[i]; }
	/**
	 * Gets the starting track for black.
	 * @param i which starting stack to use.
	 * @return	Returns the position for the black piece.
	 */
	public StartPosition getBlackStart(int i) { return _blackStart[i]; }
	/**
	 * Gets the starting track for yellow.
	 * @param i which starting stack to use.
	 * @return	Returns the position for the yellow piece.
	 */
	public StartPosition getYellowStart(int i) { return _yellowStart[i]; }
	/**
	 * Gets the starting track for white.
	 * @param i which starting stack to use.
	 * @return	Returns the position for the white piece.
	 */
	public StartPosition getWhiteStart(int i) { return _whiteStart[i]; }

	/**
	 * Gets the middle track.
	 * @param i Which position of the track to use.
	 * @return Returns the track.
	 */
	public Position getMiddle(int i) { return _middle[i]; }
	/**
	 * Gets the left track.
	 * @param i Which position of the track to use.
	 * @return Returns the track.
	 */
	public Position getLeft(int i) { return _left[i]; }
	/**
	 * Gets the right track.
	 * @param i Which position of the track to use.
	 * @return Returns the track.
	 */
	public Position getRight(int i) { return _right[i]; }
	/**
	 * Gets the center track.
	 * @param i Which position of the track to use.
	 * @return Returns the track.
	 */
	public Position getCommon(int i) { return _right[i]; }

	/**
	 * Sets the reverse button position.
	 * 
	 * @param reverse Where to set the reverse to.
	 */
	public void setReverse(int reverse) {
		_reverse = reverse;
	}
	/**
	 * Gets the button position for reverse.
	 * 
	 * @return Returns the position of the reverse button.
	 */
	public int getReverse() { return _reverse; }
	
	/**
	 * Sets the plus2 button position.
	 * @param plus2 Where to set the plus2.
	 */
	public void setPlus2(int plus2){
		_plus2 = plus2;
	}
	
	/**
	 * Gets the button position for plus2.
	 * 
	 * @return Returns the position of the plus2 button.
	 */	
	public int getPlus2() { return _plus2; }
	
	/**
	 * Sets the plus5 button position.
	 * @param plus5 Where to set the plus5.
	 */
	public void setPlus5(int plus5){
		_plus5 = plus5;
	}
	
	/**
	 * Gets the button position for plus5.
	 * 
	 * @return Returns the position of the plus5 button.
	 */
	public int getPlus5() { return _plus5; }
	/**
	 * Gets the track.
	 * 
	 * @return Returns the current selected track.
	 */
	public int getTrack() { return _track; }
	/**
	 * Gets the turn.
	 * 
	 * @return Returns the current turn.
	 */
	public int getTurn(){return _turn;}

	/**
	 * Gets the left track
	 * @return Returns the left track.
	 */
	public Position[] leftTrack() {return _left;}
	/**
	 * Gets the right track
	 * @return Returns the right track.
	 */
	public Position[] rightTrack() {return _right;}
	/**
	 * Gets the middle track
	 * @return Returns the middle track.
	 */
	public Position[] middleTrack() {return _middle;}

	/**
	 * Gets the right JRadioButton
	 * @return Returns the right JRadioButton
	 */
	public JRadioButton getRightRB() {return _rightRB;}
	/**
	 * Gets the middle JRadioButton
	 * @return Returns the middle JRadioButton
	 */
	public JRadioButton getMiddleRB() {return _middleRB;}
	/**
	 * Gets the left JRadioButton
	 * @return Returns the left JRadioButton
	 */
	public JRadioButton getLeftRB() {return _leftRB;}

	/**
	 * Changes the game state in order.
	 */
	public void changeGameState() {
		switch(getTurn()){
		case 1:
			setTurn(2);
			break;
		case 2:
			if(Driver.getState()==2){
				setTurn(1);
			}else{setTurn(3);}
			break;
		case 3:
			if(Driver.getState()==3){
				setTurn(1);
			}else{setTurn(4);}
			break;
		case 4:
			if(Driver.getState()==4){
				setTurn(1);
			}else{setTurn(5);}
			break;
		case 5:
			if(Driver.getState()==5){
				setTurn(1);
			}else{setTurn(6);}
			break;
		case 6:
			setTurn(1);
			break;
		}
	}
}

package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import model.Board;
import model.Position;
import model.StartPosition;

/**
 * Creation of the board and stores the functionality of the GUI.
 * 
 * @author Jimmy
 * 
 */
public class Game implements Runnable, ActionListener {
	private Finch _finch;
	private int _state=0;
	private JButton _temp;
	public JButton _skipB = new JButton();
	public Board _board;
	private int _control = 0;
	public ArrayList<JButton> _buttons;
	public HashMap<String,JLabel> _score = new HashMap<String,JLabel>();

	/**
	 * Creation of a new board and sets an ActionListener.
	 */
	public Game(){
		_board = new Board();
		_board.setActionListener(this);
	}
	/**
	 * Creation of a new board and sets an ActionListener.
	 * 
	 * @param finch reference a new Finch.
	 */
	public Game(Finch finch) {
		this();
		_control = 1;
		_finch = finch;
		Timer _timer = new Timer(200, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controls();
			}
		});
		_timer.start();
	}
/**
 * Creates the JFrame, JPanel, JButton, and JLabels for the board and its layout.
 * 
 */
	@Override public void run() {
		
		JFrame window = new JFrame("Stage 2");
		
		Container c = window.getContentPane();
		
		c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));

		//Creation of the JPanels that store their respective components.
		JPanel start = new JPanel();
		start.setLayout(new BoxLayout(start, BoxLayout.X_AXIS));

		JPanel control = new JPanel();
		control.setLayout(new BoxLayout(control, BoxLayout.X_AXIS));

		JPanel startRed = new JPanel();
		startRed.setLayout(new BoxLayout(startRed, BoxLayout.Y_AXIS));

		JPanel startBlack = new JPanel();
		startBlack.setLayout(new BoxLayout(startBlack, BoxLayout.Y_AXIS));

		JPanel startWhite = new JPanel();
		startWhite.setLayout(new BoxLayout(startWhite, BoxLayout.Y_AXIS));

		JPanel startGreen = new JPanel();
		startGreen.setLayout(new BoxLayout(startGreen, BoxLayout.Y_AXIS));

		JPanel startYellow = new JPanel();
		startYellow.setLayout(new BoxLayout(startYellow, BoxLayout.Y_AXIS));

		JPanel startBlue = new JPanel();
		startBlue.setLayout(new BoxLayout(startBlue, BoxLayout.Y_AXIS));

		JPanel fork = new JPanel();
		fork.setLayout(new BoxLayout(fork, BoxLayout.X_AXIS));

		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

		JPanel middle = new JPanel();
		middle.setLayout(new BoxLayout(middle, BoxLayout.X_AXIS));

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

		JPanel score = new JPanel();
		score.setLayout(new BoxLayout(score, BoxLayout.X_AXIS));

		JPanel straightaway = new JPanel();
		straightaway.setAlignmentX(Component.CENTER_ALIGNMENT);
		straightaway.setLayout(new BoxLayout(straightaway, BoxLayout.Y_AXIS));

		//Creates the JLabels for the Scores
		_score.put("Red", new JLabel());
		_score.put("Blue", new JLabel());
		_score.put("Green", new JLabel());
		_score.put("Yellow", new JLabel());
		_score.put("Black", new JLabel());
		_score.put("White", new JLabel());
		
		//Sets the text for the skip button.
		_skipB.setText("Skip");
		_skipB.setEnabled(false);

		//Adds the JPanels to the Jframe
		c.add(score);
		c.add(start);
		c.add(control);
		c.add(fork);
		c.add(straightaway);
		
		start.add(startRed);
		
		//Adds the score labels to the JPanel and checks how many are needed with respect to the number of players.
		score.add(_score.get("Red"));
		score.add(new JSeparator());
		start.add(startBlue);
		score.add(_score.get("Blue"));
		score.add(new JSeparator());
		if(Driver.getState()>2){
			start.add(startGreen);
			score.add(_score.get("Green"));
			score.add(new JSeparator());
		}if(Driver.getState()>3){
			start.add(startWhite);
			score.add(_score.get("White"));
			score.add(new JSeparator());
		}if(Driver.getState()>4){
			start.add(startBlack);
			score.add(_score.get("Black"));
			score.add(new JSeparator());
		}if(Driver.getState()>5){
			start.add(startYellow);
			score.add(_score.get("Yellow"));
			score.add(new JSeparator());
		}

		//Adds the ActionListener to the skip button.
		Skip S = new Skip(_board);
		_skipB.addActionListener(S);
		
		//Adds the left, middle and right track into the fork JPanel.
		fork.add(left);
		fork.add(center);
		fork.add(right);
		
		//Adds the Skip and left, middle and right JRadioButtons into the control JPanel.
		control.add(_skipB);
		control.add(_board.getLeftRB());
		control.add(_board.getMiddleRB());
		control.add(_board.getRightRB());
		
		//Creates an ArrayList that stores all the button locations.
		_buttons = new ArrayList<JButton>();
		
		//Adds the ActionListeners to the JRadioButtons.
		LeftRadioHandler left1 = new LeftRadioHandler(_board);
		_board.getLeftRB().addActionListener(left1);
		MiddleRadioHandler middle1 = new MiddleRadioHandler(_board);
		_board.getMiddleRB().addActionListener(middle1);
		RightRadioHandler right1 = new RightRadioHandler(_board);
		_board.getRightRB().addActionListener(right1);
		
		//Checks the amount of players and creates the JButtons for it.
		if(Driver.getState()<4){
			for (int i=0; i<4; i++) {
				createStartButton(startRed, _board.getRedStart(i));
				createStartButton(startBlue, _board.getBlueStart(i));
				createStartButton(startGreen, _board.getGreenStart(i));
				createStartButton(startWhite, _board.getWhiteStart(i));
				createStartButton(startBlack, _board.getBlackStart(i));
				createStartButton(startYellow, _board.getYellowStart(i));
			}
		}else {
			for (int i=0; i<3; i++) {
				createStartButton(startRed, _board.getRedStart(i));
				createStartButton(startBlue, _board.getBlueStart(i));
				createStartButton(startGreen, _board.getGreenStart(i));
				createStartButton(startWhite, _board.getWhiteStart(i));
				createStartButton(startBlack, _board.getBlackStart(i));
				createStartButton(startYellow, _board.getYellowStart(i));
			}
		}
		
		//Adds the reverse, plus2 and plus5 buttons into a random JButton in the fork.
		for (int i=0; i<3; i++) {
			if(i == _board.getReverse()){
				if(_board.getTrack() == 0){
					createReverseButton(left, _board.getLeft(i));
					createTrackButton(center, _board.getMiddle(i));
					createTrackButton(right, _board.getRight(i));
				}
				else if(_board.getTrack() == 1){
					createTrackButton(left, _board.getLeft(i));
					createReverseButton(center, _board.getMiddle(i));
					createTrackButton(right, _board.getRight(i));
				}
				else if(_board.getTrack() == 2){
					createTrackButton(left, _board.getLeft(i));
					createTrackButton(center, _board.getMiddle(i));
					createReverseButton(right, _board.getRight(i));
				}
			}
			else if(i == _board.getPlus2()){
				if(_board.getTrack() == 0){
					createPlus2Button(left, _board.getLeft(i));
					createTrackButton(center, _board.getMiddle(i));
					createTrackButton(right, _board.getRight(i));
				}
				else if(_board.getTrack() == 1){
					createTrackButton(left, _board.getLeft(i));
					createPlus2Button(center, _board.getMiddle(i));
					createTrackButton(right, _board.getRight(i));
				}
				else if(_board.getTrack() == 2){
					createTrackButton(left, _board.getLeft(i));
					createTrackButton(center, _board.getMiddle(i));
					createPlus2Button(right, _board.getRight(i));
				}
			}	
			else if(i == _board.getPlus5()){
				if(_board.getTrack() == 0){
					createPlus5Button(left, _board.getLeft(i));
					createTrackButton(center, _board.getMiddle(i));
					createTrackButton(right, _board.getRight(i));
				}
				else if(_board.getTrack() == 1){
					createTrackButton(left, _board.getLeft(i));
					createPlus5Button(center, _board.getMiddle(i));
					createTrackButton(right, _board.getRight(i));
				}
				else if(_board.getTrack() == 2){
					createTrackButton(left, _board.getLeft(i));
					createTrackButton(center, _board.getMiddle(i));
					createPlus5Button(right, _board.getRight(i));
				}
			}	
			else{
				createTrackButton(left, _board.getLeft(i));
				createTrackButton(center, _board.getMiddle(i));
				createTrackButton(right, _board.getRight(i));
			}
		}
		
		//Adds the reverse, plus2 or plus5 into a random JButton in the center track.
		for (int i=3; i<14; i++) {
			if(i == _board.getReverse()){
				createReverseButton(straightaway, _board.getCommon(i));
			}
			else if(i == _board.getPlus2()){
				createPlus2Button(straightaway, _board.getCommon(i));
			}
			else if(i == _board.getPlus5()){
				createPlus5Button(straightaway, _board.getCommon(i));
			}
			else{
				createTrackButton(straightaway, _board.getCommon(i));
			}
		}
		
		//Adds the skip button into buttons ArrayList.
		_buttons.add(_skipB);
		//Sets the temp to the first button in the ArrayList.
		_temp = _buttons.get(0);
		//Updates the board with current information.
		update(_board.getBoard());

		//JFrame settings
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setSize(500, 550);
		window.setVisible(true);
		
		//If the user has choosen to use the Finch the mouse will be disabled.
		if(Driver._controls.equalsIgnoreCase("finch") && _control == 1){
			window.getGlassPane().addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});
			window.getGlassPane().addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {}
				public void mouseMoved(MouseEvent e) {}
			});
			window.getGlassPane().setVisible(true);
		}	
	}

	/**
	 * Creates the plus5 button.
	 * 
	 * @param panel
	 *            Which panel will the button be created in.
	 * @param pos
	 *            Which button will the button be created it.
	 */
	private void createPlus5Button(JPanel panel, Position pos) {
		createButton(panel, new Plus5Handler(pos));
	}

	/**
	 * Creates the plus2 button.
	 * 
	 * @param panel
	 *            Which panel will the button be created in.
	 * @param pos
	 *            Which button will the button be created it.
	 */
	private void createPlus2Button(JPanel panel, Position pos) {
		createButton(panel, new Plus2Handler(pos));
	}

	/**
	 * Creates the reverse button.
	 * 
	 * @param panel
	 *            Which panel will the button be created in.
	 * @param pos
	 *            Which button will the button be created it.
	 */
	private void createReverseButton(JPanel panel, Position pos) {
		createButton(panel, new ReverseHandler(pos));
	}

	/**
	 * Creates the start button.
	 * 
	 * @param panel
	 *            Which panel will the button be created in.
	 * @param pos
	 *            Which button will the button be created it.
	 */
	public void createStartButton(JPanel panel, StartPosition pos) {
		createButton(panel, new StartHandler(pos));
	}

	/**
	 * Creates the track button.
	 * 
	 * @param panel
	 *            Which panel will the button be created in.
	 * @param pos
	 *            Which button will the button be created it.
	 */
	public void createTrackButton(JPanel panel, Position pos) {
		createButton(panel, new TrackHandler(pos));
	}

	/**
	 * Creates the buttons with their ActionListeners.
	 * 
	 * @param panel
	 *            Which panel is the button going to be created in.
	 * @param listener
	 *            Which ActionListener will the button have.
	 */
	private void createButton(JPanel panel, ActionListener listener) {
		JButton b;
		b = new JButton();
		panel.add(b);
		b.addActionListener(listener);
		_buttons.add(b);		
	}
	
	/**
	 * Updates the board whenever something is done,
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		update(_board.getBoard());
	}

	/**
	 * Updates the board with the latest information.
	 * 
	 * @param board
	 *            The board to update.
	 */
	public void update(ArrayList<String> board) {
		
		//Sets the background colors for the score labels.
		_score.get("Red").setBackground(null);
		_score.get("Blue").setBackground(null);
		_score.get("Green").setBackground(null);
		_score.get("Yellow").setBackground(null);
		_score.get("Black").setBackground(null);
		_score.get("White").setBackground(null);

		//Checks the turn and sets the background for the respective player's score label
		if(_board.getTurn()==1){_score.get("Red").setBackground(Color.RED);}
		else if(_board.getTurn()==2){_score.get("Blue").setBackground(Color.BLUE);}
		else if(_board.getTurn()==3){_score.get("Green").setBackground(Color.GREEN);}
		else if(_board.getTurn()==4){_score.get("White").setBackground(Color.WHITE);}
		else if(_board.getTurn()==5){_score.get("Black").setBackground(Color.BLACK);}
		else if(_board.getTurn()==6){_score.get("Yellow").setBackground(Color.YELLOW);}
		
		//Checks how many players there are and updates their score respectively.
		_score.get("Red").setText(Driver._player1.getName()+"  Score: "+Driver._player1._score);
		_score.get("Red").setOpaque(true);
		_score.get("Blue").setText(Driver._player2.getName()+" Score: "+Driver._player2._score);
		_score.get("Blue").setOpaque(true);
		if(Driver.getState()>2){
			_score.get("Green").setText(Driver._player3.getName()+" Score: "+Driver._player3._score);
			_score.get("Green").setOpaque(true);
		}
		if(Driver.getState()>3){
			_score.get("White").setText(Driver._player4.getName()+" Score: "+Driver._player4._score);
			_score.get("White").setOpaque(true);
		}
		if(Driver.getState()>4){
			_score.get("Black").setText(Driver._player5.getName()+" Score: "+Driver._player5._score);
			_score.get("Black").setOpaque(true);
		}
		if(Driver.getState()>5){
			_score.get("Yellow").setText(Driver._player6.getName()+" Score: "+Driver._player6._score);
			_score.get("Yellow").setOpaque(true);
		}

		if (_control == 1){
			if(_board.getTurn()==1){_finch.setLED(Color.RED);}
			else if(_board.getTurn()==2){_finch.setLED(Color.BLUE);}
			else if(_board.getTurn()==3){_finch.setLED(Color.GREEN);}
			else if(_board.getTurn()==4){_finch.setLED(Color.WHITE);}
			else if(_board.getTurn()==5){_finch.setLED(Color.BLACK);}
			else if(_board.getTurn()==6){_finch.setLED(Color.YELLOW);}
		}
		
		//Sets the skip to 0 so no one is skipped by default.
		_board._skip = 0;
		
		//Checks the board and if it is a player's turn it will enable the buttons their are allowed to use and disable the rest.
		for (int i=0; i<_buttons.size()-1; i++) {
			_buttons.get(i).setText(board.get(i));
			if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='R'){
				_buttons.get(i).setBackground(Color.RED);
				if(_board.getTurn()!=1){
					_buttons.get(i).setEnabled(false);
				}else {_buttons.get(i).setEnabled(true);_board._skip=_board._skip +1;}
			}
			if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='B'){
				_buttons.get(i).setBackground(Color.BLUE);
				if(_board.getTurn()!=2){
					_buttons.get(i).setEnabled(false);
				}else {_buttons.get(i).setEnabled(true);_board._skip=_board._skip +1;}
			}
			if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='G'){
				_buttons.get(i).setBackground(Color.GREEN);
				if(_board.getTurn()!=3){
					_buttons.get(i).setEnabled(false);
				}else {_buttons.get(i).setEnabled(true);_board._skip=_board._skip +1;}
			}
			if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='W'){
				_buttons.get(i).setBackground(Color.WHITE);
				if(_board.getTurn()!=4){
					_buttons.get(i).setEnabled(false);
				}else {_buttons.get(i).setEnabled(true);_board._skip=_board._skip +1;}
			}
			if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='K'){
				_buttons.get(i).setBackground(Color.BLACK);
				if(_board.getTurn()!=5){
					_buttons.get(i).setEnabled(false);
				}else {_buttons.get(i).setEnabled(true);_board._skip=_board._skip +1;}
			}
			if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='Y'){
				_buttons.get(i).setBackground(Color.YELLOW);
				if(_board.getTurn()!=6){
					_buttons.get(i).setEnabled(false);
				}else {_buttons.get(i).setEnabled(true);_board._skip=_board._skip +1;}
			}
			if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='['){
				_buttons.get(i).setBackground(null);
				_buttons.get(i).setEnabled(false);
			}
		}	
		
		//Checks the skip button and if it allows for a skip, enable it.
		if(_board._skip!=0){
			_skipB.setEnabled(false);
		}
		if (_board._skip==0){
			_skipB.setEnabled(true);
			for (int i=0; i<_buttons.size()-1; i++) {
				if (_buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='R' || _buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='B' || _buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='G' || _buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='K' || _buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='W' || _buttons.get(i).getText().charAt(_buttons.get(i).getText().length()-2)=='Y'){
					_buttons.get(i).setEnabled(true);
				}
			}
		}
		
		//Checks if a player has won and closes the game if there is a winner.
		if(Driver._player1.win()){
			Driver._player1.gameWin();
			System.exit(0);
		}
		if(Driver._player2.win()){
			Driver._player2.gameWin();
			System.exit(0);
		}
		if(Driver.getState()>2){
			if(Driver._player3.win()){
				Driver._player3.gameWin();
				System.exit(0);
			}
		}
		if(Driver.getState()>3){
			if(Driver._player4.win()){
				Driver._player4.gameWin();
				_board = new Board();
				System.exit(0);
			}
		}
		if(Driver.getState()>4){
			if(Driver._player5.win()){
				Driver._player5.gameWin();
				_board = new Board();
				System.exit(0);
			}
		}
		if(Driver.getState()>5){
			if(Driver._player6.win()){
				Driver._player6.gameWin();
				_board = new Board();
				System.exit(0);
			}
		}
	}
	
	/**
	 * Controls what the game does with respect to the finch controls.
	 */
	public void controls(){
		int lastIndex = (_buttons.size()-1);
		//Gets the state of the buttons that are enabled.
		while(!_buttons.get(_state).isEnabled()){
			if(_state == lastIndex){_state = 0;}
			else{_state++;}
		}
		
		//Focuses the selection onto a button.
		_buttons.get(_state).requestFocus();
		//Sets the temp to the button focused on.
		_temp = _buttons.get(_state);
		
		//If the Finch detects an obstacle on the right it will focus to the next button.
		if(_finch.isObstacleRightSide()){
			if(_state == lastIndex){_state = 0;}
			else{_state++;}
			while(!_buttons.get(_state).isEnabled()){
				if(_state == lastIndex){_state = 0;}
				else{_state++;}
			}
			_buttons.get(_state).requestFocus();
			_temp = _buttons.get(_state);
		}
		//If the Finch detects an obstacle on the left side it will focus to the previous button.
		else if(_finch.isObstacleLeftSide()){
			if(_state == 0){_state = lastIndex;}
			else {_state--;}
			while(!_buttons.get(_state).isEnabled()){
				if(_state == 0){_state = lastIndex;}
				else{_state--;}
			}
			_buttons.get(_state).requestFocus();
			_temp = _buttons.get(_state);

		}
		
		//If the Finch has its wings tilted to the left the left track is picked.
		if(_finch.isLeftWingDown()){
			_board.getLeftRB().setSelected(true);
			_board.getMiddleRB().setSelected(false);
			_board.getRightRB().setSelected(false);
		}
		//If the Finch is not tilted the middle track is picked.
		else if(_finch.isRightWingDown()){
			_board.getLeftRB().setSelected(false);
			_board.getMiddleRB().setSelected(false);
			_board.getRightRB().setSelected(true);
		//If the Finch has has its wings tilted to the right the right track is picked.
		}else if(_finch.isFinchLevel()){
			_board.getLeftRB().setSelected(false);
			_board.getMiddleRB().setSelected(true);
			_board.getRightRB().setSelected(false);
		}
		
		//If the Finch is shaken it will click the button focused on.
		if(_finch.isShaken()){
			_temp.doClick();
		}
	}
}

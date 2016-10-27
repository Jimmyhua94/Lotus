package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import model.Player;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
/**
 * Starts the board and takes the required arguments
 * @author Jimmy
 *
 */
public class Driver {
	public static String _controls;
	public static Player _player1;
	public static Player _player2;
	public static Player _player3;
	public static Player _player4;
	public static Player _player5;
	public static Player _player6;
	private static int _playercount=2;
	/**
	 * Checks the arguments inputed and starts the board if the arguments are correct.
	 * @param args The arguments required to play the game. The first argument must be either mouse or finch, followed by a minimum of two player names up to six.
	 */
	public static void main(String[] args) {
		//Code that may throw an exception.
		try {
			//Checks if the controller arguments are correct and creates a error.
			if (!args[0].equalsIgnoreCase("mouse") && !args[0].equalsIgnoreCase("finch")){
				error("You have entered an incorrect controller argument."+'\n'+"Please enter: mouse or finch");
			}
			//Checks if there are 2 or more players and creates a error.
			else if (args.length<3){
				error("Please enter a minimum of two players."+'\n'+"i.e. controller player1 player2");
			}
			else if(args.length==3){
				_controls = args[0];
				_player1 = new Player(args[1]);
				_player2 = new Player(args[2]);
				_playercount=2;
			}else if(args.length==4){
				_controls = args[0];
				_player1 = new Player(args[1]);
				_player2 = new Player(args[2]);
				_player3 = new Player(args[3]);
				_playercount=3;
			}else if(args.length==5){
				_controls = args[0];
				_player1 = new Player(args[1]);
				_player2 = new Player(args[2]);
				_player3 = new Player(args[3]);
				_player4 = new Player(args[4]);
				_playercount=4;
			}else if(args.length==6){
				_controls = args[0];
				_player1 = new Player(args[1]);
				_player2 = new Player(args[2]);
				_player3 = new Player(args[3]);
				_player4 = new Player(args[4]);
				_player5 = new Player(args[5]);
				_playercount=5;
			}else if(args.length==7){
				_controls = args[0];
				_player1 = new Player(args[1]);
				_player2 = new Player(args[2]);
				_player3 = new Player(args[3]);
				_player4 = new Player(args[4]);
				_player5 = new Player(args[5]);
				_player6 = new Player(args[6]);
				_playercount=6;
			// Checks if you have entered more then 6 players and creates an error
			}else if(args.length>7){
				error("Please enter a maximum of six players."+'\n'+"i.e. controller, player1, player2, player3, player4, player5, player6");
			}
			//If mouse was chosen as the controller the game starts.
			if (args[0].equalsIgnoreCase("mouse") && args.length>=3){
				SwingUtilities.invokeLater(new Game());
			}
			//If finch was chosen as the controller the game starts with a new finch.
			else if (args[0].equalsIgnoreCase("finch")&& args.length>=3){
				SwingUtilities.invokeLater(new Game(new Finch()));
			}
		}
		//If an ArrayIndexOutOfBoundsException was caught create an error.
		catch (ArrayIndexOutOfBoundsException e){
			error("You have not entered any arguments.");
		}
	}
	/**
	 * Gets the amount of players.
	 * @return Returns how much players there are.
	 */
	public static int getState(){
		return _playercount;
	}
	/**
	 * Creates a new JOptionPane that Displays a JDialog for an error.
	 * @param s The message for the JDialog.
	 */
	public static void error(String s){
		JOptionPane pane = new JOptionPane(s);
		final JDialog dialog = pane.createDialog("Invalid Entry");
		//Closes the JDialog after 5000ms
		Timer timer = new Timer(5000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				System.exit(0);
			}
		});
		timer.setRepeats(false);
		timer.start();
		dialog.setVisible(true);
	}

}

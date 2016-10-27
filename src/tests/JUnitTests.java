package tests;

import java.awt.Color;
import java.util.Stack;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.*;
import model.*;

public class JUnitTests {
	private Game _game = new Game();
	private Board _board = _game._board;
	private Position p = new Position(0,3, _board);
	String[] args = new String[7];
	private Piece r = new Piece("R");
	private Piece y = new Piece("R");
	private Piece b = new Piece("R");
	private Piece g = new Piece("G");
	private Piece k = new Piece("K");
	private Piece w = new Piece("W");
	
	public JUnitTests(){
		args[0] = new String("mouse");
		args[1] = new String("player1");
		args[2] = new String("player2");
		args[3] = new String("player3");
		args[4] = new String("player4");
		args[5] = new String("player5");
		args[6] = new String("player6");
		Driver.main(args);
		_game.run();
	}
	
	@Test
	public void testScore(){
		Piece p = new Piece("R");
		Driver._player1.addScore();
		Driver._player1.addScore();
		Driver._player1.addScore();
		int expected = 4;
		_board.score(p);
		int actual = Driver._player1._score; 
		assertTrue("Created a new red piece and added 3 to Red's score.  We then called the score method and execpted Red Score to be "+expected+"But we actually got" +actual, 
				expected == actual);
	}
	
	@Test
	public void score(){
		Piece x = _board.getRedStart(1)._stack.pop();
		_board.score(x);
		int actual = Driver._player1._score;
		int expected = 1;
		assertTrue("Scoreing one point for red, expected a score of "+expected+", but it actually was " +actual, 
				expected == actual);
	}
	
	// Board.setTurn Test
	@Test
	public void testSetTurn(){
		_board.setTurn(3);
		int expected = 3;
		int actual = _board.getTurn();
		assertTrue("Set _turn to "+expected+" but it actually was " +actual, 
				expected == actual);
		
	}
		// Board.getRedStart Test
				@Test
				public void testGetRedStart(){
				int actual = _board.getRedStart(3).height();
				int expected =4;
					assertTrue("Getting the Red's start position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
					
				}
				// Board.getBlueStart Test
				@Test
				public void testGetBlueStart(){
				int actual = _board.getBlueStart(2).height();
				int expected =3;
					assertTrue("Getting the Blue's start position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
					
				}
				// Board.getGreenStart Test
				@Test
				public void testGetGreenStart(){
				int actual = _board.getGreenStart(1).height();
				int expected =2;
					assertTrue("Getting the Green's start position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
					
				}
				// Board.getWhiteStart Test
				@Test
				public void testGetWhiteStart(){
				int actual = _board.getWhiteStart(0).height();
				int expected =1;
					assertTrue("Getting the White's start position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
					
				}
				// Board.getblackStart Test
				@Test
				public void testGetBlackStart(){
				int actual = _board.getBlackStart(2).height();
				int expected =3;
					assertTrue("Getting the Black's start position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
					
				}
				// Board.getYellowStart Test
				@Test
				public void testGetYellowStart(){
				int actual = _board.getYellowStart(2).height();
				int expected =3;
					assertTrue("Getting the Yellow's start position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
					
				}
				// Board.getMiddle Test
				@Test
				public void testGetMiddle(){
				int actual = _board.getMiddle(2).height();
				int expected =0;
					assertTrue("Getting the second middle track position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
					
				}
				
				// Board.getLeft Test
				@Test
				public void testGetLeft(){
					_board.getLeft(2).putOn(r);
					_board.getLeft(2).putOn(g);
				int actual = _board.getLeft(2).height();
				int expected =2;
					assertTrue("Getting the third Left track position which should start with a height of"+expected+", but it actually was " +actual, 
							expected == actual);
				}
				
				// Board.getRight Test
				@Test
				public void testGetRight(){
					_board.getRight(1).putOn(k);
					_board.getRight(1).putOn(r);
					_board.getRight(1).putOn(w);
				int expected = 3;
				int actual = _board.getRight(1).height();
				assertTrue("Getting the second Right track position which should start with a height of"+expected+", but it actually was " +actual, 
						expected == actual);
				}
				
				// Board.getCommon Test
				@Test
				public void testGetCommon(){
					_board.getCommon(3).putOn(k);
					_board.getCommon(3).putOn(r);
					_board.getCommon(3).putOn(w);
				int expected = 3;
				int actual = _board.getCommon(3).height();
				assertTrue("Getting the first common track position which should start with a height of"+expected+", but it actually was " +actual, 
						expected == actual);
				}
				
				@Test
				public void setReverse(){
					_board.setReverse(5);
					int expected = 5;
					int actual = _board.getReverse();
					assertTrue("Setting reverse to button 5 and expected "+expected+", but it actually was " +actual, 
							expected == actual);
				}
				
				@Test
				public void setPlus2(){
					_board.setPlus2(5);
					int expected = 5;
					int actual = _board.getPlus2();
					assertTrue("Setting plus2 to button 5 and expected "+expected+", but it actually was " +actual, 
							expected == actual);
				}
				
				@Test
				public void setPlus5(){
					_board.setPlus5(5);
					int expected = 5;
					int actual = _board.getPlus5();
					assertTrue("Setting plus5 to button 5 and expected "+expected+", but it actually was " +actual, 
							expected == actual);
				}
				
				//Board.ChangeGameState
				@Test
				public void testChangeGameState(){
					_board.setTurn(Driver.getState());
					_board.changeGameState();
					int expected = 1;
					int actual = _board.getTurn();
					assertTrue("Setting the turn to the last player and running the changeGameState method I expect it to be the "+expected+"st Players turn, but it actually was" +actual, 
							expected == actual);
				}
				//Driver.getState
				@Test
				public void testGetState(){
					int expected = 6;
					int actual = Driver.getState();
					assertTrue("We have setup our game with 6 players I expect the state to be "+expected+", but it actually was" +actual, 
							expected == actual);
				}
				//Piece.setPosition and Piece.getPosition
				@Test
				public void testSetGetPosition(){
					r.setPosition(3);
					int expected = 3;
					int actual = r.getPosition();
					assertTrue("Set piece 'r' to a position of "+expected+", but it actually was" +actual, 
							expected == actual);
				}
				//Piece.setTrack and Piece.getTrack
				@Test
				public void testSetGetTrack(){
					r.setTrack(_board.leftTrack());
					Position[] expected = _board.leftTrack();
					Position[] actual = r.getTrack();
					assertTrue("Set piece 'r' to a the lefttrack but it actually was" +actual, 
							expected == actual);
				}
				//Piece.setTrack and Piece.getTrack
				@Test
				public void testSetGetStart(){
					r.setStart(p._stack);
					Stack<Piece> expected = p._stack;
					Stack<Piece> actual = r.getStart();
					assertTrue("Set piece 'r' to a the lefttrack but it actually was" +actual, 
							expected == actual);
				}
				//Piece.getPlayer
				@Test
				public void testGetPlayer(){
					String expected = "G";
					String actual = g.getPlayer();
					assertTrue("Set piece 'r' to a the lefttrack but it actually was" +actual, 
							expected.equals(actual));
				}
				//Game.update1
				@Test
				public void testUpdate1(){
					_board.setTurn(3);
					_game.update(_board.getBoard());
					Color expected = Color.GREEN;
					Color actual = _game._score.get("Green").getBackground();
					assertTrue("Setting it to Green's turn I expected the JLabel to be color"+expected+"but it was "+actual,expected == actual);
				}
				//Game.update2
				@Test
				public void testUpdate2(){
					_board.getLeft(8).putOn(w);
					Color expected = Color.WHITE;
					Color actual = _game._buttons.get(32).getBackground();
					assertTrue("Adding a White piece to track position 8/button 32 expecting the color to be" +expected+ "but it was "+actual,expected == actual);
				}
				//Player.addScore
				@Test
				public void testAddScore(){
					Driver._player4._score=3;
					Driver._player4.addScore();
					int expected = 4;
					int actual = Driver._player4._score;
					assertTrue("Setting White players score to 3 and then calling addScore I expect the score to be" +expected+ "but it was "+actual,
							expected == actual);
				}
				//Player.win
				@Test
				public void testWin(){
					Driver._player5._score=10;
					boolean expected = true;
					boolean actual = Driver._player5.win();
					assertTrue("Setting Black players score to 10 and then calling win I expect it to be" +expected+ "but it was "+actual,
							expected == actual);
				}
				//Player.gameWin
				@Test
				public void testGameWin(){
					Driver._player5.gameWin();
					String expected = "The Game Is Over";
					String actual = Driver._player5.dialog.getTitle();
					assertTrue("Calling gameWin() I expect that the dialog text will be" +expected+ "but it was "+actual,
							expected == actual);
				}
				//Position.movePlus5
//				@Test
//				public void testMovePlus5(){
//					_board.getCommon(6).putOn(y);
//					
//					System.out.println(_board.getCommon(1).height());
//					System.out.println(_board.getCommon(2).height());
//					System.out.println(_board.getCommon(3).height());
//					System.out.println(_board.getCommon(4).height());
//					System.out.println(_board.getCommon(5).height());
//					System.out.println(_board.getCommon(6).height());
//					System.out.println(_board.getCommon(7).height());
//					System.out.println(_board.getCommon(8).height());
//					System.out.println(_board.getCommon(9).height());
//					System.out.println(_board.getCommon(10).height());
//					System.out.println(_board.getCommon(11).height());
//					System.out.println(_board.getCommon(12).height());
//					System.out.println(_board.getCommon(13).height());
//
//					_board.getCommon(6).movePlus5();
//					System.out.println(_board.getCommon(1).height());
//					System.out.println(_board.getCommon(2).height());
//					System.out.println(_board.getCommon(3).height());
//					System.out.println(_board.getCommon(4).height());
//					System.out.println(_board.getCommon(5).height());
//					System.out.println(_board.getCommon(6).height());
//					System.out.println(_board.getCommon(7).height());
//					System.out.println(_board.getCommon(8).height());
//					System.out.println(_board.getCommon(9).height());
//					System.out.println(_board.getCommon(10).height());
//					System.out.println(_board.getCommon(11).height());
//					System.out.println(_board.getCommon(12).height());
//					System.out.println(_board.getCommon(13).height());
//
//					int expected = 1;
//					int actual = _board.getCommon(13).height();
//					assertTrue("Putting a piece on Position 8 and moving it five the height of position 13 should equal " +expected+ "but it was "+actual,
//							expected == actual);
//					
//					
//				}
				/*//Position.movePlus2
				@Test
				public void testMovePlus2(){
					_board.getCommon(7).putOn(b);
					_board.getCommon(7).movePlus2();
					int expected = 1;
					int actual = _board.getCommon(9).height();
					assertTrue("Calling gameWin() I expect that the dialog text will be" +expected+ "but it was "+actual,
							expected == actual);
				}
				//Position.reverseTop
				@Test
				public void testReverseTop(){
					r.setPosition(8);
					_board.getCommon(8).putOn(r);
					_board.getCommon(8).putOn(y);
					_board.getCommon(8).reverseTop();
					Piece expected = r;
					Piece actual = _board.getCommon(6).peekTop();
					assertTrue("Calling gameWin() I expect that the dialog text will be" +expected+ "but it was "+actual,
							expected == actual);
				}*/
}

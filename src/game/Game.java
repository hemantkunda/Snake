package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import board.*;
import display.*;
import snake.*;

/**
 * The Game class handles the setup and game-ending scenarios of Snake.
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class Game implements ActionListener{
   private BoardDisplay display;
   private Board board;
   private Snake snake;
   private Timer timer;
   
   /**
    * Creates and runs a new Game.
    * @param args Command line arguments
    */
   public static void main(String[] args) {
      Game game = new Game();
      game.run();
   }
   
   /**
    * Creates a new "instance" of a Game.
    * A Snake, Board, and BoardDisplay are created in succession; the Board's display
    * and the BoardDisplay's game are then set.
    */
   private void initializeGame() {
      snake = new Snake();
      board = new Board(snake);
      display = new BoardDisplay(board, snake);
      board.setDisplay(display);
      display.setGame(this);
   }
   
   /**
    * Initializes a new Game and creates a Timer that sends ActionEvents to
    * the BoardDisplay.
    */
   public void run() {
      initializeGame();
      timer = new Timer(200, display);
      timer.start();
   }
   
   /**
    * Called once the Game is over. A popup dialogue is created, showing the player's
    * score (arbitrarily determined to be 20 * the snake's length - 3.
    */
   public void gameOverScreen() {
      String message = "Your score was " + 20 * (snake.length() - 3) + " points!";
      JOptionPane.showMessageDialog(null, message, "GAME OVER", 
            JOptionPane.INFORMATION_MESSAGE);
   }
   
   /**
    * Called manually by the BoardDisplay when it detects that the Game is over.
    * First, the Timer is stopped; then, the end game dialogue is created.
    * 
    * @param e the Action indicating a game over scenario
    */
   public void actionPerformed(ActionEvent e) {
      System.out.println(e.getActionCommand());
      timer.stop();
      gameOverScreen();
   }
}

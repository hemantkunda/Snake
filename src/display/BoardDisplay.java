package display;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import board.Board;
import board.BoardPiece;
import board.Location;
import board.Direction;
import game.Game;
import snake.Snake;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The BoardDisplay class is used to display the contents of the game board - 
 * specifically the Snake and Food.
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class BoardDisplay implements ActionListener{
   private Board board;
   private JPanel[][] grid;
   private JFrame frame;
   private Snake snake;
   private Game game;
   

   /**
    * Constructs a new BoardDisplay object with the provided Board and Snake, then
    * sets up
    * @param board
    * @param snake
    */
   public BoardDisplay(Board board, Snake snake) {
      this.snake = snake;
      this.board = board;

      grid = new JPanel[20][25];

      // Schedule a job for the event-dispatching thread:
      // creating and showing this application's GUI.
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
               // to make buttons fully colored
               UIManager.setLookAndFeel(
                     UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
               e.printStackTrace();
            }
            createAndShowGUI();
         }
      });

      // Wait until display has been drawn
      try {
         while (frame == null || !frame.isVisible())
            Thread.sleep(1);
      } catch (InterruptedException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }

   /**
    * Create the GUI and show it. For thread safety, this method should be
    * invoked from the event-dispatching thread.
    */
   private void createAndShowGUI() {
      // Create and set up the window.
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new GridLayout(20, 25));
      frame.setTitle("Snake");
      frame.addKeyListener(snake);
      frame.setFocusable(true);

      // Create each square as a button.
      for (int row = 0; row < grid.length; row++)
         for (int col = 0; col < grid[row].length; col++) {
            JPanel panel = new JPanel();
            panel.setOpaque(true);
            // panel.setBackground(new Color(155, 145, 115));
            panel.setBackground(new Color(110, 85, 55));
            panel.setPreferredSize(new Dimension(30, 30));
            frame.getContentPane().add(panel);
            grid[row][col] = panel;
         }

      // Show the pieces
      showBoard();

      // Display the window.
      frame.pack();
      frame.setVisible(true);
   }

   /**
    * Sets the BoardDisplay's Game to the provided Game.
    * 
    * @param game the BoardDisplay's new Game
    */
   public void setGame(Game game) {
      this.game = game;
   }
   
   /**
    * Repaints and revalidates every Location that holds a BoardPiece, then repaints
    * and revalidates the JFrame.
    */
   public void showBoard() {
      
      for (Location loc : board.snakeLocs) {
         JPanel panel = grid[loc.x()][loc.y()];
         BoardPiece piece = board.get(loc);
         
         panel.removeAll();
         
         panel.revalidate();
         panel.repaint();
         
         Icon icon = null;
         if (piece != null) {
            // System.out.println(loc);
            icon = new ImageIcon(piece.getImageFileName());
            //System.out.println(piece);
         }
         JLabel label = new JLabel("", icon, JLabel.CENTER);
         panel.add(label, BorderLayout.CENTER);
      }
      Location f = board.foodLoc;
      JPanel panel = grid[f.x()][f.y()];
      BoardPiece piece = board.get(f);
      
      panel.removeAll();
      panel.revalidate();
      panel.repaint();
      
      Icon icon = null;
      if (piece != null) {
         // System.out.println(loc);
         icon = new ImageIcon(piece.getImageFileName());
         System.out.println(piece);
      }
      
      JLabel label = new JLabel("", icon, JLabel.CENTER);
      panel.add(label, BorderLayout.CENTER);
      System.out.println("_____________");
      
      frame.revalidate();
      frame.repaint();

   }
   
   /**
    * Gets the JPanel at the specified Location
    * 
    * @param l the Location of the desired JPanel
    * @return the JPanel located at the provided Location
    */
   public JPanel getPanel(Location l) {
      return grid[l.x()][l.y()];
   }

   /**
    * Sets the JFrame's title to the provided String
    * @param title the JFrame's new title
    */
   public void setTitle(String title) {
      frame.setTitle(title);
   }

   /**
    * Sets the BoardDisplay's board to the provided Board object.
    * 
    * @param board the BoardDisplay's new Board
    */
   public void setBoard(Board board) {
      this.board = board;
   }

   /**
    * Called whenever the BoardDisplay receives an Action from the Game's Timer.
    * If the Snake moved forwards successfully, the UI is redrawn; otherwise, an Action
    * is sent to the Game indicating that the game is over.
    * 
    * @param event the periodic event sent by the Timer
    */
   public void actionPerformed(ActionEvent event) {
      if (board.moveSnake()) {
         game.actionPerformed(new ActionEvent(this, 0, "ENDGAME"));
      }
      showBoard();
   }
}
package board;

import java.util.LinkedList;

import javax.swing.JPanel;

import display.BoardDisplay;
import game.Game;
import snake.Body;
import snake.Head;
import snake.Segment;
import snake.Snake;
import snake.Tail;

/**
 * Represents the virtual board that the Snake lies on.
 * The Board is responsible for moving the Snake and keeping track of its location.
 * As a result, it also is in charge of determining when the Game is over.
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class Board 
{
   private BoardPiece[][] grid;
   private int row;
   private int col;
   private Snake snake;
   public LinkedList<Location> snakeLocs;
   private boolean isGameOver;
   private BoardDisplay display;
   public Location foodLoc;
   private Game game;
   
   /**
    * Makes a new Board object that holds an 20x25 grid.
    * The Snake is put into the board at the Locations (10, 12), (11, 12), and (12, 12).
    * This means it's oriented vertically and facing upwards.
    */
   public Board(Snake snake)
   {
      grid = new BoardPiece[20][25];
      isGameOver = false;
      this.row = 20;
      this.col = 25;
      this.snake = snake;
      snake.setBoard(this);
      this.snakeLocs = new LinkedList<Location>();
      snakeLocs.add(new Location(10, 12));
      snakeLocs.add(new Location(11, 12));
      snakeLocs.add(new Location(12, 12));
      
      updateSnake(true);
   }
   
   /**
    * Updates the Snake with its new body location information.
    * A new Head and Tail are placed into the Board in their respective locations (found
    * using snakeLocs).  Then, each Body is placed into the Board in its corresponding
    * location (again, using snakeLocs).
    * Finally, if the Snake has eaten a piece of Food this round, a new piece of Food
    * is generated.
    * 
    * @param eaten true if the Snake ate a piece of food this cycle;
    *              false otherwise
    */
   public void updateSnake(boolean eaten) {
      
      Head head = new Head(snakeLocs.getFirst(), this);
      put(head, snakeLocs.getFirst());
      Tail tail = new Tail(snakeLocs.getLast(), this);
      put(tail, snakeLocs.getLast());
      LinkedList<Body> body = new LinkedList<Body>();
      for (int i = 1; i < snakeLocs.size() - 1; i++) {
         Body b = new Body(snakeLocs.get(i), this);
         body.add(b);
         put(b, snakeLocs.get(i));
      }
      snake.setHead(head);
      snake.setTail(tail);
      snake.setBody(body);    
      if (eaten) {
         generateFood();
      }
   }
   
   /**
    * Moves the Snake forward one Location in the Direction that it is facing 
    * and returns false.
    * If the Snake cannot move forward for whatever reason (either it would hit itself
    * or leave the boundaries of the Board), then true is returned instead and the Game 
    * is understood to be over.
    * 
    * @return true if the Snake failed to moved forward 
    *         false otherwise
    */
   public boolean moveSnake() {
      Location inFront = snakeLocs.getFirst().inFront(snake.getDirection());
      if (!isValid(inFront)) {
         return true;
      }
      BoardPiece piece = get(inFront);
      boolean eaten = false;
      snakeLocs.addFirst(inFront);
      for (Location l : snakeLocs) {
         remove(l);
      }
      if (piece instanceof Food) {
         System.out.println("eat!");
         snake.incrementLength();
         eaten = true;
      }
      else if (piece instanceof Segment) {
         return true;
      }
      else {
         Location l = snakeLocs.removeLast();
         JPanel p = display.getPanel(l);
         p.removeAll();
         p.revalidate();
      }
      
      updateSnake(eaten);
      return false;
   }
   
   /**
    * Sets the Board's display to the provided BoardDisplay object.
    * 
    * @param display the Board's new display
    */
   public void setDisplay(BoardDisplay display) {
      this.display = display;
   }
   
   /**
    * Generates a piece of Food at a random Location on the Board. If the generated
    * Location is occupied, the method is called again.
    */
   public void generateFood() {
      int x = (int)(Math.random() * row);
      int y = (int)(Math.random() * col);
      Location l = new Location(x, y);
      if (isEmpty(l)) {
         System.out.println("Food spawned at: " + l);
         System.out.println("----------");
         Food food = new Food(l, this);
         put(food, l);
         foodLoc = l;
      }
      else {
         System.out.println("Respawning food...");
         generateFood();
      }
   }
   
   /**
    * Returns the x dimension of the Board.
    * 
    * @return the length of the Board in the x direction (vertical)
    */
   public int x() {
      return row;
   }
   
   /**
    * Returns the y dimension of the Board.
    * 
    * @return the length of the Board in the y direction (horizontal)
    */
   public int y() {
      return col;
   }
   
   /**
    * Gets the BoardPiece located at the provided Location object.
    * 
    * @param l the Location to query
    * 
    * @return the BoardPiece stored at the specified coordinates
    */
   public BoardPiece get(Location l)
   {
      return (grid[l.x()][l.y()]);
   }
  
   /**
    * Returns the BoardPiece at the specified Location 
    * and replaces it with null.
    * 
    * @param l where the removal should occur
    * 
    * @return the BoardPiece previously at Location l
    */ 
   public BoardPiece remove(Location l)
   {
      return put(null, l);
   }
   
   /**
    * Puts the specified Segment at the specified 
    * Location and returns whatever was originally at the Location.
    * 
    * @param obj the BoardPiece to be placed
    * @param l the Location to store the BoardPiece
    * 
    * @return the BoardPiece previously at Location l
    */
   public BoardPiece put(BoardPiece obj, Location l)
   {
      BoardPiece current = grid[l.x()][l.y()];
      grid[l.x()][l.y()] = obj;
      return current;
   }

   /**
    * Checks a Location to see if there is a BoardPiece located there.
    * 
    * @param l the Location to be checked
    * @return true if the Board has a BoardPiece stored there
    *         false otherwise
    */
   public boolean isEmpty(Location l) {
      if (0 <= l.x() && l.x() < row) {
         if (0 <= l.y() && l.y() < col) {
            return get(l) == null;
         }
      }
      return false;
   }
   
   /**
    * Checks to see if the provided Location falls within the boundaries of the Board.
    * 
    * @param l the Location to be checked
    * 
    * @return true if the Location's coordinates both fall between 0 and the respective
    * Board dimension
    *         false otherwise
    */ 
   public boolean isValid(Location l)
   {
      if (0 <= l.x() && l.x() < row)
      {
         if (0 <= l.y() && l.y() < col)
         {
            return true;    
         }
      }
      return false;
   }
}
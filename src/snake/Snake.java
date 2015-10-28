package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import board.Board;
import board.Location;
import board.Direction; 

/**
 * The Snake class represents the Snake slithering around on the Board.  
 * The main purpose of this class is to detect arrow key presses, which change the
 * direction of the Snake's motion.
 * 
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class Snake implements KeyListener {
   
   private Direction dir;
   private Head head;
   private LinkedList<Body> body;
   private Tail tail;
   private int length;
   
   private Board board;
   
   /**
    * Creates a new Snake facing upwards with a length of 3.
    */
   public Snake() {
      dir = Direction.UP;
      
      length = 3;
      
   }
   
   /**
    * Sets the Snake's Board to the provided Board.
    * 
    * @param board the Snake's new Board
    */
   public void setBoard(Board board) {
      this.board = board;
   }
   
   /**
    * Returns the Location of the Snake's Head.
    * 
    * @return the Location of the Snake's Head
    */
   public Location getHeadLoc() {
      return head.getLocation();
   }
   
   /**
    * Increases the Snake's length by 1.
    */
   public void incrementLength() {
      length++;
   }
   
   /**
    * Gets the length of the Snake.
    * 
    * @return the Snake's length
    */
   public int length() {
      return length;
   }
   
   /**
    * Sets the Direction of the Snake to the specified Direction.
    * 
    * @param d the Snake's new Direction
    */
   public void setDirection(Direction d) {
      dir = d;
   }
   
   /**
    * Gets the Snake's Direction of motion.
    * 
    * @return the Direction of the Snake
    */
   public Direction getDirection() {
      return dir;
   }
   
   /**
    * Sets the Snake's Head to the provided Head object.
    * 
    * @param head the Snake's new Head
    */
   public void setHead(Head head) {
      this.head = head;
   }
   
   /**
    * Sets the Snake's Body to the provided List of Body segments.
    * 
    * @param body the Snake's new Body
    */
   public void setBody(LinkedList<Body> body) {
      this.body = body;
   }
   
   /**
    * Sets the Snake's Tail to the provided Tail object.
    * 
    * @param tail the Snake's new Tail
    */
   public void setTail(Tail tail) {
      this.tail = tail;
   }
   
   /**
    * Returns the Location in front of the Snake's Head.
    * 
    * @return the Location in front of the Snake's Head.
    */
   public Location inFront() {
      return head.getLocation().inFront(dir);
   }
   
   /**
    * Called when a key is pressed. 
    * Sets the Direction of the Snake based on the arrow key that is pressed; for
    * example, if the Up arrow key is pressed, the Snake's Direction is set to UP.
    * 
    * @param event the key press event
    */
   public void keyPressed(KeyEvent event) {
      switch (event.getKeyCode()) {
         case KeyEvent.VK_UP:
            setDirection(Direction.UP);
            System.out.println("going up");
            break;
         case KeyEvent.VK_DOWN:
            setDirection(Direction.DOWN);
            System.out.println("going down");
            break;
         case KeyEvent.VK_LEFT:
            setDirection(Direction.LEFT);
            System.out.println("going left");
            break;
         case KeyEvent.VK_RIGHT:
            setDirection(Direction.RIGHT);
            System.out.println("going right");
            break;
         default:
            break;
      }
   }

   /**
    * Called when a key is released.
    * 
    * @param event the key release event
    */
   public void keyReleased(KeyEvent event) {
      
   }

   /**
    * Called when a key is typed (pressed then released).
    * 
    * @param event the key type event
    */
   public void keyTyped(KeyEvent event) {
      
   }
   
   /**
    * Returns a String containing the Locations of the Snake's Head, Body, and Tail.
    * 
    * @return a String representation of the Snake
    */
   public String toString() {
      return head.toString() + 
             "\nBody: " + body.toString() + 
             "\n" + tail.toString() + 
             "\n____________________________";
   }
   
}

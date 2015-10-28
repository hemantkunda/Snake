package snake;

import board.Board;
import board.Location;

/**
 * The Head class is a subclass of the Segment class, and it represents the Snake's head.
 * 
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class Head extends Segment {
   
   /**
    * Creates a new Head object at the specified Location in the specified Board.
    * 
    * @param loc the Head's initial Location
    * @param board the Board to which the Head belongs
    */
   public Head(Location loc, Board board) {
      super(loc, board);
   }
   
   /**
    * Gets a String containing the location of the Head's image.
    * 
    * @return a String containing the address of the Head's image
    */
   public String getImageFileName() {
      return "pieces/head.png";
   }
   
   /**
    * Returns the String "HEAD: " followed by the Head's Location.
    * 
    * @return a String representation of the Head.
    */
   public String toString() {
      return "HEAD: " + super.toString();
   }
}

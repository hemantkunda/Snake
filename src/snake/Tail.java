package snake;

import board.Board;
import board.Location;

/**
 * The Tail class is a subclass of the Segment class, and it represents the Snake's tail.
 * 
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class Tail extends Segment {

   /**
    * Creates a new Tail object at the specified Location in the specified Board.
    * 
    * @param loc the Tail's initial Location
    * @param board the Board to which the Tail belongs
    */
   public Tail(Location loc, Board board) {
      super(loc, board);
   }
   
   /**
    * Gets a String containing the location of the Tail's image.
    * 
    * @return a String containing the address of the Tail's image
    */
   public String getImageFileName() {
      return "pieces/tail.png";
   }
   
   /**
    * Returns the String "TAIL: " followed by the Tail's Location.
    * 
    * @return a String representation of the Tail.
    */
   public String toString() {
      return "TAIL: " + super.toString();
   }
}

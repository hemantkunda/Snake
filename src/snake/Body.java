package snake;

import board.Board;
import board.Location;

/**
 * The Body class is a subclass of the Segment class, and it represents the inner
 * region of the Snake.
 * 
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class Body extends Segment {
   
   /**
    * Creates a new Body object at the specified Location in the specified Board.
    * 
    * @param loc the Body's initial Location
    * @param board the Board to which the Body belongs
    */
   public Body(Location loc, Board board) {
      super(loc, board);
   }
   
   /**
    * Gets a String containing the location of the Body's image.
    * 
    * @return a String containing the address of the Body's image
    */
   public String getImageFileName() {
      return "pieces/body.png";
   }
   
   /**
    * Returns the String "BODY: " followed by the Body's Location.
    * 
    * @return a String representation of the Body.
    */
   public String toString() {
      return "BODY: " + loc.toString();
   }
}

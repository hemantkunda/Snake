package board;

import board.Board;
import board.BoardPiece;
import board.Location;

/**
 * The Food class represents the food that the Snake can eat to increase its size.
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public class Food extends BoardPiece {
   
   /**
    * Creates a new Food object at the specified Location in the specified Board.
    * 
    * @param loc the Food's initial Location
    * @param board the Board to which the Food belongs
    */
   public Food(Location loc, Board board) {
      super(loc, board);
   }
   
   /**
    * Gets a String containing the location of the Food's image.
    * 
    * @return a String containing the address of the Food's image
    */
   public String getImageFileName() {
      return "pieces/food.png";
   }
   
   /**
    * Returns the String "FOOD: " followed by the Food's Location.
    * 
    * @return a String representation of the Food.
    */
   public String toString() {
      return "FOOD: " + super.toString();
   }
}

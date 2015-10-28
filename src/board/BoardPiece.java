package board;

/**
 * A superclass for any object located on the Board.
 * Provides methods for finding its own Location as well as the image that
 * represents it on the GUI.
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public abstract class BoardPiece {
   protected Location loc;
   protected Board board;
   
   /**
    * Initializes the BoardPiece with the provided Board and Location and places itself
    * within its Board at its Location.
    * @param loc the BoardPiece's initial Location
    * @param board the Board to which the BoardPiece belongs
    */
   public BoardPiece(Location loc, Board board) {
      this.loc = loc;
      this.board = board;
      board.put(this, loc);
   }
   
   /**
    * Gets the Location of the BoardPiece
    * 
    * @return the Location of the BoardPiece
    */
   public Location getLocation() {
      return loc;
   }
   
   /**
    * Gets a String containing the location of the BoardPiece's image.
    * 
    * @return a String containing the address of the BoardPiece's image
    */
   public abstract String getImageFileName();
   
   /**
    * Returns the Location of the BoardPiece as a String.
    * 
    * @return the BoardPiece's Location's toString
    */
   public String toString() {
      return loc.toString();
   }
   
}

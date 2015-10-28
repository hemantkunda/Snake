package snake;

import board.Board;
import board.BoardPiece;
import board.Location;

/**
 * The Segment class represents a discrete piece of the Snake; each Segment is displayed
 * on a single JPanel.  There are 3 types of Segments: the Head, Body, and Tail.
 * @author Hemant Kunda
 * @date October 2, 2015
 */
public abstract class Segment extends BoardPiece{
   
   /**
    * Creates a new Segment "object" at the specified Location in the specified Board.
    * 
    * @param loc the Segment's initial Location
    * @param board the Board to which the Segment belongs
    */
   public Segment(Location loc, Board board) {
      super(loc, board);
   }
}

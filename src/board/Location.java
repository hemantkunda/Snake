package board;

/**
 * Wrapper class for a set of coordinates. Also contains utility methods
 * for returning 
 * @author Hemant Kunda
 *
 */
public class Location 
{
   private int x;
   private int y;
   
   /**
    * Creates a new Location object with the specified x and y attributes.
    * 
    * @param r the Location's x-coordinate
    * @param c the Location's y-coordinate
    */
   public Location(int r, int c)
   {
      x = r;
      y = c;
   }
   
   /** Returns the x coordinate of the Location.
    * 
    * @return the Location's x-coordinate
    */
   public int x()
   {
      return x;
   }
   
   /** Returns the y coordinate of the Location.
    * 
    * @return the Location's y-coordinate
    */
   public int y()
   {
      return y;
   }
   
   /**
    * Gets the Location in front of this Location, assuming the Location is 
    * "facing" the specified Direction.
    * 
    * @param d the Direction that the Location is facing
    * 
    * @return a Location that is "in front of" this Location
    */
   public Location inFront(Direction d) {
      switch (d) {
      case UP:
         return new Location(x-1, y);
      case DOWN:
         return new Location(x+1, y);
      case LEFT:
         return new Location(x, y-1);
      case RIGHT:
         return new Location(x, y+1);
      }
      return this;
   }

   /**
    * Compares this Location to another Location based on their x and y coordinates
    * 
    * @param other the other Location to be compared
    * @return true if both Locations share the same x and y coordinates
    *         false otherwise
    */
   public boolean equals(Location other)
   {
      return (other.x() == x() && other.y() == y());
   }

   /**
    * Returns a String containing the Location's x and y coordinates.
    * 
    * @return a String representation of the Location
    */
   public String toString()
   {
      return "Location(" + x() + ", " + y() + ")" ;
   }
}
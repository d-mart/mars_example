package com.mars.mission;
/**
 * 
 */

/**
 * @author dmartinez
 *
 */
public class Field {
	protected Coordinate size;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Field(int x, int y) {
		// ensure the field has no negative and at least one positive dimension
		if((x >= 0) && (y >= 0) && ((y + x) > 1))
			size = new Coordinate(x, y);
		else
			throw new IllegalArgumentException("Field dimensions must be postive and field area must be greater than 0");
	}
	
	/**
	 * 
	 * @param c (x,y) of upper right corner of field
	 */
	public Field(Coordinate c) {
		this(c.x, c.y);
	}
	
	public boolean containsCoordinate(Coordinate c) {
		return(c.inPlane(new Coordinate(0,0), size));
	}
}

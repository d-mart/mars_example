package com.mars.mission;
/**
 * represents an x,y coordinate in a plane
 * 
 * @author dmartinez
 *
 */
public class Coordinate {
	protected int x, y;
	
	public Coordinate(int new_x, int new_y) {
		x = new_x;
		y = new_y;
	}
	
	/**
	 * Move by an ordinal pair
	 */
	public void move(Coordinate move_by) {
		move_x(move_by.x);
		move_y(move_by.y);
	}
	
	/**
	 * Move by individual X and Y offsets
	 */
	public void move(int offset_x, int offset_y) {
		move_x(offset_x);
		move_y(offset_y);
	}
	
	/**
	 * Move in x direction by an offset
	 */
	public void move_x(int offset_x) {
		this.x += offset_x;
	}
	
	/**
	 * Move in y direction by an offset
	 */
	public void move_y(int offset_y) {
		this.y += offset_y;
	}
	
	/**
	 * true if this coordinate is on a plane
	 * represented by the coordinate arguments.
	 */
	public boolean inPlane(Coordinate a, Coordinate b) {
		// Check if x lies outside the max min x values of a and b
		if((this.x > Math.max(a.x, b.x)) || (this.x < Math.min(a.x,  b.x)))
			return(false);
		// check if y lies outside the max,min y values of a and b
		if((this.y > Math.max(a.y, b.y)) || (this.y < Math.min(a.y,  b.y)))
			return(false);
		// all checks cleared. x and y lie within the rectanle [a,b]
		return(true);
	}
}

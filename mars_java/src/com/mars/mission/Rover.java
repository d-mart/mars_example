package com.mars.mission;
/**
 * 
 */

/**
 * @author dmartinez
 *
 */
public class Rover {
	private Field plateau;  /**< The area of Mars on which this rover is allowed to explore */
	protected Coordinate position;
	protected Heading heading;
	
	
	/**
	 * Takes a string with landing instructions in the format "X Y (N|S|E|W)"
	 * @param landingInstruction - e.g. "5 4 E"
	 * @param targetField - A Field on which to land
	 * @throws IllegalArgumentException
	 */
	public Rover(String landingInstruction, Field targetField) throws IllegalArgumentException {
		// Check and save the exploration area
		if(targetField == null) throw new IllegalArgumentException("Landing area is invalid");
		// save the landing field into this object
		this.plateau = targetField;
		// Break landing command into individual strings
		String[] landingDetails = landingInstruction.split(" ");
		// create landing position out of first to words.  E.g. "5" "4" --> (5,4)
		this.position = new Coordinate(Integer.parseInt(landingDetails[0]), Integer.parseInt(landingDetails[1]));
		// Make sure the landing position is within the field specified
		if(!plateau.containsCoordinate(this.position)) throw new IllegalArgumentException("Landing location is outside of exploration area");
		// Set the heading - upcase the first char of the word and check it
		heading = Heading.get(landingDetails[2].substring(0, 1).toUpperCase());
		if(heading == null)
			throw new IllegalArgumentException("Invalid heading");
	}
	
	/**
	 * Process a list of commands.  The list is a single string containing
	 * one or more commands.  Valid commands are
	 *     'L' - turn left
	 *     'R' - turn right
	 *     'M' - move forward one square.
	 * @param commandList - one or more commands for rover, e.g. "LMMMLMRRRMM"
	 */
	public void command(String commandList) {
		commandList = commandList.toUpperCase();
		for(int i = 0; i < commandList.length(); i++) {
			switch(commandList.charAt(i)) {
			case 'L':
				turn(-1);
				break;
			case 'R':
				turn(1);
				break;
			case 'M':
				move();
				break;
			default:
				// Silently ignore invalid commands 
				// - OR -
				// throw new IllegalArgumentException("Invalid Command");
			}
		}
	}
	
	/**
	 * Move one unit in the direction of current heading
	 */
	public void move() {
		Coordinate tentativePosition = new Coordinate(position.x, position.y);
		// Calculate where we would move given our present heading
		switch(heading) {
		case NORTH:
			tentativePosition.move_y(1);
			break;
		case SOUTH:
			tentativePosition.move_y(-1);
			break;
		case EAST:
			tentativePosition.move_x(1);
			break;
		case WEST:
			tentativePosition.move_x(-1);
			break;
		}
		// if the calculated end position is valid, go ahead and move
		if(plateau.containsCoordinate(tentativePosition)) {
			position = tentativePosition;
		}
		else {
			// Attempt was made to drive off the exploration area
			// Report exception or make log entry if desired
		}
	}
	
	@Override public String toString() {
		return(position.x + " " + position.y + " " + heading.toString());
	}
	
	/**
	 * Adjust the heading of the rover
	 * @param direction: -1 => Left turn (-90 degrees)
	 *                    1 => right turn (90 degrees)
	 *                    other values ignored 
	 */
	public void turn(int direction) {
		/** Counter-Clockwise, Left, -90 degree turn */
		if(direction == -1) {
			switch(this.heading) {
			case NORTH:
				heading = Heading.WEST;
				break;
			case WEST:
				heading = Heading.SOUTH;
				break;
			case SOUTH:
				heading = Heading.EAST;
				break;
			case EAST:
				heading = Heading.NORTH;
				break;
			default:
				// Nothing 
				break;
			}
		}
		/** Clockwise, Right, +90 degree turn */
		if(direction == 1) {
			switch(this.heading) {
			case NORTH:
				heading = Heading.EAST;
				break;
			case WEST:
				heading = Heading.NORTH;
				break;
			case SOUTH:
				heading = Heading.WEST;
				break;
			case EAST:
				heading = Heading.SOUTH;
				break;
			default:
				// Nothing 
				break;
			}
		}
	}
}


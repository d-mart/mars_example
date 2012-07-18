package com.mars.mission;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * Represents a direction of travel or orientation
 * 
 * @author dmartinez
 *
 */
public enum Heading {
	NORTH("N"), 
	EAST("E"), 
	SOUTH("S"), 
	WEST("W");
	
	// Storage for a character representation of the heading
	private String letter = null;
	
	// Map to get values from ordinal letter, i.e. "N", "S", "E", "W"
	private static Map<String, Heading> lookup = new HashMap<String, Heading>();

	private Heading(String s) {
		letter = s.substring(0, 1);
	}
	
	// create Map of letters to values
	static {
		for(Heading h : Heading.values()) {
			lookup.put(h.letter, h);
		}
	}
	
	// Get just first letter of direction
	@Override public String toString() {
		return super.toString().substring(0,1);
	}
	
	/**
	 * Get enum from direction letter (String)
	 * @param label - "N", "S", "E", or "W"
	 * @return NORTH, SOUTH, EAST, WEST, or null
	 */
	public static Heading get(String label) {
		return lookup.get(label);
	}
}

package com.mars.mission;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mars.mission.Field;
import com.mars.mission.Heading;
import com.mars.mission.Rover;

/**
 * 
 */

/**
 * @author dmartinez
 *
 */
public class RoverTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		Rover r;
		Field f = new Field(10, 10);
		/* Null field ==> Exception */
		try {
			r = new Rover("1 2 E", null);
			fail("Should've gotten exception for passing a null field");
		}
		catch(IllegalArgumentException expected) { /* pass - exception expected case */ }
		/* Out of bounds landing area */
		try {
			r = new Rover("11 11 E", f);
			fail("Should've gotten exception for trying to land outside of plateau");
		}
		catch(IllegalArgumentException expected) { /* pass - exception expected case */ }
		/* Invalid heading */
		try {
			r = new Rover("1 2 Z", f);
			fail("Should've gotten exception for invalid heading");
		}
		catch(IllegalArgumentException expected) { /* pass - exception expected case */ }
		
		/* Valid constructor, verify correct location and heading */
		r = new Rover("2 4 E", f);
		assertEquals(r.position.x, 2);
		assertEquals(r.position.y, 4);
		assertEquals(r.heading, Heading.EAST);
	}
	
	@Test
	public void testCommand() {
		// Test with given test input
		Field f = new Field(5, 5);
		
		Rover r1 = new Rover("1 2 N", f);
		r1.command("LMLMLMLMM");
		assertEquals("1 3 N", r1.toString());
		
		Rover r2 = new Rover("3 3 E", f);
		r2.command("MMRMMRMRRM");
		assertEquals("5 1 E", r2.toString());
	}
	
	@Test
	public void testMove() {
		
		/**
		 * Test to bottom of Y-axis
		 */
		Field f = new Field(10, 10);
		Rover r = new Rover("1 2 S", f);
		
		// Verify initial conditions
		assertEquals(1, r.position.x);
		assertEquals(2, r.position.y);
		assertEquals(Heading.SOUTH, r.heading);
		
		// Move and recheck
		r.move();
		assertEquals(1, r.position.x);
		assertEquals(1, r.position.y);
		assertEquals(Heading.SOUTH, r.heading);
		
		// Move again and check
		r.move();
		assertEquals(1, r.position.x);
		assertEquals(0, r.position.y);
		assertEquals(Heading.SOUTH, r.heading);
		
		// Now at boundary. Further movements will result in no change
		r.move();
		assertEquals(1, r.position.x);
		assertEquals(0, r.position.y);
		assertEquals(Heading.SOUTH, r.heading);
		
		
		/**
		 * Test to end of X-axis
		 */
		f = new Field(35, 5);
		r = new Rover("33 5 E", f);
		
		// Verify initial conditions
		assertEquals(33, r.position.x);
		assertEquals(5, r.position.y);
		assertEquals(Heading.EAST, r.heading);
		
		// Move and recheck
		r.move();
		assertEquals(34, r.position.x);
		assertEquals(5, r.position.y);
		assertEquals(Heading.EAST, r.heading);
		
		// Move again and check
		r.move();
		assertEquals(35, r.position.x);
		assertEquals(5, r.position.y);
		assertEquals(Heading.EAST, r.heading);
		
		// Now at boundary. Further movements will result in no change
		r.move();
		assertEquals(35, r.position.x);
		assertEquals(5, r.position.y);
		assertEquals(Heading.EAST, r.heading);
	}
	
	@Test
	public void testTurn() {
		Field f = new Field(10, 10);
		Rover r = new Rover("5 5 N", f);
		
		// Verify object is constructed as we expect
		assertEquals(Heading.NORTH, r.heading);
		
		// Verify that invalid input (i.e. != +/- 1) does not change heading
		r.turn(5);
		assertEquals(Heading.NORTH, r.heading);
		r.turn(-2);
		assertEquals(Heading.NORTH, r.heading);
		r.turn(0);
		assertEquals(Heading.NORTH, r.heading);
		
		// verify left CCW (-1) (Left) turns
		r.turn(-1);
		assertEquals(Heading.WEST,  r.heading);
		r.turn(-1);
		assertEquals(Heading.SOUTH, r.heading);
		r.turn(-1);
		assertEquals(Heading.EAST,  r.heading);
		r.turn(-1);
		assertEquals(Heading.NORTH, r.heading);
		r.turn(-1);
		assertEquals(Heading.WEST,  r.heading);
		
		// verify CW (1) (Right) turns
		r.turn(1);
		assertEquals(Heading.NORTH, r.heading);
		r.turn(1);
		assertEquals(Heading.EAST,  r.heading);
		r.turn(1);
		assertEquals(Heading.SOUTH, r.heading);
		r.turn(1);
		assertEquals(Heading.WEST,  r.heading);
		r.turn(1);
		assertEquals(Heading.NORTH, r.heading);
		
	}

}

package com.mars.mission;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mars.mission.Coordinate;
import com.mars.mission.Field;

/**
 * 
 */

/**
 * @author dmartinez
 *
 */
public class FieldTest {

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
		Field f;
		/* negative x coord */
		try {
			f = new Field(-1, 1);
			fail("Shouldn't get here - Exception was not thrown for invalid argument");
		}
		catch(IllegalArgumentException expected) { /* pass - exception expected case */ }
		/* negative y coord */
		try {
			f = new Field(2, -1);
			fail("Shouldn't get here - Exception was not thrown for invalid argument");
		}
		catch(IllegalArgumentException expected) { /* pass - exception expected case */ }
		/* x,y = 0 */
		try {
			f = new Field(0, 0);
			fail("Shouldn't get here - Exception was not thrown for invalid argument");
		}
		catch(IllegalArgumentException expected) { /* pass - exception expected case */ }
	    
		/* Verify constructor with x, y coord args */
		f = new Field(5, 6);
		assertEquals(f.size.x, 5);
		assertEquals(f.size.y, 6);
		
		/* Verify constructor with Coordinate arg */
		f = new Field(new Coordinate(10,11));
		assertEquals(f.size.x, 10);
		assertEquals(f.size.y, 11);	
	}

	@Test
	/**
	 * Verify that a Field correctly reports whether a given coordinate is within itself or not
	 */
	public void testContainsCoordinate() {
		Field f = new Field(3,4);
		
		assertFalse(f.containsCoordinate(new Coordinate( 3, -1)));
		assertFalse(f.containsCoordinate(new Coordinate(-1,  4)));
		assertFalse(f.containsCoordinate(new Coordinate(-1, -1)));
		assertFalse(f.containsCoordinate(new Coordinate( 4,  4)));
		assertFalse(f.containsCoordinate(new Coordinate( 3,  5)));
		assertFalse(f.containsCoordinate(new Coordinate( 4,  5)));
		
		assertTrue(f.containsCoordinate(new Coordinate(0, 0)));
		assertTrue(f.containsCoordinate(new Coordinate(3, 0)));
		assertTrue(f.containsCoordinate(new Coordinate(0, 4)));
		assertTrue(f.containsCoordinate(new Coordinate(3, 4)));
		assertTrue(f.containsCoordinate(new Coordinate(1, 1)));
	}
}

package com.mars.mission;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mars.mission.Coordinate;

/**
 * 
 */

/**
 * @author dmartinez
 *
 */
public class CoordinateTest {

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
		Coordinate c = new Coordinate(4, 5);
		assertEquals(4, c.x);
		assertEquals(5, c.y);
		
		c = new Coordinate( 0, -1);
		assertEquals( 0, c.x);
		assertEquals(-1, c.y);
	}
	
	@Test
	public void testMoveByCoordinate() {
		Coordinate c = new Coordinate(5, 5);
		
		c.move(new Coordinate(1,1));
		assertEquals(6, c.x);
		assertEquals(6, c.y);
		
		c.move(new Coordinate(-2,-3));
		assertEquals(4, c.x);
		assertEquals(3, c.y);
		
		c.move(new Coordinate(-2,10));
		assertEquals( 2, c.x);
		assertEquals(13, c.y);
	}
	
	@Test
	public void testMoveByXy() {
		Coordinate c = new Coordinate(5,5);
		
		c.move(-1, 1);
		assertEquals(4, c.x);
		assertEquals(6, c.y);
		
		c.move(0, 0);
		assertEquals(4, c.x);
		assertEquals(6, c.y);
		
		c.move(3, -15);
		assertEquals( 7, c.x);
		assertEquals(-9, c.y);
	}
	
	@Test
	public void testInPlane() {
		assertTrue(new Coordinate(5,5).inPlane(new Coordinate(0, 0), new Coordinate(10, 10)));
		
		Coordinate a = new Coordinate(-5, 0);
		Coordinate b = new Coordinate( 1, 4);
		
		assertFalse(new Coordinate(-6,  3).inPlane(a, b));
		assertFalse(new Coordinate( 4, -1).inPlane(a, b));
		assertFalse(new Coordinate( 4,  5).inPlane(a, b));
		assertFalse(new Coordinate( 2,  2).inPlane(a, b));
		
		assertTrue(new Coordinate(-5,  4).inPlane(a, b));
		assertTrue(new Coordinate( 1,  0).inPlane(a, b));
		assertTrue(new Coordinate( 0,  0).inPlane(a, b));
		assertTrue(new Coordinate( 1,  4).inPlane(a, b));
	}
}

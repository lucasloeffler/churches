package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

	private static final double DELTA = 0.00001;

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfHigherBounds() {
		Coordinate coordinate = new Coordinate(91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfLowerBounds() {
		Coordinate coordinate = new Coordinate(-91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfHigherBounds() {
		Coordinate coordinate = new Coordinate(45, 181);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfLowerBounds() {
		Coordinate coordinate = new Coordinate(45, -181);
	}

	@Test
	public void testDefaultConstructor() {
		Coordinate coordinate = new Coordinate();
		assertEquals(0.0, coordinate.getLatitude(), DELTA);
		assertEquals(0.0, coordinate.getLongitude(), DELTA);
	}

	@Test
	public void testConstructor() {
		Coordinate coordinate = new Coordinate(-12, 92);
		assertEquals(-12, coordinate.getLatitude(), DELTA);
		assertEquals(92, coordinate.getLongitude(), DELTA);
	}

	@Test
	public void testLatitudinalDistance() {
		Coordinate coordinate1 = new Coordinate(30, 20);
		Coordinate coordinate2 = new Coordinate(90, 90);
		assertEquals(60, coordinate1.getLatitudinalDistance(coordinate2), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudinalDistanceParameterNull() {
		Coordinate coordinate1 = new Coordinate(30, 20);
		Coordinate coordinate2 = null;
		double d = coordinate1.getLatitudinalDistance(coordinate2);
	}

	@Test
	public void testLongitudinalDistance() {
		Coordinate coordinate1 = new Coordinate(30, 20);
		Coordinate coordinate2 = new Coordinate(90, 90);
		assertEquals(70, coordinate1.getLongitudinalDistance(coordinate2),
				DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudinalDistanceParameterNull() {
		Coordinate coordinate1 = new Coordinate(30, 20);
		Coordinate coordinate2 = null;
		double d = coordinate1.getLongitudinalDistance(coordinate2);
	}

	@Test
	public void testDistance() {
		Coordinate coordinate1 = new Coordinate(30, 20);
		Coordinate coordinate2 = new Coordinate(90, 90);
		Coordinate distance = coordinate1.getDistance(coordinate2);
		// We make assertions, referring to the definition of getDistance()
		// method
		assertEquals(coordinate1.getLatitudinalDistance(coordinate2),
				distance.getLatitude(), DELTA);
		assertEquals(coordinate1.getLongitudinalDistance(coordinate2),
				distance.getLongitude(), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceParamterNull() {
		Coordinate coordinate1 = new Coordinate(30, 20);
		Coordinate coordinate2 = null;
		Coordinate distance = coordinate1.getDistance(coordinate2);
	}

}
package org.wahlzeit.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class SphericCoordinateTest {

	private static final double DELTA = 0.5;

	private static final SphericCoordinate erlangen = new SphericCoordinate(
			49.58, 11.01);
	private static final SphericCoordinate houston = new SphericCoordinate(
			29.76, -95.36);
	private static final SphericCoordinate yakutsk = new SphericCoordinate(
			62.03, 129.6);
	private static final SphericCoordinate sydney = new SphericCoordinate(
			-33.86, 151.2);

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfHigherBounds() {
		new SphericCoordinate(91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfLowerBounds() {
		new SphericCoordinate(-91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfHigherBounds() {
		new SphericCoordinate(45, 181);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfLowerBounds() {
		new SphericCoordinate(45, -181);
	}

	@Test
	public void testDefaultConstructor() {
		SphericCoordinate sphericCoordinate = new SphericCoordinate();
		assertEquals(0.0, sphericCoordinate.getLatitude(), DELTA);
		assertEquals(0.0, sphericCoordinate.getLongitude(), DELTA);
	}

	@Test
	public void testConstructor() {
		SphericCoordinate sphericCoordinate = new SphericCoordinate(-12, 92);
		assertEquals(-12, sphericCoordinate.getLatitude(), DELTA);
		assertEquals(92, sphericCoordinate.getLongitude(), DELTA);
	}

	@Test
	public void testLatitudinalDistance() {
		assertEquals(19.8, erlangen.getLatitudinalDistance(houston), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudinalDistanceParameterNull() {
		erlangen.getLatitudinalDistance(null);
	}

	@Test
	public void testLongitudinalDistanceSimple() {
		double longitudinalDistance = sydney.getLongitudinalDistance(yakutsk);
		assertEquals(21.6, longitudinalDistance, DELTA);
	}

	@Test
	public void testLongitudinalDistanceComplex() {
		double longitudinalDistance = houston.getLongitudinalDistance(sydney);
		assertEquals(113.4, longitudinalDistance, DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudinalDistanceParameterNull() {
		sydney.getLongitudinalDistance(null);
	}

	// Checked the expected values with
	// http://www.movable-type.co.uk/scripts/latlong.html
	@Test
	public void testDistanceErlangenHouston() {
		double distance = erlangen.getDistance(houston);
		assertEquals(distance, 8599, DELTA);
	}

	@Test
	public void testDistanceHoustonYakutsk() {
		double distance = houston.getDistance(yakutsk);
		assertEquals(distance, 9046, DELTA);
	}

	@Test
	public void testDistanceYakutskSydney() {
		double distance = yakutsk.getDistance(sydney);
		assertEquals(distance, 10838, DELTA);
	}

	@Test
	public void testDistanceSydneyErlangen() {
		double distance = sydney.getDistance(erlangen);
		assertEquals(distance, 16335, DELTA);
	}

	@Test
	public void testDistanceErlangenErlangen() {
		double distance = erlangen.getDistance(erlangen);
		assertEquals(distance, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceParamterNull() {
		erlangen.getDistance(null);
	}

}
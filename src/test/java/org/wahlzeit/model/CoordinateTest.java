package org.wahlzeit.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoordinateTest {

	private static final double DELTA = 0.5;

	private static final SphericCoordinate erlangenSpheric = SphericCoordinate
			.getSphericCoordinate(49.58, 11.01);
	private static final SphericCoordinate erlangenSphericCopy = SphericCoordinate
			.getSphericCoordinate(49.58, 11.01);

	private static final SphericCoordinate houston = SphericCoordinate
			.getSphericCoordinate(29.76, -95.36);
	private static final SphericCoordinate yakutsk = SphericCoordinate
			.getSphericCoordinate(62.03, 129.6);
	private static final SphericCoordinate sydney = SphericCoordinate
			.getSphericCoordinate(-33.86, 151.2);

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfHigherBounds() {
		SphericCoordinate.getSphericCoordinate(91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudeOutOfLowerBounds() {
		SphericCoordinate.getSphericCoordinate(-91, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfHigherBounds() {
		SphericCoordinate.getSphericCoordinate(45, 181);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLongitudeOutOfLowerBounds() {
		SphericCoordinate.getSphericCoordinate(45, -181);
	}

	@Test
	public void testConstructor() {
		SphericCoordinate sphericCoordinate = SphericCoordinate
				.getSphericCoordinate(-12, 92);
		assertEquals(-12, sphericCoordinate.getLatitude(), DELTA);
		assertEquals(92, sphericCoordinate.getLongitude(), DELTA);
	}

	@Test
	public void testEquals() {
		assertTrue(erlangenSpheric.isEqual(erlangenSpheric));
		assertTrue(erlangenSpheric.isEqual(erlangenSphericCopy));
		assertTrue(!erlangenSpheric.isEqual(houston));
	}

	@Test
	public void testLatitudinalDistance() {
		assertEquals(19.8, erlangenSpheric.getLatitudinalDistance(houston),
				DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLatitudinalDistanceParameterNull() {
		erlangenSpheric.getLatitudinalDistance(null);
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
		double distance = erlangenSpheric.getDistance(houston);
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
		double distance = sydney.getDistance(erlangenSpheric);
		assertEquals(distance, 16335, DELTA);
	}

	@Test
	public void testDistanceErlangenErlangen() {
		double distance = erlangenSpheric.getDistance(erlangenSpheric);
		assertEquals(distance, 0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceParamterNull() {
		erlangenSpheric.getDistance(null);
	}

}
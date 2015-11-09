package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CartesianCoordinateTest {

	private static final CartesianCoordinate locationDefault = new CartesianCoordinate();
	private static final CartesianCoordinate locationSimple = new CartesianCoordinate(
			1, 2, 3);

	private static final SphericCoordinate erlangenSpheric = new SphericCoordinate(
			49.58, 11.01);
	private static final SphericCoordinate houstonSpheric = new SphericCoordinate(
			29.76, -95.36);

	private static final CartesianCoordinate erlangenCartesian = erlangenSpheric
			.toCartesianCoordinate();
	private static final CartesianCoordinate houstonCartesian = houstonSpheric
			.toCartesianCoordinate();

	private static final double DELTA = 0.01;

	@Test
	public void testDefaultConstructor() {
		assertEquals(0, locationDefault.getX(), DELTA);
		assertEquals(0, locationDefault.getY(), DELTA);
		assertEquals(0, locationDefault.getZ(), DELTA);
	}

	@Test
	public void testConstructor() {
		assertEquals(1, locationSimple.getX(), DELTA);
		assertEquals(2, locationSimple.getY(), DELTA);
		assertEquals(3, locationSimple.getZ(), DELTA);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceNull() {
		locationDefault.getDistance(null);
	}

	@Test
	public void testGetDistance() {
		assertEquals(0, erlangenCartesian.getDistance(erlangenCartesian), 0);

		assertEquals(houstonSpheric.getDistance(erlangenSpheric),
				houstonCartesian.getDistance(erlangenCartesian), DELTA);

		assertEquals(erlangenSpheric.getDistance(houstonSpheric),
				erlangenCartesian.getDistance(houstonCartesian), DELTA);
	}

	@Test
	public void testToSpheric() {
		assertEquals(erlangenSpheric, erlangenCartesian.toSphericCoordinate());
	}

	@Test
	public void testToCartesian() {
		assertEquals(erlangenCartesian, erlangenSpheric.toCartesianCoordinate());
	}

}

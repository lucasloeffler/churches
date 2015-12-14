package org.wahlzeit.model;

import java.util.ArrayList;

/**
 * @author Lucas Löffler
 *
 */
public abstract class AbstractCoordinate implements Coordinate {

	private static final long serialVersionUID = -3526198300387449394L;
	
	static protected ArrayList<Coordinate> instances = new ArrayList<Coordinate>();

	/**
	 * Computes the euclidean distance between to two points in the cartesian
	 * coordinate sytem.
	 *
	 * @param coordinate
	 * @return the distance as double value
	 *
	 * @methodtype query
	 */
	@Override
	public double getDistance(Coordinate coordinate) {
		// Preconditions
		assertClassInvariants();
		assertParamNotNull(coordinate);
		assertSameRadius(coordinate.getRadius());

		// We need angle in radians for the formula, so we first have to convert
		// the latitudinal values and the longitudinal distance to radians
		double radiansLatitudeThis = Math.toRadians(this.getLatitude());
		double radiansLatitudeOther = Math.toRadians(coordinate.getLatitude());
		double radiansDeltaLong = Math.toRadians(Math.abs(this.getLongitude()
				- coordinate.getLongitude()));

		// Compute sines for both radians latitudes
		double sineLatitudeThis = Math.sin(radiansLatitudeThis);
		double sineLatitudeOther = Math.sin(radiansLatitudeOther);

		// Compute cosines for both radians latitudes
		double cosineLatitudeThis = Math.cos(radiansLatitudeThis);
		double cosineLatitudeOther = Math.cos(radiansLatitudeOther);

		// Compute cosine for longitudinal distance
		double cosineLongitudinalDistance = Math.cos(radiansDeltaLong);

		// Compute the angle in radians according to the formula
		double angleRadians = Math.acos(sineLatitudeThis * sineLatitudeOther
				+ cosineLatitudeThis * cosineLatitudeOther
				* cosineLongitudinalDistance);

		// Finally compute the distance
		double distance = this.getRadius() * angleRadians;

		// Postcondition
		assert distance >= 0;
		return distance;
	}

	protected void assertClassInvariants() {
		// Check if we have a valid coordinate
		assertValidLatitude(this.getLatitude());
		assertValidLongitude(this.getLongitude());
		assertValidRadius(this.getRadius());
	}

	protected void assertParamNotNull(Coordinate coordinate) {
		if (coordinate == null)
			throw new IllegalArgumentException(
					"Passed coordinate must not be null.");
	}

	protected void assertSameRadius(double radius) {
		if (this.getRadius() != radius)
			throw new IllegalArgumentException(
					"Cannot calculate distance of coordinates with different radius.");
	}

	protected static void assertValidCoordinate(Coordinate coordinate) {
		assert (coordinate != null);
		assertValidLatitude(coordinate.getLatitude());
		assertValidLongitude(coordinate.getLongitude());
		assertValidRadius(coordinate.getRadius());
	}

	protected static void assertValidLatitude(double latitude) {
		if (Double.isNaN(latitude) || latitude < -90 || latitude > 90)
			throw new IllegalArgumentException(
					"Invalid latitude. Value must be between 90 and -90.");
	}

	protected static void assertValidLongitude(double longitude) {
		if (Double.isNaN(longitude) || longitude < -180 || longitude > 180)
			throw new IllegalArgumentException(
					"Invalid longitude. Value must be between 180 and -180.");
	}

	protected static void assertValidRadius(double radius) {
		if (radius < 0)
			throw new IllegalArgumentException(
					"Invalid radius. Radius must not be negative.");
	}

	/**
	 * 
	 * @methodtype query
	 */
	@Override
	public boolean isEqual(Coordinate coordinate) {
		if (coordinate == null)
			return false;
		if (this == coordinate) {
			return true;
		}
		if (getClass() != coordinate.getClass())
			return false;

		if (Double.doubleToLongBits(this.getLatitude()) != Double
				.doubleToLongBits(coordinate.getLatitude()))
			return false;
		if (Double.doubleToLongBits(this.getLongitude()) != Double
				.doubleToLongBits(coordinate.getLongitude()))
			return false;
		return true;
	}
}

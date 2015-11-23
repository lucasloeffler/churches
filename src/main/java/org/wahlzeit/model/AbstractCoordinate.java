package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

/**
 * @author Lucas Löffler
 *
 */
public abstract class AbstractCoordinate extends DataObject implements
		Coordinate {

	private static final long serialVersionUID = -3526198300387449394L;

	/**
	 * Computes the euclidean distance between to two points in the cartesian
	 * coordinate sytem.
	 *
	 * @param coordinate
	 * @return the distance as double value
	 *
	 * @methodtype get
	 */
	@Override
	public double getDistance(Coordinate coordinate) {
		assertParamNotNull(coordinate);
		assertSameRadius(coordinate.toSphericCoordinate().getRadius());

		// We need angle in radians for the formula, so we first have to convert
		// the latitudinal values and the longitudinal distance to radians
		double radiansLatitudeThis = Math.toRadians(this.toSphericCoordinate()
				.getLatitude());
		double radiansLatitudeOther = Math.toRadians(coordinate
				.toSphericCoordinate().getLatitude());
		double radiansLongitudinalDistance = Math.toRadians(this
				.toSphericCoordinate().getLongitudinalDistance(
						coordinate.toSphericCoordinate()));

		// Compute sines for both radians latitudes
		double sineLatitudeThis = Math.sin(radiansLatitudeThis);
		double sineLatitudeOther = Math.sin(radiansLatitudeOther);

		// Compute cosines for both radians latitudes
		double cosineLatitudeThis = Math.cos(radiansLatitudeThis);
		double cosineLatitudeOther = Math.cos(radiansLatitudeOther);

		// Compute cosine for longitudinal distance
		double cosineLongitudinalDistance = Math
				.cos(radiansLongitudinalDistance);

		// Compute the angle in radians according to the formula
		double angleRadians = Math.acos(sineLatitudeThis * sineLatitudeOther
				+ cosineLatitudeThis * cosineLatitudeOther
				* cosineLongitudinalDistance);

		// Finally compute the distance
		double distance = this.toSphericCoordinate().getRadius() * angleRadians;
		return distance;
	}

	protected void assertParamNotNull(Coordinate coordinate) {
		if (coordinate == null)
			throw new IllegalArgumentException(
					"Passed coordinate must not be null.");
	}

	protected void assertSameRadius(double radius) {
		if (this.toSphericCoordinate().getRadius() != radius)
			throw new IllegalArgumentException(
					"Cannot calculate distance of coordinates with different radius.");
	}

	protected void assertValidLatitude(double latitude) {
		if (Double.isNaN(latitude) || latitude < -90 || latitude > 90)
			throw new IllegalArgumentException(
					"Invalid latitude. Value must be between 90 and -90.");
	}

	protected void assertValidLongitude(double longitude) {
		if (Double.isNaN(longitude) || longitude < -180 || longitude > 180)
			throw new IllegalArgumentException(
					"Invalid longitude. Value must be between 180 and -180.");
	}

	protected void assertValidRadius(double radius) {
		if (radius < 0)
			throw new IllegalArgumentException("Invalid radius. Radius must not be negative.");
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

		SphericCoordinate sphericCoordinateThis = this.toSphericCoordinate();
		SphericCoordinate sphericCoordinateOhter = coordinate
				.toSphericCoordinate();

		if (Double.doubleToLongBits(sphericCoordinateThis.getLatitude()) != Double
				.doubleToLongBits(sphericCoordinateOhter.getLatitude()))
			return false;
		if (Double.doubleToLongBits(sphericCoordinateThis.getLongitude()) != Double
				.doubleToLongBits(sphericCoordinateThis.getLongitude()))
			return false;
		return true;
	}

	public abstract SphericCoordinate toSphericCoordinate();
}

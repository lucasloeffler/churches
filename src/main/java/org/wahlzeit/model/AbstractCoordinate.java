package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.DataObject;

/**
 * @author Lucas Löffler
 *
 */
public abstract class AbstractCoordinate extends DataObject implements
		Coordinate {

	private static final Logger log = Logger.getLogger(AbstractCoordinate.class
			.getName());

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
		if (coordinate == null)
			throw new IllegalArgumentException(
					"Cannot calculate distance between coordinates. Passed coordinate is null.");

		SphericCoordinate sphericCoordinateThis = this.toSphericCoordinate();
		SphericCoordinate sphericCoordinateOhter = coordinate
				.toSphericCoordinate();

		if (sphericCoordinateThis.getRadius() != sphericCoordinateOhter
				.getRadius())
			throw new IllegalArgumentException(
					"Cannot calculate distance of coordinates with different radius.");

		// We need angle in radians for the formula, so we first have to convert
		// the latitudinal values and the longitudinal distance to radians
		double radiansLatitudeThis = Math.toRadians(sphericCoordinateThis
				.getLatitude());
		double radiansLatitudeOther = Math.toRadians(sphericCoordinateOhter
				.getLatitude());
		double radiansLongitudinalDistance = Math
				.toRadians(sphericCoordinateThis
						.getLongitudinalDistance(sphericCoordinateOhter));

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
		double distance = sphericCoordinateThis.getRadius() * angleRadians;
		// log.info("Distance is: " + distance);
		return distance;

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

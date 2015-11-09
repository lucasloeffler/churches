package org.wahlzeit.model;

import java.util.logging.Logger;

/**
 * A spheric coordinate describes a point in a spherical coordinate system.
 * Every point contains of a latitudinal value, a longitudinal value and a
 * radial distance (radius).
 * 
 * @author Lucas Löffler
 *
 */
public class SphericCoordinate implements Coordinate {

	private static final Logger log = Logger.getLogger(SphericCoordinate.class
			.getName());

	private static final long serialVersionUID = 9148993767234441255L;

	private final int EARTHRADIUS = 6371;

	private double latitude;

	private double longitude;

	private double radius;

	/**
	 * Construct an empty SphericCoordinate object with both latitude and
	 * longitude = 0. We assume the earth to be the sphere in this case, so we
	 * set earth radius as radius.
	 * 
	 * @methodtype constructor
	 */
	public SphericCoordinate() {
		setLatitude(0);
		setLongitude(0);
		setRadius(EARTHRADIUS);
	}

	/**
	 * Construct a SphericCoordinate object with latitude and longitude but no
	 * given radius. We assume the earth to be the sphere in this case, so we
	 * set earth radius as radius.
	 * 
	 * @param latitude
	 *            the latitude value
	 * @param longitude
	 *            the longitude value
	 * 
	 * @methodtype constructor
	 *
	 */
	public SphericCoordinate(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
		setRadius(EARTHRADIUS);
	}

	/**
	 * Construct a SphericCoordinate object with latitude, longitude and radius.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * 
	 * @methodtype constructor
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
		setLatitude(latitude);
		setLongitude(longitude);
		setRadius(radius);
	}

	/**
	 * Computes the orthodromic distance between two coordinates.
	 * 
	 * @param coordinate
	 * @return the distance as double value
	 * @methodtype get
	 */
	public double getDistance(Coordinate coordinate) {

		if (coordinate == null) {
			throw new IllegalArgumentException(
					"Cannot calculate distance. Passed coordinate is null.");
		}
		SphericCoordinate sphericCoordinate = null;
		if (coordinate instanceof SphericCoordinate)
			sphericCoordinate = (SphericCoordinate) coordinate;
		else
			sphericCoordinate = coordinate.toSphericCoordinate();

		// We need angle in radians for the formula, so we first have to convert
		// the latitudinal values and the longitudinal distance to radians
		double radiansLatitudeThis = Math.toRadians(this.latitude);
		double radiansLatitudeOther = Math.toRadians(sphericCoordinate
				.getLatitude());
		double radiansLongitudinalDistance = Math.toRadians(this
				.getLongitudinalDistance(coordinate));

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
		double distance = radius * angleRadians;
		// log.info("Distance is: " + distance);
		return distance;
	}

	/**
	 * Calculates the latitudinal distance between this Coordinate and the
	 * passed coordinate by subtracting the latitude of the passed coordinate
	 * from the latitude of this coordinate and returning the absolute value of
	 * the result.
	 * 
	 * @param coordinate
	 *            The coordinate to calculate the distance to
	 * @return The latitudinal distance as double value
	 * 
	 * @methodtype get
	 */
	public double getLatitudinalDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException(
					"Cannot calculate latitudinal distance. Passed coordinate is null.");
		}
		SphericCoordinate sphericCoordinate = null;
		if (coordinate instanceof SphericCoordinate)
			sphericCoordinate = (SphericCoordinate) coordinate;
		else
			sphericCoordinate = coordinate.toSphericCoordinate();
		return Math.abs(latitude - sphericCoordinate.getLatitude());
	}

	/**
	 * Calculates the longitudinal distance between this coordinate and the
	 * passed coordinate by subtracting the longitude of the passed coordinate
	 * from the longitude of this coordinate and returning the absolute value of
	 * the result.
	 * 
	 * @param coordinate
	 *            The coordinate to calculate the distance to
	 * @return The longitudinal distance
	 * 
	 * @methodtype get
	 */
	public double getLongitudinalDistance(Coordinate coordinate) {
		if (coordinate == null)
			throw new IllegalArgumentException(
					"Cannot calculate longitudinal distance. Passed coordinate is null.");
		SphericCoordinate sphericCoordinate = null;
		if (coordinate instanceof SphericCoordinate)
			sphericCoordinate = (SphericCoordinate) coordinate;
		else
			sphericCoordinate = coordinate.toSphericCoordinate();

		double distance;
		if (Math.signum(longitude) != Math.signum(sphericCoordinate
				.getLongitude())) {
			distance = Math.abs(this.getLongitude())
					+ Math.abs(sphericCoordinate.getLongitude());
			if (distance > 180) {
				distance = 360 - distance;
			}
		} else {
			distance = Math.abs(longitude - sphericCoordinate.getLongitude());
		}
		// log.info("Longitudinal distance is: " + distance);
		return distance;
	}

	@Override
	public SphericCoordinate toSphericCoordinate() {
		return this;
	}

	@Override
	public CartesianCoordinate toCartesianCoordinate() {
		double sineLongitude = Math.sin(this.longitude);
		double sineLatitude = Math.sin(this.latitude);
		double cosineLatitude = Math.cos(this.latitude);
		double cosineLongitude = Math.cos(this.longitude);
		double x = radius * sineLongitude * cosineLatitude;
		double y = radius * sineLongitude * sineLatitude;
		double z = radius * cosineLongitude;
		return new CartesianCoordinate(x, y, z);
	}

	@Override
	public boolean isEqual(Coordinate coordinate) {
		if (this == coordinate)
			return true;
		if (coordinate == null)
			return false;
		if (getClass() != coordinate.getClass())
			return false;
		SphericCoordinate other = (SphericCoordinate) coordinate;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Simple getter
	 * 
	 * @return the latitude
	 * @methodtype get
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * We know that latitudes can only have values between ±90°, so we can
	 * integrate the validation check into setter method
	 * 
	 * @param latitude
	 * @throws IllegalArgumentException
	 * @methodtype set
	 */
	public void setLatitude(double latitude) throws IllegalArgumentException {
		if (Double.isNaN(latitude) || latitude < -90 || latitude > 90) {
			throw new IllegalArgumentException(
					"Invalid latitude. Value must be between 90 and -90.");
		} else {
			this.latitude = latitude;
		}
	}

	/**
	 * Simple getter
	 * 
	 * @return the longitude
	 * @methodtype get
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * We know that longitudes can only have values between ±180°, so we can
	 * integrate the validation check into setter method.
	 * 
	 * @param longitude
	 * @methodtype set
	 */
	public void setLongitude(double longitude) {
		// TODO 179.999
		if (Double.isNaN(longitude) || longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException(
					"Invalid longitude. Value must be between 180 and -180.");
		} else {
			this.longitude = longitude;
		}
	}

	/**
	 * @return
	 * @methodtype get
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 * @methodtype set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
}
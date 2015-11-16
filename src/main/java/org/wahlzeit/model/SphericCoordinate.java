package org.wahlzeit.model;

import java.util.logging.Logger;

/**
 * A spheric coordinate describes a point in a spherical coordinate system.
 * Every point contains of a latitudinal value, a longitudinal value and a
 * radial distance (radius).
 * 
 * @author Lucas L�ffler
 *
 */
public class SphericCoordinate extends AbstractCoordinate {

	private static final Logger log = Logger.getLogger(SphericCoordinate.class
			.getName());

	private static final long serialVersionUID = 9148993767234441255L;

	private static final int EARTHRADIUS = 6371;

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
		this(0, 0, EARTHRADIUS);
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
		this(latitude, longitude, EARTHRADIUS);
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
	 * 
	 * @methodtype conversion
	 */
	@Override
	public String toString() {
		return "Spheric coordinate with latitude: " + latitude
				+ " and longitude: " + longitude;
	}

	/**
	 * 
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate toSphericCoordinate() {
		return this;
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
	public double getLatitudinalDistance(SphericCoordinate sphericCoordinate) {
		if (sphericCoordinate == null) {
			throw new IllegalArgumentException(
					"Cannot calculate latitudinal distance. Passed coordinate is null.");
		}
		return Math.abs(this.latitude - sphericCoordinate.getLatitude());
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
	public double getLongitudinalDistance(SphericCoordinate sphericCoordinate) {
		if (sphericCoordinate == null)
			throw new IllegalArgumentException(
					"Cannot calculate longitudinal distance. Passed coordinate is null.");

		double distance;
		if (Math.signum(longitude) != Math.signum(sphericCoordinate
				.getLongitude())) {
			distance = Math.abs(this.getLongitude())
					+ Math.abs(sphericCoordinate.getLongitude());
			if (distance > 180) {
				distance = 360 - distance;
			}
		} else {
			distance = Math.abs(this.longitude
					- sphericCoordinate.getLongitude());
		}
		// log.info("Longitudinal distance is: " + distance);
		return distance;
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
	 * We know that latitudes can only have values between �90�, so we can
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
	 * We know that longitudes can only have values between �180�, so we can
	 * integrate the validation check into setter method.
	 * 
	 * @param longitude
	 * @methodtype set
	 */
	public void setLongitude(double longitude) {
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
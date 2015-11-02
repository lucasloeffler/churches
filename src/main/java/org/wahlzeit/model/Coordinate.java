package org.wahlzeit.model;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * A coordinate describes a location on the earth by defining its latitudinal
 * value (north-south position) and its longitudinal value (east-west position).
 * 
 * @author Lucas Löffler
 *
 */
public class Coordinate implements Serializable {

	private static final Logger log = Logger.getLogger(Coordinate.class
			.getName());

	private static final long serialVersionUID = 9148993767234441255L;

	private double latitude;
	private double longitude;

	/**
	 * Construct an empty Coordinate object with both latitude and longitude = 0
	 * 
	 * @methodtype constructor
	 */
	public Coordinate() {
		setLatitude(0);
		setLongitude(0);
	}

	/**
	 * Construct a Coordinate object with latitude and longitude
	 * 
	 * @param latitude
	 *            the latitude value
	 * @param longitude
	 *            the longitude value
	 * @methodtype constructor
	 *
	 */
	public Coordinate(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
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
		if (Double.isNaN(longitude) || longitude < -180 || longitude > 180) {
			throw new IllegalArgumentException(
					"Invalid longitude. Value must be between 180 and -180.");
		} else {
			this.longitude = longitude;
		}
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

		// We need angle in radians for the formula, so we first have to convert
		// the latitudinal values and the longitudinal distance to radians
		double radiansLatitudeThis = Math.toRadians(this.latitude);
		double radiansLatitudeOther = Math.toRadians(coordinate.getLatitude());
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

		// Finally compute the distance, assume earth radius is 6371km
		double distance = 6371 * angleRadians;
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
	 * @methodtype get
	 */
	public double getLatitudinalDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException(
					"Cannot calculate latitudinal distance. Passed coordinate is null.");
		}
		return Math.abs(latitude - coordinate.getLatitude());
	}

	/**
	 * Calculates the longitudinal distance between this coordinate and the
	 * passed coordinate by subtracting the longitude of the passed coordinate
	 * from the longitude of this coordinate and returning the absolute value of
	 * the result. TODO fix comment
	 * 
	 * @param coordinate
	 *            The coordinate to calculate the distance to
	 * @return The longitudinal distance
	 * @methodtype get
	 */
	public double getLongitudinalDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException(
					"Cannot calculate longitudinal distance. Passed coordinate is null.");
		}
		double distance;
		if (Math.signum(longitude) != Math.signum(coordinate.getLongitude())) {
			distance = Math.abs(this.getLongitude())
					+ Math.abs(coordinate.getLongitude());
			if (distance > 180) {
				distance = 360 - distance;
			}
		} else {
			distance = Math.abs(longitude - coordinate.getLongitude());
		}
		// log.info("Longitudinal distance is: " + distance);
		return distance;
	}
}
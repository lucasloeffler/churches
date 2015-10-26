package org.wahlzeit.model;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * A coordinate describes a location on the earth by defining its latitudinal
 * value (north-south position) and its longitudinal (east-west position) value.
 * 
 * @author Lucas Löffler
 *
 */
public class Coordinate implements Serializable {

	// private static final Logger log = Logger.getLogger(Coordinate.class
	// .getName());

	private static final long serialVersionUID = 9148993767234441255L;

	private double latitude;
	private double longitude;

	/**
	 * Construct an empty Coordinate object with both latitude and longitude = 0
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
	 */
	public Coordinate(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/**
	 * Simple getter
	 * 
	 * @return the latitude
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
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * We know that longitudes can only have values between ±180°, so we can
	 * integrate the validation check into setter method.
	 * 
	 * @param longitude
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
	 * Calculates the distance between this coordinate and the passed coordinate
	 * by constructing a new {@code Coordinate} object that contains the
	 * latitudinal distance to the passed coordinate as latitude and the
	 * longitudinal distance to the passed coordinate as longitude.
	 * 
	 * @param coordinate
	 * @return
	 */
	public Coordinate getDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException(
					"Cannot calculate distance. Passed coordinate is null.");
		}
		return new Coordinate(getLatitudinalDistance(coordinate),
				getLongitudinalDistance(coordinate));
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
	 * the result.
	 * 
	 * @param coordinate
	 *            The coordinate to calculate the distance to
	 * @return The longitudinal distance
	 */
	public double getLongitudinalDistance(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException(
					"Cannot calculate longitudinal distance. Passed coordinate is null.");
		}
		return Math.abs(longitude - coordinate.getLongitude());
	}
}
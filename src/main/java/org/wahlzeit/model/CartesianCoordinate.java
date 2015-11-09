package org.wahlzeit.model;

import java.util.logging.Logger;

/**
 * A cartesian coordinate describes a point in a three-dimensional cartesian
 * coordinate system. Every point contains of three axis values (x, y, z) that
 * describe its position in a space.
 * 
 * @author Lucas Löffler
 *
 */
public class CartesianCoordinate implements Coordinate {

	private static final Logger log = Logger
			.getLogger(CartesianCoordinate.class.getName());

	private static final long serialVersionUID = -484535636537846455L;

	private double x;

	private double y;

	private double z;

	/**
	 * 
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		this.setX(0);
		this.setY(0);
		this.setZ(0);
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

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
					"Cannot calculate distance. Passed coordinate is null.");
		CartesianCoordinate cartesianCoordinate;
		if (coordinate instanceof CartesianCoordinate)
			cartesianCoordinate = (CartesianCoordinate) coordinate;
		else
			cartesianCoordinate = coordinate.toCartesianCoordinate();

		double differenceX = cartesianCoordinate.getX() - this.getX();
		double differenceY = cartesianCoordinate.getY() - this.getY();
		double differenceZ = cartesianCoordinate.getZ() - this.getZ();

		double distance = Math.sqrt(Math.pow(differenceX, 2)
				+ Math.pow(differenceY, 2) + Math.pow(differenceZ, 2));

		// log.info("Distance is: " + distance);

		return distance;
	}

	/**
	 * 
	 * @methodtype query
	 */
	@Override
	public boolean isEqual(Coordinate coordinate) {
		if (this == coordinate)
			return true;
		if (coordinate == null)
			return false;
		if (getClass() != coordinate.getClass())
			return false;
		if (coordinate instanceof CartesianCoordinate) {
			CartesianCoordinate other = (CartesianCoordinate) coordinate;
			if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
				return false;
			if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
				return false;
			if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
				return false;
			return true;
		} else {
			return this.isEqual(coordinate.toCartesianCoordinate());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * 
	 * @methodtype conversion
	 */
	@Override
	public CartesianCoordinate toCartesianCoordinate() {
		return this;
	}

	/**
	 * 
	 * @methodtype conversion
	 */
	@Override
	public SphericCoordinate toSphericCoordinate() {
		double r = Math.sqrt(Math.pow(this.getX(), 2)
				+ Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
		double latitude = Math.toDegrees(Math.asin(this.getZ() / r));
		double longitude = Math.toDegrees(Math.atan2(this.getY(), this.getX()));

		return new SphericCoordinate(latitude, longitude, r);
	}

	/**
	 * 
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @methodtype set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @methodtype set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * 
	 * @methodtype set
	 */
	public void setZ(double z) {
		this.z = z;
	}
}
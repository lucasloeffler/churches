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
public class CartesianCoordinate extends AbstractCoordinate {

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
		this(0, 0, 0);
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
	 * 
	 * @methodtype conversion
	 */
	@Override
	public String toString() {
		return "Cartesian coordinate with values x: " + x + ", y: " + y
				+ ", z: " + z;
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
		log.info("Latitude: " + latitude);
		log.info("Longitude: " + longitude);
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
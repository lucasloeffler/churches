package org.wahlzeit.model;

/**
 * A cartesian coordinate describes a point in a three-dimensional cartesian
 * coordinate system. Every point contains of three axis values (x, y, z) that
 * describe its position in a space.
 * 
 * @author Lucas Löffler
 *
 */
public class CartesianCoordinate extends AbstractCoordinate {

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
		assertClassInvariants();
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	public CartesianCoordinate(Coordinate coordinate) {
		assertValidCoordinate(coordinate);
		CartesianCoordinate cartesianCoordiante = doGetCartesianCoordinateFromSphericValues(
				coordinate.getLatitude(), coordinate.getLongitude(),
				coordinate.getRadius());
		this.setX(cartesianCoordiante.getX());
		this.setY(cartesianCoordiante.getY());
		this.setZ(cartesianCoordiante.getZ());
		assertClassInvariants();
	}

	/**
	 * 
	 * @methodtype conversion
	 */
	private static CartesianCoordinate doGetCartesianCoordinateFromSphericValues(
			double latitude, double longitude, double radius) {
		double sineLongitude = Math.sin(longitude);
		double sineLatitude = Math.sin(latitude);
		double cosineLatitude = Math.cos(latitude);
		double cosineLongitude = Math.cos(longitude);
		double x = radius * sineLongitude * cosineLatitude;
		double y = radius * sineLongitude * sineLatitude;
		double z = radius * cosineLongitude;
		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * 
	 * @methodtype conversion
	 */
	private SphericCoordinate toSphericCoordinate() {
		double radius = Math.sqrt(Math.pow(this.getX(), 2)
				+ Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
		double latitude = Math.toDegrees(Math.asin(this.getZ() / radius));
		double longitude = Math.toDegrees(Math.atan2(this.getY(), this.getX()));
		SphericCoordinate sphericCoordinate = new SphericCoordinate(latitude,
				longitude, radius);
		assertValidCoordinate(sphericCoordinate);
		return sphericCoordinate;
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

	@Override
	public double getLatitude() {
		return this.toSphericCoordinate().getLatitude();
	}

	@Override
	public void setLatitude(double latitude) {
		assertValidLatitude(latitude);
		CartesianCoordinate cartesianCoordinate = doGetCartesianCoordinateFromSphericValues(
				latitude, this.getLongitude(), this.getRadius());
		setX(cartesianCoordinate.getX());
		setY(cartesianCoordinate.getY());
		setZ(cartesianCoordinate.getZ());
		assertClassInvariants();
	}

	@Override
	public double getLongitude() {
		return this.toSphericCoordinate().getLongitude();
	}

	@Override
	public void setLongitude(double longitude) {
		assertValidLongitude(longitude);
		CartesianCoordinate cartesianCoordinate = doGetCartesianCoordinateFromSphericValues(
				this.getLatitude(), longitude, this.getRadius());
		setX(cartesianCoordinate.getX());
		setY(cartesianCoordinate.getY());
		setZ(cartesianCoordinate.getZ());
		assertClassInvariants();
	}

	@Override
	public double getRadius() {
		return this.toSphericCoordinate().getRadius();
	}

	@Override
	public void setRadius(double radius) {
		assertValidRadius(radius);
		CartesianCoordinate cartesianCoordinate = doGetCartesianCoordinateFromSphericValues(
				this.getLatitude(), this.getLongitude(), radius);
		setX(cartesianCoordinate.getX());
		setY(cartesianCoordinate.getY());
		setZ(cartesianCoordinate.getZ());
		assertClassInvariants();
	}
}
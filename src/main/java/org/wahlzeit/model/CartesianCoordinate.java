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

	private final double x;

	private final double y;

	private final double z;

	/**
	 * @methodtype create
	 */
	public static CartesianCoordinate getCartesianCoordinate(double x,
			double y, double z) {
		CartesianCoordinate tmp = new CartesianCoordinate(x, y, z);
		synchronized (instances) {
			for (Coordinate coordinate : instances) {
				if (coordinate.equals(tmp))
					return (CartesianCoordinate) coordinate;
			}
			instances.add(tmp);
			return tmp;
		}
	}

	/**
	 * @methodtype create
	 */
	public static CartesianCoordinate getCartesianCoordinate(
			Coordinate coordinate) {
		assertValidCoordinate(coordinate);
		CartesianCoordinate cartesianCoordinate;
		if (coordinate instanceof CartesianCoordinate)
			cartesianCoordinate = (CartesianCoordinate) coordinate;
		else
			cartesianCoordinate = doGetCartesianCoordinateFromSphericValues(
					coordinate.getLatitude(), coordinate.getLongitude(),
					coordinate.getRadius());
		return getCartesianCoordinate(cartesianCoordinate.getX(),
				cartesianCoordinate.getY(), cartesianCoordinate.getZ());
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		assertClassInvariants();
	}

	/**
	 * 
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		this(0, 0, 0);
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
	public Coordinate setX(double x) {
		return getCartesianCoordinate(x, this.y, this.z);
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
	public Coordinate setY(double y) {
		return getCartesianCoordinate(this.x, y, this.z);
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
	public Coordinate setZ(double z) {
		return getCartesianCoordinate(this.x, this.y, z);
	}

	@Override
	public double getLatitude() {
		return this.toSphericCoordinate().getLatitude();
	}

	@Override
	public Coordinate setLatitude(double latitude) {
		assertValidLatitude(latitude);
		CartesianCoordinate cartesianCoordinate = doGetCartesianCoordinateFromSphericValues(
				latitude, this.getLongitude(), this.getRadius());
		return cartesianCoordinate;
	}

	@Override
	public double getLongitude() {
		return this.toSphericCoordinate().getLongitude();
	}

	@Override
	public Coordinate setLongitude(double longitude) {
		assertValidLongitude(longitude);
		CartesianCoordinate cartesianCoordinate = doGetCartesianCoordinateFromSphericValues(
				this.getLatitude(), longitude, this.getRadius());
		return cartesianCoordinate;
	}

	@Override
	public double getRadius() {
		return this.toSphericCoordinate().getRadius();
	}

	@Override
	public Coordinate setRadius(double radius) {
		assertValidRadius(radius);
		CartesianCoordinate cartesianCoordinate = doGetCartesianCoordinateFromSphericValues(
				this.getLatitude(), this.getLongitude(), radius);
		return cartesianCoordinate;
	}
}
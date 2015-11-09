package org.wahlzeit.model;

import java.io.Serializable;

public interface Coordinate extends Serializable {

	/**
	 * @param coordinate
	 * @return the distance between the two coordinates
	 * 
	 * @methodtype get
	 */
	public double getDistance(Coordinate coordinate);

	/**
	 * @param coordinate
	 * @return True if both coordinates are equal. False if they are not.
	 * 
	 * @methodtype query
	 */
	public boolean isEqual(Coordinate coordinate);

	/**
	 * 
	 * @return the spheric representation of a coordinate
	 * 
	 * @methodtype conversion
	 */
	public SphericCoordinate toSphericCoordinate();

	/**
	 * 
	 * @return the cartesian representation of a coordinate
	 * 
	 * @methodtype conversion
	 */
	public CartesianCoordinate toCartesianCoordinate();

}
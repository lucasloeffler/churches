package org.wahlzeit.model;

import java.io.Serializable;

/**
 * @author Lucas Löffler
 *
 */
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
	 * @return the spheric representation of a coordinate
	 * 
	 * @methodtype conversion
	 */
	public SphericCoordinate toSphericCoordinate();

	/**
	 * @return the String representation of a coordinate
	 * 
	 * @methodtype conversion
	 */
	public String toString();

}
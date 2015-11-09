package org.wahlzeit.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Container;

/**
 * Location consists of a name and a {@link Coordinate}.
 * 
 * @author Lucas Löffler
 */
public class Location implements Serializable {

	private static final long serialVersionUID = -1565006012734834270L;

	/**
	 * The locations name
	 */
	protected String name;

	/**
	 * The locations coordinate
	 */
	@Container
	protected Coordinate coordinate;

	/**
	 * @methodtype constructor
	 */
	public Location() {
		// empty String as name
		this.setName("");
	}

	/**
	 * @methodtype constructor
	 */
	public Location(String name, Coordinate coordinate) {
		this.setName(name);
		this.setCoordinate(coordinate);
	}

	/**
	 * @methodtype get
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodtype set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
}
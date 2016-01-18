package org.wahlzeit.model;

import java.io.Serializable;

import org.wahlzeit.services.DataObject;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Entity class that represents a church. Every church has a unique id, a
 * specific name, a location, a {@link ChurchType} and a year of completion.
 * 
 * @author Lucas Löffler
 *
 */
@Entity
public class Church extends DataObject implements Serializable {

	@Id
	private Long id;

	/**
	 * Name of the church, e.g. St. Peter's Basilica
	 */
	private String name;

	/**
	 * Name of the town or village the church is located, e.g. Vatican City or
	 * Berlin
	 */
	private String location;

	/**
	 * {@link ChurchType} of the church
	 */
	private ChurchType type;

	/**
	 * Year of completion, e.g. 1626
	 */
	private int yearOfCompletion;

	/**
	 * @methodtype constructor
	 */
	public Church() {
		// empty constructor, do nothing
	}

	/**
	 * @methodtype constructor
	 */
	public Church(String name, ChurchType churchType) {
		this.setName(name);
		this.setType(churchType);
	}

	/**
	 * @methodtype constructor
	 */
	public Church(String name, String location, ChurchType type,
			int yearOfCompletion) {
		this.setName(name);
		this.setLocation(location);
		this.setType(type);
		this.setYearOfCompletion(yearOfCompletion);
	}

	/**
	 * @methodtype get
	 */
	public Long getId() {
		return this.id;
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
	public String getLocation() {
		return location;
	}

	/**
	 * @methodtype set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @methodtype get
	 */
	public ChurchType getType() {
		return type;
	}

	/**
	 * @methodtype set
	 */
	public void setType(ChurchType type) {
		this.type = type;
	}

	/**
	 * @methodtype get
	 */
	public int getYearOfCompletion() {
		return yearOfCompletion;
	}

	/**
	 * @methodtype set
	 */
	public void setYearOfCompletion(int yearOfCompletion) {
		this.yearOfCompletion = yearOfCompletion;
	}
}

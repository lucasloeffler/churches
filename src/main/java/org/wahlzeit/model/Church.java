package org.wahlzeit.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Entity class that represents a church. Every church has a unique id, a
 * specific name, a {@link ChurchType} and a year of completion.
 * 
 * @author Lucas Löffler
 *
 */
@Entity
public class Church implements Serializable {

	private static final long serialVersionUID = -3845788324666569497L;

	@Id
	protected String id;

	/**
	 * Name of the church
	 */
	protected String name;

	/**
	 * ChurchType of the church
	 */
	protected ChurchType type;

	/**
	 * Year of completion
	 */
	protected int yearOfCompletion;

	/**
	 * @methodtype constructor
	 */
	public Church() {
		// do nothing
	}

	/**
	 * @methodtype constructor
	 */
	public Church(String name) {
		this.setName(name);
		this.setType(ChurchType.UNKNOWN);
	}

	/**
	 * @methodtype constructor
	 */
	public Church(String name, ChurchType type, int yearOfCompletion) {
		this.setName(name);
		this.setType(type);
		this.setYearOfCompletion(yearOfCompletion);
	}

	/**
	 * @methodtype get
	 */
	public String getId() {
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

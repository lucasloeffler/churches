package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * Represents a photo of a church.
 * 
 * @author Lucas Löffler
 *
 */
@Subclass(index = true)
public class ChurchPhoto extends Photo {

	protected Church church;

	/**
	 * @methodtype constructor
	 */
	public ChurchPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public ChurchPhoto(Church church) {
		super();
		this.setChurch(church);
	}

	/**
	 * @methodtype constructor
	 */
	public ChurchPhoto(PhotoId myId) {
		super(myId);
	}

	/**
	 * @methodtype get
	 */
	public Church getChurch() {
		return church;
	}

	/**
	 * @methodtype set
	 */
	public void setChurch(Church church) {
		this.church = church;
	}

}
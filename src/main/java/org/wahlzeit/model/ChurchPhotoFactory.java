package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

/**
 * 
 * @author Lucas Löffler
 *
 */
@Pattern(
		name = "Singleton",
		participants = {
			"Singleton" // This class
		}
	)
public class ChurchPhotoFactory extends PhotoFactory {

	private static final Logger log = Logger.getLogger(ChurchPhotoFactory.class
			.getName());

	private static ChurchPhotoFactory instance = null;

	public ChurchPhotoFactory() {
		// do nothing
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized ChurchPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage()
					.addAction("setting generic ChurchPhotoFactory").toString());
			setInstance(new ChurchPhotoFactory());
		}
		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(
			ChurchPhotoFactory churchPhotoFactory) {
		if (instance != null) {
			throw new IllegalStateException(
					"attempt to initalize ChurchPhotoFactory twice");
		}
		instance = churchPhotoFactory;
	}

	/**
	 * Creates a new church photo
	 * 
	 * @methodtype factory
	 */
	@Override
	public ChurchPhoto createPhoto() {
		return new ChurchPhoto();
	}

	/**
	 * Creates a new church photo with the specified id
	 * 
	 * @methodtype factory
	 * 
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new ChurchPhoto(id);
	}

	/**
	 * Creates a new church photo with given church
	 * 
	 * @methodtype factory
	 */
	public Photo createPhoto(Church church) {
		return new ChurchPhoto(church);
	}

}

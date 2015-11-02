package org.wahlzeit.model;

/**
 * 
 * @author Lucas Löffler
 *
 */
public class ChurchPhotoFactory extends PhotoFactory {

	public ChurchPhotoFactory() {
		// do nothing
	}

	/**
	 * Creates a new church photo
	 * 
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
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

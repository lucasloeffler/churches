package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Set;

import org.wahlzeit.services.DataObject;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * @author Lucas Löffler
 *
 */
@Entity
public class ChurchType extends DataObject {

	@Id
	private Long id;

	/**
	 * Name of the church type, e.g. cathedral
	 */
	private String name;

	/**
	 * May contain the supertype of a church type, e.g. cathedral is the
	 * supertype of duomo
	 */
	private ChurchType superType;

	/**
	 * Set of subtypes of a certain church type
	 */
	private Set<ChurchType> subTypes;

	/**
	 * Latin name of the church type, e.g. ecclesia cathedralis is the Latin
	 * name for cathedral
	 */
	private String latinName;

	/**
	 * German name of the church type, e.g. Kathedrale is the German name for
	 * cathedral
	 */
	private String germanName;

	/**
	 * Additional description of the church type
	 */
	private String description;

	public ChurchType(String name, ChurchType superType,
			Set<ChurchType> subTypes, String latinName, String germanName,
			String description) {
		this.name = name;
		this.superType = superType;
		this.subTypes = subTypes;
		this.latinName = latinName;
		this.germanName = germanName;
		this.description = description;
	}

	public ChurchType(String name, String latinName, String germanName,
			String description) {
		this.superType = null;
		this.subTypes = null;
		this.name = name;
		this.latinName = latinName;
		this.germanName = germanName;
		this.description = description;
	}

	public ChurchType(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChurchType getSuperType() {
		return superType;
	}

	public void setSuperType(ChurchType superType) {
		this.superType = superType;
	}

	public Set<ChurchType> getSubTypes() {
		return this.subTypes;
	}

	public void setSubTypes(Set<ChurchType> subTypes) {
		this.subTypes = subTypes;
	}

	public void addSubtype(ChurchType churchType) {
		assertValidChurchType(churchType);
		if (getSubTypes() == null)
			subTypes = new HashSet<ChurchType>();
		subTypes.add(churchType);
	}

	public void removeSubtype(ChurchType churchType) {
		assertValidChurchType(churchType);
		if (this.subTypes != null) {
			if (this.subTypes.contains(churchType))
				this.subTypes.remove(churchType);
			else
				throw new UnsupportedOperationException(
						"Cannot remove subtye. Subtype set does not contain this subtype.");
		} else {
			throw new UnsupportedOperationException(
					"Cannot remove subtype. Subtypes set is null");
		}
	}

	protected static void assertValidChurchType(ChurchType churchType) {
		if (churchType == null) {
			throw new UnsupportedOperationException("Error. churchType is null");
		}
	}

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public String getGermanName() {
		return germanName;
	}

	public void setGermanName(String germanName) {
		this.germanName = germanName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
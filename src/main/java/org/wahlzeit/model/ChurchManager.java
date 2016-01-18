package org.wahlzeit.model;

import java.util.HashMap;

public class ChurchManager {

	private static ChurchManager instance;

	private static HashMap<String, Church> churches = new HashMap<String, Church>();

	private static HashMap<String, ChurchType> churchTypes = new HashMap<String, ChurchType>();

	public static ChurchManager getInstance() {
		if (instance == null)
			return new ChurchManager();
		else
			return instance;
	}

	public static void setInstance(ChurchManager churchManager) {
		if (instance != null)
			throw new IllegalStateException(
					"attempt to initalize ChurchManager twice");
		instance = churchManager;
	}

	public ChurchType getChurchType(String name) {
		ChurchType churchType;
		if (!churchTypes.containsKey(name)) {
			churchType = new ChurchType(name);
			churchTypes.put(churchType.getName(), churchType);
		} else {
			churchType = churchTypes.get(name);
		}
		return churchType;
	}

	public Church getChurch(String name, ChurchType churchType) {
		churchType = getChurchType(churchType.getName());
		Church church = churchType.createInstance(name);
		churches.put(church.getName(), church);
		return church;
	}
}

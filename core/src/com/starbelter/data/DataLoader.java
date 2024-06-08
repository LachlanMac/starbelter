package com.starbelter.data;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.starbelter.data.gson.PlanetNames;
import com.starbelter.util.Util;


public class DataLoader {

	public static ArrayList<String> bodyPrefixes;
	public static ArrayList<String> bodySuffixes;
	public static ArrayList<String> bodyMiddlefixes;
	public static ArrayList<String> allBodyNames = new ArrayList<String>();

	public static void load() {

		Gson gson = new Gson();

		PlanetNames planetNames = gson.fromJson(
				Gdx.files.local("./data/names/bodynames.json").readString(), PlanetNames.class);
		bodyPrefixes = (ArrayList<String>) planetNames.getPrefixes();
		bodySuffixes = (ArrayList<String>) planetNames.getSuffixes();
		bodyMiddlefixes = (ArrayList<String>) planetNames.getMiddlefixes();
	}

	public static String getBodyName() {
		String name = "";
		boolean uniqueName = false;
		String prefix = bodyPrefixes.get(Util.getRandom().nextInt(bodyPrefixes.size()));
		String suffix = bodySuffixes.get(Util.getRandom().nextInt(bodySuffixes.size()));
		String middlefix = bodyMiddlefixes
				.get(Util.getRandom().nextInt(bodyMiddlefixes.size()));

		while (!uniqueName) {
			switch (Util.getRandom().nextInt(4)) {
			case 0:
				name = prefix + middlefix;
				break;
			case 1:
				name = prefix + middlefix + suffix;
				break;
			case 2:
			case 3:
				name = prefix + suffix;
				break;
			}
			if (!allBodyNames.contains(name)) {
				uniqueName = true;
			}
		}

		return name;

	}

}

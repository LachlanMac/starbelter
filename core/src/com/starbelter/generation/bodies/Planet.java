package com.starbelter.generation.bodies;

import java.util.ArrayList;

import com.starbelter.util.Util;

public class Planet extends CelestialBody {

	public static final float MOON_CHANCE = 0.1f;
	private ArrayList<Moon> moons;

	public Planet(int size, int orbit, Star star, float temperature, float ageInMillions) {
		super(size, orbit, star, temperature, ageInMillions);
		buildMoons();
		calculate();
	}

	protected void buildMoons() {

		moons = new ArrayList<Moon>();
		int orbit = 1;
		if (size < 6)
			return;

		for (int i = 0; i < Util.getRandom().nextInt(size - 5); i++) {
			int maxSize = size - (size / 2);
			int moonSize = Math.max(0, Util.getRandom().nextInt(maxSize));
			float moonAgeInMillions = Math.max(10, ageInMillions - Util.getRandom().nextFloat(0, 10));
			Moon m = new Moon(moonSize, orbit, star, temperature, moonAgeInMillions, this);
			moons.add(m);
			orbit += (1 + Util.getRandom().nextInt(3));
		}

	}

	public ArrayList<Moon> getMoons() {
		return moons;
	}

}

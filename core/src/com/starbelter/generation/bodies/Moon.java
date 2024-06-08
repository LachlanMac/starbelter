package com.starbelter.generation.bodies;

public class Moon extends CelestialBody {

	private Planet planet;

	public Moon(int size, int orbit, Star star, float temperature, float age,
			Planet planet) {
		super(size, orbit, star, temperature, age);
		this.setPlanet(planet);
		calculate();
	}

	// very simplified way of determining how much this will increase the tectonics
	// of a planet.
	public float getTectonicInfluence() {
		float influence = Math.max(0, size - orbit);
		return influence;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

}

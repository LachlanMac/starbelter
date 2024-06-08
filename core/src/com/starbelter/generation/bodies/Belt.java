package com.starbelter.generation.bodies;

import java.util.ArrayList;
import java.util.List;

import com.starbelter.util.Util;

public class Belt {

	private double RADIUS = 100;
	private Star star;
	private Planet planet;
	private int orbit;
	List<Asteroid> asteroids;

	public Belt(Star star, int orbit, int temperature) {
		this.star = star;
		this.orbit = orbit;
		generateBelt(Util.getRandom().nextInt(1000) + 500);
	}
	
	public Belt(Planet planet, int orbit, int temperature) {
		this.planet = planet;
		this.orbit = orbit;
		generateBelt(Util.getRandom().nextInt(1000) + 500);
	}

	public List<Asteroid> generateBelt(int totalAsteroids) {
		asteroids = new ArrayList<>();
		double angleIncrement = 360.0 / totalAsteroids; // angle increment in degrees

		for (int i = 0; i < totalAsteroids; i++) {
			double angle = Math.toRadians(angleIncrement * i); // convert angle to radians
			int x = (int) (RADIUS * Math.cos(angle)); // calculate x coordinate
			int y = (int) (RADIUS * Math.sin(angle)); // calculate y coordinate

			asteroids.add(new Asteroid(x, y, this));
		}

		return asteroids;
	}
	
	

}

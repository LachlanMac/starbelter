package com.starbelter.generation.bodies;

import java.util.ArrayList;

import com.starbelter.generation.Galaxy;
import com.starbelter.generation.bodies.Star.StarClass;
import com.starbelter.util.Util;

/* class that represents a star system */
public class Star {
	
	//TO:DO define this in a configuration sheet
	public static final int MAX_ORBITS = 50;
	public static final int PLANET_CHANCE = 8;
	private static final int BELT_CHANCE = 1;
	public static final float INVERSE_SQUARE_CONSTANT = 20.0f;
	public static final double STEFAN_BOLTZMANN_CONSTANT = 5.67e-8;
	public static final int MAX_PLANET_SIZE = 80;
	
	//class variables
	private int temperature, size;
	private double radiation, temperatureFactor;
	private float ageInMillions = 0;

	private StarClass starClass;
	private ArrayList<Planet> planets;
	private ArrayList<Belt> belts;
	
	public enum StarClass {
		O, B, A, F, G, K, M, X
	}


	//constructor
	public Star(StarClass starClass, int temperature, double radiation, int size,
			float ageInMillions, double temperatureFactor) {
		this.starClass = starClass;
		this.temperature = temperature;
		this.radiation = radiation;
		this.size = size;
		this.ageInMillions = ageInMillions;
		this.temperatureFactor = temperatureFactor;
		planets = new ArrayList<Planet>();
		belts = new ArrayList<Belt>();
		Galaxy.STAR_COUNT++;
		addPlanets();
		// System.out.println("\n\n" + this);

	}
	
	
	//function that adds planets to the star
	public void addPlanets() {
		//if blackhole..no planets
		if (starClass == StarClass.X)
			return;
		
		//for each possible orbit
		for (int i = 0; i < MAX_ORBITS; i++) {
			//get probability
			int random = Util.getRandomPercentage();
			if (random < BELT_CHANCE) {
				Belt b = new Belt(this, i, 0);
				belts.add(b);
				Galaxy.BELT_COUNT++;
			} else if (random < PLANET_CHANCE) {
				//calculate surface radiation based on distance
				double surfaceRadiation = (this.radiation * Star.INVERSE_SQUARE_CONSTANT
						* temperatureFactor) / Math.pow(((i * 2) + 1), 2);
				//calculate surface temperature and convert to celcius
				double surfaceTemperature = Math
						.pow(surfaceRadiation / STEFAN_BOLTZMANN_CONSTANT, 0.25) - 273.0f;
				//create new planet
				Planet p = new Planet(Util.getRandom().nextInt(MAX_PLANET_SIZE), i, this,
						Math.max(-273, (float) surfaceTemperature),
						Util.getRandom().nextFloat(5, ageInMillions - 5));
				//add planet to the list
				planets.add(p);
				
				
				//debug
				Galaxy.PLANET_COUNT++;
				if (CelestialBody.isHabitable(p)) {
					Galaxy.HABITABLE_PLANET_COUNT++;
				}
				for (Moon m : p.getMoons()) {
					if (CelestialBody.isHabitable(m)) {
						Galaxy.HABITABLE_MOON_COUNT++;
					}
					Galaxy.MOON_COUNT++;
				}
			}
		}
	}
	
	//create star based on ages according to observable averages
	public static Star createStar(float minAge, float maxAge) {
		Galaxy.STAR_COUNT++;
		
		float probability = Util.getRandom().nextFloat();
		StarClass starClass;
		int temperature;
		float radiation;
		int size;
		float ageInMillions = 0;
		float temperatureFactor;

		if (probability < 0.03) {
			starClass = StarClass.O;
			temperature = Util.getRandom().nextInt(30000, 50001);
			temperatureFactor = 1.5f; // Higher factor for hotter stars
			radiation = (Util.getRandom().nextFloat() * 100000 + 50000)
					* temperatureFactor;
			size = Util.getRandom().nextInt(15, 21);
			ageInMillions = Util.getRandom().nextFloat(minAge, Math.min(maxAge, 50));
		} else if (probability < 0.10) {
			starClass = StarClass.B;
			temperature = Util.getRandom().nextInt(10000, 30001);
			temperatureFactor = 1.3f;
			radiation = (Util.getRandom().nextFloat() * 50000 + 30000)
					* temperatureFactor;
			size = Util.getRandom().nextInt(10, 16);
			ageInMillions = Util.getRandom().nextFloat(minAge, Math.min(maxAge, 100));
		} else if (probability < 0.20) {
			starClass = StarClass.A;
			temperature = Util.getRandom().nextInt(7500, 10001);
			temperatureFactor = 1.1f;
			radiation = (Util.getRandom().nextFloat() * 30000 + 20000)
					* temperatureFactor;
			size = Util.getRandom().nextInt(5, 10);
			ageInMillions = Util.getRandom().nextFloat(minAge, Math.min(maxAge, 2000));
		} else if (probability < 0.35) {
			starClass = StarClass.F;
			temperature = Util.getRandom().nextInt(6000, 7501);
			temperatureFactor = 1.05f;
			radiation = (Util.getRandom().nextFloat() * 20000 + 15000)
					* temperatureFactor;
			size = Util.getRandom().nextInt(4, 7);
			ageInMillions = Util.getRandom().nextFloat(minAge, Math.min(maxAge, 3000));
		} else if (probability < 0.55) {
			starClass = StarClass.G;
			temperature = Util.getRandom().nextInt(5200, 6001);
			temperatureFactor = 1.0f; // Baseline factor for Sun-like stars
			radiation = (Util.getRandom().nextFloat() * 15000 + 10000)
					* temperatureFactor;
			size = Util.getRandom().nextInt(3, 5);
			ageInMillions = Util.getRandom().nextFloat(minAge, Math.min(maxAge, 10000));
		} else if (probability < 0.80) {
			starClass = StarClass.K;
			temperature = Util.getRandom().nextInt(3500, 5201);
			temperatureFactor = 0.85f;
			radiation = (Util.getRandom().nextFloat() * 10000 + 5000) * temperatureFactor;
			size = Util.getRandom().nextInt(2, 4);
			ageInMillions = Util.getRandom().nextFloat(minAge, Math.min(maxAge, 30000));
		} else if (probability < 0.95) {
			starClass = StarClass.M;
			temperature = Util.getRandom().nextInt(2000, 3501);
			temperatureFactor = 0.7f; // Lower factor for cooler stars
			radiation = (Util.getRandom().nextFloat() * 5000 + 2500) * temperatureFactor;
			size = Util.getRandom().nextInt(1, 3);
			ageInMillions = Util.getRandom().nextFloat(minAge, Math.min(maxAge, 100000));
		} else {
			starClass = StarClass.X; // black hole
			temperature = 0;
			temperatureFactor = 0.6f; // Factor for radiation not caused by temperature
			radiation = (Util.getRandom().nextFloat() * 5000 + 2500) * temperatureFactor;
			size = Util.getRandom().nextInt(1, 3);
			ageInMillions = Util.getRandom().nextFloat(minAge, maxAge); 
		}

		return new Star(starClass, temperature, radiation, size, ageInMillions,
				temperatureFactor);
	}

	//getters and setters
	
	public StarClass getStarClass() {
		return starClass;
	}

	public int getTemperature() {
		return temperature;
	}

	public double getRadiation() {
		return radiation;
	}

	public float getAgeInMillions() {
		return ageInMillions;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Star : [" + starClass.name() + "] temp:" + temperature + " rads:"
				+ radiation + " size:" + size + " Age in billions= "
				+ (ageInMillions / 1000f);
	}

	public double getTemperatureFactor() {
		return temperatureFactor;
	}

	public void setTemperatureFactor(double temperatureFactor) {
		this.temperatureFactor = temperatureFactor;
	}
}
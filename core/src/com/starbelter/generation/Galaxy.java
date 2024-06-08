package com.starbelter.generation;

import com.starbelter.generation.bodies.Star;

public class Galaxy {

	
	//for debug purposes
	public static int PLANET_COUNT = 0;
	public static int MOON_COUNT = 0;
	public static int STAR_COUNT = 0;
	public static int HABITABLE_MOON_COUNT = 0;
	public static int HABITABLE_PLANET_COUNT = 0;
	public static int GOLDILOX_COUNT = 0;
	public static int BELT_COUNT = 0;

	
	//to:do :  make these values come from a loadable config file
	private static final int DEFAULT_GALAXY_RADIUS = 40;
	private static final int DEFAULT_GALAXY_CENTER_RADIUS = 1;
	private static final int STAR_AGE_IN_MILLIONS_MIN = 10;
	private static final int STAR_AGE_IN_MILLIONS_MAX = 10000;


	//class variables
	private Sector[][] sectors;
	private int radius, galaxyCenterRadius, diameter;

	//constructor
	public Galaxy() {
		radius = DEFAULT_GALAXY_RADIUS;
		galaxyCenterRadius = DEFAULT_GALAXY_CENTER_RADIUS;
		//calculate galactic diameter
		diameter = 2 * radius + 1;
		
		initializeSectors();

		//debug
		System.out.println("Total Stars:" + Galaxy.STAR_COUNT + "\nPlanets:"
				+ Galaxy.PLANET_COUNT + "\nTotal Moons:" + Galaxy.MOON_COUNT
				+ "\nHabitable Planets:" + Galaxy.HABITABLE_PLANET_COUNT
				+ "\nHabitable Moons:" + Galaxy.HABITABLE_MOON_COUNT + "\nGoldilox Count:"
				+ GOLDILOX_COUNT + "\nBelt Count:" + BELT_COUNT);
	}

	
	private void initializeSectors() {
		//set center
		int centerX = radius;
		int centerY = radius;
		
		sectors = new Sector[diameter][diameter];
		
		//loop through each open sector
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[i].length; j++) {
				//create sector at galactic coordinates
				sectors[i][j] = new Sector(i, j);
				//find delta x & y
				int dx = i - centerX;
				int dy = j - centerY;
				//calculate sector distance from galactic center
				double distanceFromCenter = Math.sqrt(dx * dx + dy * dy);
				
				//for now, always create a star for each sector within the galactic bounds
				//to:do, randomize if there is a star or not, with probability decreasing based on distance to center
				if (distanceFromCenter <= radius && distanceFromCenter >= galaxyCenterRadius) {
					sectors[i][j].setStar(Star.createStar(STAR_AGE_IN_MILLIONS_MIN,STAR_AGE_IN_MILLIONS_MAX));
				}
			}
		}
	}
}

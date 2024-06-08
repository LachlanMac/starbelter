package com.starbelter.generation;

import com.starbelter.generation.bodies.Star;

/*representation of an empty space in the galaxy that occupies a galactic coordinate and can contain a star*/
public class Sector {
	//class variables
	private int x, y;
	private Star star;
	
	//constructor
	public Sector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//getters & setters
	public void setStar(Star star) {
		this.star = star;
	}

	public Star getStar() {
		return star;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

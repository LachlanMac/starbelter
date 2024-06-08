package com.starbelter.data.gson;

public class Gas extends RawMaterial {

	private float greenhouseEffect;

	public Gas(int id, String name, float weight, float greenhouseEffect) {
		super(id, name, weight);
		this.setGreenhouseEffect(greenhouseEffect);
		
	}

	public float getGreenhouseEffect() {
		return greenhouseEffect;
	}

	public void setGreenhouseEffect(float greenhouseEffect) {
		this.greenhouseEffect = greenhouseEffect;
	}

}

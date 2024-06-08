package com.starbelter.generation.bodies;

import com.starbelter.util.Util;

public class Atmosphere {

	private float o2 = 0f; // oxygen
	private float co2 = 0f; // carbon dioxide
	private float h2o = 0f; // water vapor
	private float so2 = 0f; // sulfuricDioxide
	private float hy = 0f; // hydrogen
	private float he = 0f; // helium
	private float ch4 = 0f; // methane
	private float nh3 = 0f; // ammonia
	private float ni = 0f; // nitrogen

	private float ar = 0f;
	private float xe = 0f;
	private float ne = 0f;

	private float totalGas = 0f;
	private float pressure = 0f;
	private float temperature = 0f;
	private float waterPercentage = 0f;

	float o2Percentage;
	float co2Percentage;

	float h2oPercentage;
	float so2Percentage;
	float hyPercentage;
	float hePercentage;
	float ch4Percentage;
	float nh3Percentage;
	float niPercentage;
	float arPercentage;
	float xePercentage;
	float nePercentage;

	public Atmosphere(float pressure, float waterPercentage, float temperature, float o2,
			float co2, float h2o, float so2, float hy, float he, float ch4, float nh3,
			float ni) {
		this.pressure = pressure;
		this.waterPercentage = waterPercentage;
		this.temperature = temperature;
		this.o2 = o2;
		this.co2 = co2;
		this.h2o = h2o;
		this.so2 = so2;
		this.hy = hy;
		this.he = he;
		this.ch4 = ch4;
		this.nh3 = nh3;
		this.ni = ni;

		this.ne = Math.max(0, Util.getRandom().nextFloat(-1f, 2f));
		this.ar = Math.max(0, Util.getRandom().nextFloat(-1f, 2f));
		this.xe = Math.max(0, Util.getRandom().nextFloat(-1f, 2f));

		totalGas = o2 + co2 + h2o + so2 + hy + he + ch4 + nh3 + ni + ne + xe + ar;
		o2Percentage = (o2 / totalGas) * 100;
		co2Percentage = (co2 / totalGas) * 100;
		h2oPercentage = (h2o / totalGas) * 100;
		so2Percentage = (so2 / totalGas) * 100;
		hyPercentage = (hy / totalGas) * 100;
		hePercentage = (he / totalGas) * 100;
		ch4Percentage = (ch4 / totalGas) * 100;
		nh3Percentage = (nh3 / totalGas) * 100;
		niPercentage = (ni / totalGas) * 100;
		arPercentage = (ar / totalGas) * 100;
		xePercentage = (xe / totalGas) * 100;
		nePercentage = (ne / totalGas) * 100;
	}

	@Override
	public String toString() {
		// Ensure totalGas is not zero to avoid division by zero errors
		if (totalGas == 0) {
			return "Atmosphere is empty or values are not set properly.";
		}

		// Calculate the percentage composition of each gas

		// Format the output string
		return String.format("Atmospheric Composition: \n" + "Oxygen: %.2f%%\n"
				+ "Carbon Dioxide: %.2f%%\n" + "Water Vapor: %.2f%%\n"
				+ "Sulfur Dioxide: %.2f%%\n" + "Hydrogen: %.2f%%\n" + "Helium: %.2f%%\n"
				+ "Methane: %.2f%%\n" + "Ammonia: %.2f%%\n" + "Nitrogen: %.2f%%\n"
				+ "Argon: %.2f%%\n" + "Xenon: %.2f%%\n" + "Neon: %.2f%%\n", o2Percentage,
				co2Percentage, h2oPercentage, so2Percentage, hyPercentage, hePercentage,
				ch4Percentage, nh3Percentage, niPercentage, arPercentage, xePercentage,
				nePercentage);
	}

	public float getO2() {
		return o2;
	}

	public void setO2(float o2) {
		this.o2 = o2;
	}

	public float getCo2() {
		return co2;
	}

	public void setCo2(float co2) {
		this.co2 = co2;
	}

	public float getH2o() {
		return h2o;
	}

	public void setH2o(float h2o) {
		this.h2o = h2o;
	}

	public float getSo2() {
		return so2;
	}

	public void setSo2(float so2) {
		this.so2 = so2;
	}

	public float getHy() {
		return hy;
	}

	public void setHy(float hy) {
		this.hy = hy;
	}

	public float getHe() {
		return he;
	}

	public void setHe(float he) {
		this.he = he;
	}

	public float getCh4() {
		return ch4;
	}

	public void setCh4(float ch4) {
		this.ch4 = ch4;
	}

	public float getNh3() {
		return nh3;
	}

	public void setNh3(float nh3) {
		this.nh3 = nh3;
	}

	public float getNi() {
		return ni;
	}

	public void setNi(float ni) {
		this.ni = ni;
	}

	public float getTotalGas() {
		return totalGas;
	}

	public void setTotalGas(float totalGas) {
		this.totalGas = totalGas;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getWaterPercentage() {
		return waterPercentage;
	}

	public void setWaterPercentage(float waterPercentage) {
		this.waterPercentage = waterPercentage;
	}

	public float getO2Percentage() {
		return o2Percentage;
	}

	public void setO2Percentage(float o2Percentage) {
		this.o2Percentage = o2Percentage;
	}

	public float getCo2Percentage() {
		return co2Percentage;
	}

	public void setCo2Percentage(float co2Percentage) {
		this.co2Percentage = co2Percentage;
	}

	public float getH2oPercentage() {
		return h2oPercentage;
	}

	public void setH2oPercentage(float h2oPercentage) {
		this.h2oPercentage = h2oPercentage;
	}

	public float getSo2Percentage() {
		return so2Percentage;
	}

	public void setSo2Percentage(float so2Percentage) {
		this.so2Percentage = so2Percentage;
	}

	public float getHyPercentage() {
		return hyPercentage;
	}

	public void setHyPercentage(float hyPercentage) {
		this.hyPercentage = hyPercentage;
	}

	public float getHePercentage() {
		return hePercentage;
	}

	public void setHePercentage(float hePercentage) {
		this.hePercentage = hePercentage;
	}

	public float getCh4Percentage() {
		return ch4Percentage;
	}

	public void setCh4Percentage(float ch4Percentage) {
		this.ch4Percentage = ch4Percentage;
	}

	public float getNh3Percentage() {
		return nh3Percentage;
	}

	public void setNh3Percentage(float nh3Percentage) {
		this.nh3Percentage = nh3Percentage;
	}

	public float getNiPercentage() {
		return niPercentage;
	}

	public void setNiPercentage(float niPercentage) {
		this.niPercentage = niPercentage;
	}
}

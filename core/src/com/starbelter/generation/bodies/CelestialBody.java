package com.starbelter.generation.bodies;

import java.util.ArrayList;

import com.starbelter.data.DataLoader;
import com.starbelter.generation.Galaxy;
import com.starbelter.util.Util;

public abstract class CelestialBody {

	protected int size, orbit, daysPerYear;
	protected float tectonicStrength, radiationStrength, magnetosphereStrength,
			temperature, axialTilt, rotationSpeed, gravity, pressure, mass, yearLength,
			waterCoverage, photosynthesisCoverage, surfaceTemperature;
	protected Core core;
	protected Surface surface;
	protected float ageInMillions;

	protected boolean goldilox = false;

	protected Atmosphere atmosphere;

	public static final float YEAR_LENGTH_MULTIPLIER = 4;

	protected enum Core {
		IRON_NICKEL, GAS, SILICATE, ICE, MOLTEN
	}

	protected enum Surface {
		HEAVY_METAL, SOFT_METAL, ICE, SILICATE, GAS
	}

	protected String name;
	protected Star star;

	public CelestialBody(int size, int orbit, Star star, float temperature,
			float ageInMillions) {
		this.size = size;
		this.orbit = orbit;
		this.star = star;
		this.temperature = temperature;
		this.surfaceTemperature = temperature;
		this.ageInMillions = ageInMillions;
		buildBody();
	}

	public void buildBody() {

		this.name = DataLoader.getBodyName();
		int chance = Util.getRandom().nextInt(0, 100);

		if (size > 80) {
			core = Core.GAS;
			surface = Surface.GAS;
		} else if (size <= 80 && size > 65) {
			if (chance > 80)
				core = Core.IRON_NICKEL;
			else if (chance <= 80 && chance > 65)
				core = Core.MOLTEN;
			else
				core = Core.GAS;
		} else if (size <= 65 && size > 35) {
			if (chance > 70)
				core = Core.IRON_NICKEL;
			else if (chance <= 70 && chance > 50)
				core = Core.MOLTEN;
			else if (chance <= 50 && chance > 20)
				core = Core.GAS;
			else {
				if (temperature < 0)
					core = Core.ICE;
				else
					core = Core.SILICATE;
			}
		} else {
			if (chance > 80)
				core = Core.IRON_NICKEL;
			else if (chance <= 80 && chance > 50)
				core = Core.MOLTEN;
			else {
				if (temperature < 0)
					core = Core.ICE;
				else
					core = Core.SILICATE;
			}
		}
		chance = Util.getRandom().nextInt(0, 100);
		switch (core) {
		case GAS:
			surface = Surface.GAS;
			break;
		case ICE:
			if (chance > 80)
				surface = Surface.SILICATE;
			else
				surface = Surface.ICE;
			break;
		case IRON_NICKEL:
			if (chance > 90)
				surface = Surface.HEAVY_METAL;
			else if (chance <= 90 && chance > 85)
				if (temperature < 0)
					surface = Surface.ICE;
				else
					surface = Surface.SOFT_METAL;
			else
				surface = Surface.SILICATE;
			break;
		case MOLTEN:
			if (chance > 85)
				surface = Surface.HEAVY_METAL;
			else if (chance <= 85 && chance > 80)
				surface = Surface.SOFT_METAL;
			else
				surface = Surface.SILICATE;
			break;
		case SILICATE:
			surface = Surface.SILICATE;
			break;
		default:
			break;
		}

		// calc mass
	}

	protected void calculate() {

		float coreFactor = 0.0f;

		float o2 = 0f; // oxygen
		float co2 = 0f; // carbon dioxide
		float h20 = 0f; // water vapor
		float so2 = 0f; // sulfuricDioxide
		float hy = 0f; // hydrogen
		float he = 0f; // helium
		float ch4 = 0f; // methane
		float nh3 = 0f; // ammonia
		float ni = 0f; // nitrogen
		float ar = 0f;
		float xe = 0f;
		float ne = 0f;
		// light gases

		float waterPercentage = 0f;

		ni = Math.max(0, Util.getRandom().nextFloat(-1f, 6f));

		switch (core) {
		case GAS:
			mass = size * 0.4f;
			tectonicStrength = 0f;
			coreFactor = 0.1f;
			break;
		case ICE:
			mass = size * 0.6f;
			tectonicStrength = Math.max(0f, Util.getRandom().nextFloat(-2.0f, 1f));
			coreFactor = 0.1f;
			break;
		case IRON_NICKEL:
			mass = size * 3f;
			tectonicStrength = Math.max(0f, Util.getRandom().nextFloat(-2.0f, 5f));
			coreFactor = 1.0f;
			break;
		case MOLTEN:
			mass = size * 2.2f;
			coreFactor = 1.2f;
			tectonicStrength = Math.max(0f, Util.getRandom().nextFloat(-2.0f, 10f));
			break;
		case SILICATE:
			coreFactor = 0.5f;
			mass = size;
			tectonicStrength = Math.max(0f, Util.getRandom().nextFloat(-1.0f, 3f));
			break;
		default:
			break;
		}
		switch (surface) {
		case GAS:
			hy += Util.getRandom().nextFloat(90, 200);
			he += Util.getRandom().nextFloat(50, 200);
			nh3 += Math.max(0, Util.getRandom().nextFloat(-20f, 20f));
			ch4 = Math.max(0, Util.getRandom().nextFloat(-20f, 60f));
			waterPercentage = 0;
			break;
		case HEAVY_METAL:
			so2 += Math.max(0, Util.getRandom().nextFloat(-1f, 3f));
			waterPercentage = 0.1f;
			break;
		case ICE:
			h20 += Math.max(0, Util.getRandom().nextFloat(-3f, 5f));
			waterPercentage = 0.8f;
			break;
		case SILICATE:
			co2 += Math.max(0, Util.getRandom().nextFloat(-5, 7f));
			so2 += Math.max(0, Util.getRandom().nextFloat(-5, 2f));
			waterPercentage = 0.6f;
			break;
		case SOFT_METAL:
			waterPercentage = 0.1f;
			so2 += Math.max(0, Util.getRandom().nextFloat(-1f, 3f));
			break;
		default:
			break;
		}

		o2 = Math.max(0, o2 + Util.getRandom().nextFloat(-1f, 2f));
		co2 = Math.max(0, co2 + Util.getRandom().nextFloat(-1f, 2f));
		h20 = Math.max(0, h20 + Util.getRandom().nextFloat(-1f, 2f));
		so2 = Math.max(0, so2 + Util.getRandom().nextFloat(-1f, 2f));
		hy = Math.max(0, hy + Util.getRandom().nextFloat(-1f, 2f));
		he = Math.max(0, he + Util.getRandom().nextFloat(-1f, 2f));
		ch4 = Math.max(0, ch4 + Util.getRandom().nextFloat(-1f, 2f));
		nh3 = Math.max(0, nh3 + Util.getRandom().nextFloat(-1f, 2f));
		ni = Math.max(0, ni + Util.getRandom().nextFloat(-1f, 2f));
		ar = Math.max(0, ar + Util.getRandom().nextFloat(-1f, 2f));
		xe = Math.max(0, xe + Util.getRandom().nextFloat(-1f, 2f));
		ne = Math.max(0, ne + Util.getRandom().nextFloat(-1f, 2f));

		// float starRads = star.getRadiation();
		if (this instanceof Planet) {
			float originalRotation = 1.4f;

			originalRotation *= (Math.abs((float) (orbit - Star.MAX_ORBITS))
					/ (float) (Star.MAX_ORBITS));
			originalRotation -= (float) this.ageInMillions / 10000f;
			originalRotation *= ((float) size / ((float) star.getSize() + 10));

			originalRotation = Math.max(0, originalRotation);

			// tidalLockFactor = (star.getSize()) / this.getSize() * (this.ageInMillions /
			// 1000f);
			rotationSpeed = Util.getRandom().nextFloat(0.8f, 1.2f) * originalRotation;
			ArrayList<Moon> moons = ((Planet) this).getMoons();
			for (Moon moon : moons) {
				tectonicStrength += moon.getTectonicInfluence();
			}
		} else if (this instanceof Moon) {
			Moon ref = (Moon) (this);
			float originalRotation = 5.0f;
			originalRotation -= (float) (this.ageInMillions / 5000f);

			originalRotation *= ((float) size / ((float) ref.getPlanet().getSize()));
			rotationSpeed = Util.getRandom().nextFloat(0.7f, 1.4f)
					* Math.max(0, originalRotation);

		}

		// multiply by random mod for the core
		mass *= Util.getRandom().nextFloat(.85f, 1.15f);
		gravity = (float) (300f * mass / Math.pow(size + 50, 2)) / 3 + 0.3f;

		yearLength = (float) (YEAR_LENGTH_MULTIPLIER * Math.sqrt(Math.pow(orbit + 1, 3)))
				+ Util.getRandom().nextInt(20, 40);

		// rotations PER revolution

		switch (core) {
		case GAS:
			hy = Util.getRandom().nextFloat(100, 400);
			he = Util.getRandom().nextFloat(90, 300);
			nh3 = Math.max(0, Util.getRandom().nextFloat(-10.2f, 10.2f));
			break;
		case ICE:
			co2 = Math.max(0, Util.getRandom().nextFloat(-2f, 4f)) * tectonicStrength;
			o2 = Math.max(0, Util.getRandom().nextFloat(-6f, 4f)) * tectonicStrength;
			nh3 = Math.max(0, Util.getRandom().nextFloat(-1.2f, 1.2f)) * tectonicStrength
					/ 10;
			h20 = Math.max(0, Util.getRandom().nextFloat(-2f, 2f)) * tectonicStrength * 3;
			ni = Math.max(0, Util.getRandom().nextFloat(-3f, 6f)) * tectonicStrength;
			break;
		case IRON_NICKEL:
			co2 = Math.max(0, Util.getRandom().nextFloat(-4f, 7f)) * tectonicStrength / 2;
			o2 = Math.max(0, Util.getRandom().nextFloat(-4f, 4f)) * tectonicStrength;
			h20 = Math.max(0, Util.getRandom().nextFloat(-2f, 1.5f)) * tectonicStrength;
			ni += Math.max(0, Util.getRandom().nextFloat(-4f, 8f)) * tectonicStrength;
			break;
		case MOLTEN:
			so2 = Math.max(0, Util.getRandom().nextFloat(-1f, 2f)) * tectonicStrength / 2;
			ni += Math.max(0, Util.getRandom().nextFloat(-4f, 20f)) * tectonicStrength;
			break;
		case SILICATE:
			ni += Math.max(0, Util.getRandom().nextFloat(-6f, 6f)) * tectonicStrength;
			h20 = Math.max(0, Util.getRandom().nextFloat(-3f, 2.5f)) * tectonicStrength;
			break;
		default:
			break;
		}

		axialTilt = Util.getRandom().nextFloat(0f, 50f);

		float sizeFactor = this.size / 10.0f; // Normalize size for calculation
		float rotationFactor = this.rotationSpeed / 10.0f; // Normalize rotation speed

		if (rotationSpeed == 0f) {
			daysPerYear = 1;
		} else {
			daysPerYear = (int) (yearLength / rotationSpeed) + 1;
		}

		this.magnetosphereStrength = (coreFactor * 2.3f) + (sizeFactor * 0.3f)
				+ (rotationFactor * 1.4f) + Util.getRandom().nextInt(-2, 2);
		if (magnetosphereStrength < 0)
			magnetosphereStrength = 0;
		else if (magnetosphereStrength > 50) {
			magnetosphereStrength = 50;
		}

		double escapeFactor = Math.exp(gravity - 1); // Simple exponential decay model for
														// atmospheric retention

		he *= (gravity > 0.9) ? Math.min(1, escapeFactor)
				: Math.max(0, escapeFactor - 0.5);
		hy *= (gravity > 0.9) ? Math.min(1, escapeFactor)
				: Math.max(0, escapeFactor - 0.5);
		o2 *= (gravity < 0.7) ? Math.max(0, escapeFactor - 0.3)
				: Math.min(1, escapeFactor + 0.2);
		ni *= (gravity < 0.6) ? Math.max(0, escapeFactor - 0.2)
				: Math.min(1, escapeFactor + 0.3);
		h20 *= Math.min(1, escapeFactor + 0.5); // Water vapor's retention can be less
												// affected by lower gravity

		ch4 *= Math.min(1, escapeFactor + 0.5); // Methane retention, similarly managed
		nh3 *= Math.min(1, escapeFactor + 0.5); // Ammonia, affected similar to methane

		float DECAY_RATE = 0.002f;
		float decayFactor = 50 - magnetosphereStrength;

		he *= 1.0 - decayFactor * DECAY_RATE; // Adjust the 0.01 to control the rate of
												// loss
		hy *= 1.0 - decayFactor * DECAY_RATE;
		o2 *= 1.0 - decayFactor * DECAY_RATE;
		ni *= 1.0 - decayFactor * DECAY_RATE;
		h20 *= 1.0 - decayFactor * DECAY_RATE;
		ch4 *= 1.0 - decayFactor * DECAY_RATE;
		nh3 *= 1.0 - decayFactor * DECAY_RATE;
		so2 *= 1.0 - decayFactor * DECAY_RATE;
		co2 *= 1.0 - decayFactor * DECAY_RATE;

		float k = 10f;

		float molarMass = (float) (he * 4.002602 + hy * 2.016 + o2 * 32.00 + ni * 28.02
				+ h20 * 18.01528 + co2 * 44.01 + ch4 * 16.04 + nh3 * 17.031
				+ so2 * 64.066); // weights in g/mol
		float totalGas = he + hy + o2 + ni + h20 + co2 + ch4 + nh3 + so2;
		float meanMolarMass = molarMass / totalGas;

		float atmosphericDensity = meanMolarMass * gravity;
		if (atmosphericDensity <= 0 || Float.isNaN(atmosphericDensity)) {
			atmosphericDensity = 0.01f;
		}

		float greenhouseEffect = (float) ((co2 * 1) + (ch4 * 12) + (h20 * 0.5)
				+ (nh3 * 0.1) + (so2 * 10f)) * atmosphericDensity;
		float temperatureIncrease = (float) (k
				* Math.log(1 + Math.pow(greenhouseEffect, 1.5)));

		float atmosphericPressure = atmosphericDensity / 20;

		// reset
		this.temperature = this.surfaceTemperature;

		this.temperature += temperatureIncrease;
		atmosphericPressure += (temperature - 50) / 30;

		if (atmosphericPressure < 0) {
			atmosphericPressure = 0;
		}


		// REACTION TIME!

		int methaneAmount = (int) ch4 - Util.getRandom().nextInt(2);
		int ammoniaAmount = (int) nh3 - Util.getRandom().nextInt(2);
		;

		for (int x = 0; x < methaneAmount; x++) {
			if (o2 > 2) {
				ch4 = Math.max(0, ch4 - 1);
				o2 = Math.max(0, o2 - 2);
				h20 += 2;
				co2 += 2;
			}
		}

		for (int y = 0; y < ammoniaAmount; y++) {
			if (o2 > 3) {
				ch4 = Math.max(0, ch4 - 4);
				o2 = Math.max(0, o2 - 3);
				h20 += 6;
			}
		}

		float waterAcidity = 0f;
		this.pressure = atmosphericPressure;
		if (temperature < 95 && temperature > 0 && core != Core.GAS
				&& surface == Surface.SILICATE) {
			if (Util.getRandomPercentage() < 45 + (ageInMillions / 100)) {
				Galaxy.GOLDILOX_COUNT++;
				goldilox = true;
				float removedWaterVapor = h20 - (h20 / 9);
				h20 -= removedWaterVapor;
				float water = Math.min(30, h20);

				float coveragePercentage = (Util.getRandomPercentage() + water)
						* waterPercentage;
				waterCoverage = coveragePercentage;

				int byo = (int) Math.max(1, ageInMillions / 1000);

				float possiblePhotosynthesisCoverage = Math.max(5,
						(Math.min(100, 100 * (ageInMillions / 1000))
								- coveragePercentage));
				photosynthesisCoverage = Util.getRandom().nextFloat(0,
						possiblePhotosynthesisCoverage);

				for (int i = 0; i < byo; i++) {
					if (co2 > 0) {
						float removedCO2 = co2 / 1.5f;
						co2 = Math.max(0, co2 - (removedCO2 * 2));
						o2 += removedCO2 / 10 * photosynthesisCoverage;
						ni += removedCO2 * 2;
					}
					if (so2 > 2) {
						so2 = so2 * 0.2f * waterCoverage;
					}
				}

				for (int x = 0; x < methaneAmount; x++) {
					if (o2 > 2) {
						ch4 = Math.max(0, ch4 - 1);
						o2 = Math.max(0, o2 - 2);
						h20 += 2;
						co2 += 2;
					}
				}

				for (int y = 0; y < ammoniaAmount; y++) {
					if (o2 > 3) {
						ch4 = Math.max(0, ch4 - 4);
						o2 = Math.max(0, o2 - 3);
						h20 += 6;
					}
				}

				molarMass = (float) (he * 4.002602 + hy * 2.016 + o2 * 32.00 + ni * 28.02
						+ h20 * 18.01528 + co2 * 44.01 + ch4 * 16.04 + nh3 * 17.031
						+ so2 * 64.066); // weights in g/mol
				totalGas = he + hy + o2 + ni + h20 + co2 + ch4 + nh3 + so2;
				meanMolarMass = molarMass / totalGas;

				atmosphericDensity = meanMolarMass * gravity;
				if (atmosphericDensity <= 0 || Float.isNaN(atmosphericDensity)) {
					atmosphericDensity = 0.01f;
				}

				greenhouseEffect = (float) ((co2 * 1) + (ch4 * 12) + (h20 * 0.5)
						+ (nh3 * 0.1) + (so2 * 10f)) * atmosphericDensity;
				temperatureIncrease = (float) (k
						* Math.log(1 + Math.pow(greenhouseEffect, 1.5)));

				atmosphericPressure = atmosphericDensity / 20;

				this.temperature += temperatureIncrease / 40;
				atmosphericPressure += (temperature - 50) / 30;

				if (atmosphericPressure < 0) {
					atmosphericPressure = 0;
				}
				
				//add water vapor back in
				h20 += (temperature / 10) * (waterCoverage / 50);
				
				this.pressure = atmosphericPressure;
			}
		}

		// bigger gravity = more heavy gases
		// less magnetosphere, more gases stripped away

		atmosphere = new Atmosphere(pressure, waterCoverage, temperature, o2, co2, h20,
				so2, hy, he, ch4, nh3, ni);


	}

	public static boolean isHabitable(CelestialBody b) {

		if (b.getWaterCoverage() > 5 && b.getPhotosynthesisCoverage() > 10
				&& b.getPressure() < 3 && b.getPressure() > 0.5
				&& b.getRotationSpeed() > 0.3 && b.getAtmosphere().o2Percentage > 10
				&& b.getAtmosphere().nh3Percentage < 5
				&& b.getAtmosphere().ch4Percentage < 7
				&& b.getAtmosphere().o2Percentage < 30
				&& b.getAtmosphere().co2Percentage < 4
				&& b.getAtmosphere().so2Percentage < 5) {

			return true;
		} else {
			return false;
		}

	}

	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

	public void calculateAtmosphere() {

	}

	public int getSize() {
		return size;
	}

	public int getOrbit() {
		return orbit;
	}

	public String getInfoString() {
		return "Name:" + name + "[" + core.name() + "/" + surface.name() + "]" + " temp:"
				+ temperature + " pressure:[" + pressure + "] gravity:[" + gravity
				+ "] water coverage:[" + waterCoverage + "] plant coverage: ["
				+ photosynthesisCoverage + "] rotation Speed:[" + rotationSpeed + "] "
				+ daysPerYear + "";
	}

	public String getMegaInfo() {
		return "__" + name + "__  size:" + size + " orbit:" + orbit + " temperature:"
				+ temperature + " mass:" + mass + " tectonics:" + tectonicStrength
				+ "Gravity:" + gravity + " Core:" + core.name() + " Surface:"
				+ surface.name() + " AGE==" + (ageInMillions / 1000) + " Magnetosphere:"
				+ magnetosphereStrength + " YEAR LENGTH: " + yearLength
				+ " Rotation Rate:" + rotationSpeed + " DPY:" + daysPerYear + "\n";
	}

	@Override
	public String toString() {
		/*
		 * return "name:" + name + "   size:" + size + "   orbit:" + orbit + "   rot:" +
		 * rotationSpeed + "   yearlen:" + yearLength + "   age:" + (ageInMillions /
		 * 1000) + "  dpy:" + daysPerYear + " MAGNET: " + magnetosphereStrength + "  " +
		 * gravity;
		 */

		return getInfoString();

	}

	public int getDaysPerYear() {
		return daysPerYear;
	}

	public void setDaysPerYear(int daysPerYear) {
		this.daysPerYear = daysPerYear;
	}

	public float getTectonicStrength() {
		return tectonicStrength;
	}

	public void setTectonicStrength(float tectonicStrength) {
		this.tectonicStrength = tectonicStrength;
	}

	public float getRadiationStrength() {
		return radiationStrength;
	}

	public void setRadiationStrength(float radiationStrength) {
		this.radiationStrength = radiationStrength;
	}

	public float getMagnetosphereStrength() {
		return magnetosphereStrength;
	}

	public void setMagnetosphereStrength(float magnetosphereStrength) {
		this.magnetosphereStrength = magnetosphereStrength;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getAxialTilt() {
		return axialTilt;
	}

	public void setAxialTilt(float axialTilt) {
		this.axialTilt = axialTilt;
	}

	public float getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(float rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getYearLength() {
		return yearLength;
	}

	public void setYearLength(float yearLength) {
		this.yearLength = yearLength;
	}

	public float getWaterCoverage() {
		return waterCoverage;
	}

	public void setWaterCoverage(float waterCoverage) {
		this.waterCoverage = waterCoverage;
	}

	public float getPhotosynthesisCoverage() {
		return photosynthesisCoverage;
	}

	public void setPhotosynthesisCoverage(float photosynthesisCoverage) {
		this.photosynthesisCoverage = photosynthesisCoverage;
	}

	public Core getCore() {
		return core;
	}

	public void setCore(Core core) {
		this.core = core;
	}

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public float getAgeInMillions() {
		return ageInMillions;
	}

	public void setAgeInMillions(float ageInMillions) {
		this.ageInMillions = ageInMillions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public static float getYearLengthMultiplier() {
		return YEAR_LENGTH_MULTIPLIER;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setOrbit(int orbit) {
		this.orbit = orbit;
	}

}

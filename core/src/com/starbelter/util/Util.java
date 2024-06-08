package com.starbelter.util;

import java.util.Random;

public class Util {

	public static Random rn = new Random();
	
	public static Random getRandom() {
		return rn;
	}
	
	public static int getRandomPercentage() {
		return rn.nextInt(0,100);
	}
	
	
}

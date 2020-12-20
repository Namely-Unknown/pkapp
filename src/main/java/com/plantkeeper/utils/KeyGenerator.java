package com.plantkeeper.utils;

import java.util.Random;

public class KeyGenerator {

	public static String generateKey() {
		 
		int[][] asciiArray = {{97, 122}, {65, 90}, {49, 57}};

	    int targetStringLength = 15;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	    	int j = random.nextInt(2 - 0 + 1);
	        int randomLimitedInt = asciiArray[j][0] + (int) 
	          (random.nextFloat() * (asciiArray[j][1] - asciiArray[j][0] + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();

	    return generatedString;
	}
}

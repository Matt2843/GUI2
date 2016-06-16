package com.utility;

import java.util.Random;

public class Utilities {
	
	
	public static String generateID(int length){
		String keys = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String result = "";
		Random rand = new Random();
		for (int i = 0; i<length;i++){
			result+=keys.charAt(rand.nextInt(keys.length()));
		}
		return result;
	}
}

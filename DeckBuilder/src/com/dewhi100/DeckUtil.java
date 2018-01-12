package com.dewhi100;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DeckUtil {

	private DeckUtil() {}
	
	//Peels the integer amount of colorless mana out of a CMC string.
	public static int cost(String input) {
		String number = input.substring(1, 1);
		
		return Integer.parseInt(number);
		
	}
	
	//Gets the # of matches of a string representing a color in a CMC string
	public static int colorCost(String input, String color) {
		return StringUtils.countMatches(input, color);
	}
	
	public static Map<String, Integer> colorCost(String input){
		Map<String, Integer> output = new HashMap<String, Integer>();
		
		
		
		return output;
	}
	
}

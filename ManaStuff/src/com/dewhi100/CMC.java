package com.dewhi100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CMC {

	private Map<String, Integer> cmcMap;
	
	public CMC() {
		cmcMap = new HashMap<String, Integer>();
	}

	public void add(String input) {

		System.out.println("CMC.add- input is: " + input);

		
		String cmcString = input;
		cmcString.replace("}", "");//remove }
		String[] manaArray = cmcString.split("{");//{ is delimiter
		List<String> manaList = Arrays.asList(manaArray);
		
		System.out.println("CMC.add- manaList is: " + manaList);
		
		for(String s: manaList) {
						
			//is it a number, meaning it's colorless?
			if(!Pattern.matches(".*\\D.*", s)) {
				System.out.println("CMC.add- " + s + " identified as a number.");
				cmcMap.put("#", Integer.parseInt(s));
				continue;
			}
			
			System.out.println("CMC.add- testing cmcMap for " + s);
			if(cmcMap.containsKey(s)) {
				//there is already mana of that color. add one more.
				cmcMap.put(s, cmcMap.get(s) + 1);
			}else {
				//there wasn't any mana of that color. there is now.
				cmcMap.put(s, new Integer(1));
			}
			System.out.println("CMC.add- value for " + s + " is " + cmcMap.get(s));
		}
	}
	
	int total() {
		int total = 0;
		
		for(Integer i: cmcMap.values()) {
			total += i;
		}
		
		return total;
	}	
}

package mana;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CMC {

	private Map<String, Integer> cmcMap;
	
	public CMC() {
		cmcMap = new HashMap<String, Integer>();
	}

	public void add(String input) {
		String cmcString = input;
		cmcString.replace("}", "");//remove }
		String[] manaArray = cmcString.split("{");//{ is delimiter
		List<String> manaList = Arrays.asList(manaArray);
		
		for(String s: manaList) {
			if(cmcMap.containsKey(s)) {
				//there is already mana of that color. add one more.
				cmcMap.put(s, cmcMap.get(s) + 1);
			}else {
				//there wasn't any mana of that color. there is now.
				cmcMap.put(s, new Integer(1));
			}
		}
	}
	
}

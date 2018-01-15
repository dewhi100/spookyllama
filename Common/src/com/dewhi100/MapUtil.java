package com.dewhi100;

import java.util.Map;

public class MapUtil {

	private MapUtil() {
		
	}
	
	public static void add(Map<String, Integer> map, String s) {
		
		Integer i = map.get(s);
		
		if(i != null) {
			map.put(s,  i + 1);
		}else {
			map.put(s,  1);
		}
		
	}
	
}

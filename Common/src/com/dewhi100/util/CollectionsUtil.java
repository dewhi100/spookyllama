package com.dewhi100.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dewhi100.math.BinomialCoefficient;

public class CollectionsUtil {

	private CollectionsUtil() {
		
	}
	
	public static void add(Map<String, Integer> map, String s) {
		
		Integer i = map.get(s);
		
		if(i != null) {
			map.put(s,  i + 1);
		}else {
			map.put(s,  1);
		}
		
	}
	
	public static List<Integer> deepCopyIntegers(List<Integer> list) {
		List<Integer> output = new ArrayList<Integer>();

		for (Integer i : list) {
			output.add(new Integer(i));
		}

		return output;
	}

	public static List<BinomialCoefficient> deepCopyBC(List<BinomialCoefficient> input) {
		
		List<BinomialCoefficient> output = new ArrayList<BinomialCoefficient>();
		
		for(BinomialCoefficient bc:input) {
			output.add(bc.clone());
		}

		return output;
	}
	
	public static List<BinomialCoefficient> fatCopyBC(List<BinomialCoefficient> input) {
		
		List<BinomialCoefficient> output = new ArrayList<BinomialCoefficient>();
		
		for(BinomialCoefficient bc:input) {
			output.add(bc.selectAll());
		}

		return output;
	}
	
	
}

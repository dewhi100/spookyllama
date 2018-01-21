package com.dewhi100.math;

import java.util.ArrayList;
import java.util.List;

public class Math {

	public static List<List<Integer>> getPossibilities(List<Integer> list) {
		ArrayList<List<Integer>> output = new ArrayList<List<Integer>>();

		if(!isGreaterThanZeroes(list)) {
			output.add(list);
			return output;
		}
		
		List<Integer> smallerList = deepCopy(list);
		
		while(!isOnes(smallerList)) {
			output.add(smallerList);
			smallerList = decrement(smallerList, list);			
		}
		
		output.add(smallerList);
		
		return output;

	}

	private static List<Integer> decrement(List<Integer> list, List<Integer> maximums){
		
		List<Integer> output = deepCopy(list);
		
		int index = list.size() - 1;
		
		while(index >= 0) {
			Integer i = output.get(index);
			if(i > 1) {
				output.set(index, new Integer(i - 1));
				break;
			}else {
				output.set(index, maximums.get(index).intValue());
				index--;
			}
		}
		return output;
	}
	
	private static boolean isGreaterThanZeroes(List<Integer> list) {
		for(Integer i:list) {
			if(i == null) {
				return false;
			}
			if(i.intValue() <= 0) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isOnes(List<Integer> list) {
		for(Integer i:list) {
			if(i == null) {
				return false;
			}
			if(i.intValue() > 1) {
				return false;
			}
		}
		return true;
	}
	
/*	private static int getLargestPlace(List<Integer> list) {
		int output = 0;
		int place = 0;
		int highest = 0;

		while (place < list.size()) {

			int i = list.get(place);
			if (i >= highest) {
				output = place;
				highest = i;
			}

			place++;
		}
		return output;
	}*/

	private static List<Integer> deepCopy(List<Integer> list) {
		List<Integer> output = new ArrayList<Integer>();

		for (Integer i : list) {
			output.add(new Integer(i));
		}

		return output;
	}

}

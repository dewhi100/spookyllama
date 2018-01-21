package com.dewhi100;

import java.util.ArrayList;
import java.util.List;

import com.dewhi100.math.Math;

public class MainClass {

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
//	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//	            public void run() {
//	                new Display().createAndShowGUI();
//	            }
//	        });
	    	
	    		List<Integer> list = new ArrayList<Integer>();
	    		list.add(new Integer(2));
	    		list.add(new Integer(3));
	    		list.add(new Integer(2));
	    	
	    		List<List<Integer>> coolList = Math.getPossibilities(list);
	    		coolList = coolList;
	    		
	    }
}

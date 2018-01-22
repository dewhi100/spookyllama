package com.dewhi100;

import java.util.Arrays;

import com.dewhi100.math.BinomialCoefficient;
import com.dewhi100.math.HypergeometricMath;

public class MainClass {

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
//	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//	            public void run() {
//	                new Display().createAndShowGUI();
//	            }
//	        });
	    	
	    	BinomialCoefficient bc1 = new BinomialCoefficient(4, 1); //successes
	    	BinomialCoefficient bc3 = new BinomialCoefficient(4, 1); //another successes
	    	BinomialCoefficient bc2= new BinomialCoefficient(60, 8); //population	    	
	    	
	    double d = HypergeometricMath.multivariateCumulativeProbability(bc2, Arrays.asList(bc1, bc3));
	    	d = 0;
	    }
}

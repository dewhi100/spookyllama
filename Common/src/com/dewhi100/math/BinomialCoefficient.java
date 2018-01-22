package com.dewhi100.math;

import org.apache.commons.math3.util.CombinatoricsUtils;

public class BinomialCoefficient {

	private int t;
	private int s;

	public BinomialCoefficient(int total, int sample) {
		t = total;
		s = sample;
	}

	public double value() {
		return CombinatoricsUtils.binomialCoefficientDouble(t, s);
	}

	public BinomialCoefficient oneLess() {
		if (s > 0) {
			return new BinomialCoefficient(t, s - 1);
		}
		return null;
	}

	public BinomialCoefficient clone() {
		return new BinomialCoefficient(t, s);
	}

	public BinomialCoefficient selectAll() {
		return new BinomialCoefficient(t, t);
	}	
	
	public int getT() {
		return t;
	}

	public int getS() {
		return s;
	}
}

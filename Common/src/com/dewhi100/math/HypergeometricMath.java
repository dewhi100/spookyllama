package com.dewhi100.math;

import java.util.List;

import org.apache.commons.math3.distribution.HypergeometricDistribution;

import com.dewhi100.util.CollectionsUtil;

public class HypergeometricMath {

	private HypergeometricMath() {
	}

	public static double nonVariateCumulativeProbability(BinomialCoefficient population,
			BinomialCoefficient successes) {
		return new HypergeometricDistribution(population.getT(), successes.getT(), population.getS())
				.cumulativeProbability(successes.getS());
	}

	public static double multivariateProbability(BinomialCoefficient population, List<BinomialCoefficient> successes) {
		BinomialCoefficient remainder = remainder(population, successes);
		
		double output = 1;
		
		if (remainder.getS() < 0) {
			System.out.println("Warning: You want more successes than can fit in the sample size.");
			return 0;
		}
		if (remainder.getT() < 0) {
			System.out.println("Warning: There are more successes than can fit in the population.");
			return 0;
		}

		for(BinomialCoefficient bc:successes) {
			output *= bc.value();
		}
		
		output *= remainder.value();
		output /= population.value();
		
		return output;
	}

	public static double multivariateCumulativeProbability(BinomialCoefficient population, List<BinomialCoefficient> successes) {
		double output = 0;
		List<BinomialCoefficient> smaller = CollectionsUtil.fatCopyBC(successes);
		
		output += multivariateProbability(population, smaller);

		while(!isMinimum(smaller, successes)) {
			oneLess(smaller, successes);
			output += multivariateProbability(population, smaller);
		}
		
		return output;
	}
	
	private static void oneLess(List<BinomialCoefficient> toReduce, List<BinomialCoefficient> minimums) {
		int index = toReduce.size() - 1;
		
		while(index >= 0) {
			BinomialCoefficient bc = toReduce.get(index);
			int s = bc.getS();
			if(s > minimums.get(index).getS()) {
				toReduce.set(index, bc.oneLess());
				break;
			}else {
				toReduce.set(index, bc.selectAll());
				index--;
			}
		}
	}
	
	private static boolean isMinimum(List<BinomialCoefficient> list, List<BinomialCoefficient> minimum) {
		int index = list.size() -1;

		while(index >= 0) {
			if(list.get(index).getS() > minimum.get(index).getS()) {
				return false;
			}
			index--;
		}
		
		return true;
	}
	
	private static BinomialCoefficient remainder(BinomialCoefficient population, List<BinomialCoefficient> successes) {
		int requestedSampleSize = 0;
		int requestedPopulationSize = 0;

		for (BinomialCoefficient bc : successes) {
			requestedSampleSize += bc.getS();
			requestedPopulationSize += bc.getT();
		}
		return new BinomialCoefficient(population.getT() - requestedPopulationSize, population.getS() - requestedSampleSize);
	}

}

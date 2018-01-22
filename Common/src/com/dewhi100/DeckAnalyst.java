package com.dewhi100;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dewhi100.math.BinomialCoefficient;
import com.dewhi100.math.HypergeometricMath;

import io.magicthegathering.javasdk.resource.Card;

public class DeckAnalyst {

	private DeckAnalyst() {}
	
	public static ManaTally calculateDevotion(Deck deck) {
		
		ManaTally output = null;
		
		for(Card c:deck.getDeck()) {
			if(output == null) {
				output = ManaTally.asManaCost(c);
			}else {
				output.add(ManaTally.asManaCost(c));
			}
		}
		
		return output;
	}
	
	public static ManaTally calculateManaSourceCards(Deck deck) {
		ManaTally output = null;
		
		for(Card c:deck.getDeck()) {
			ManaTally mt = ManaTally.asManaSource(c);
//			if(mt.getTotal() <= 0) {
//				continue;
//			}
			if(output == null) {
				output = mt;
			}else {
				output.add(mt);
			}
		}

		return output;
	}
	
	public static ManaTally calculateFetchLands(Deck deck) {
		ManaTally output = null;
		
		for(Card c:deck.getDeck()) {
			ManaTally mt = ManaTally.asFetchLand(c);
			if(mt.getTotal() <= 0) {
				continue;
			}
			if(output == null) {
				output = mt;
			}else {
				output.add(mt);
			}
		}
		
		return output;
	}
	
	public static ManaTally calculateManaLands(Deck deck) {
		ManaTally output = null;
		
		for(Card c:deck.getDeck()) {
			ManaTally mt = ManaTally.asManaLand(c);
			if(mt.getTotal() <= 0) {
				continue;
			}
			if(output == null) {
				output = mt;
			}else {
				output.add(mt);
			}
		}
		
		return output;

	}
	
	public static Double oddsOfDrawing(Map<Card, Integer> wishes, int turn, Deck deck){
		List<BinomialCoefficient> wishCoefficients = new ArrayList<BinomialCoefficient>();
		
		BinomialCoefficient d = new BinomialCoefficient(deck.size(), turn);

		for(Entry<Card, Integer> e:wishes.entrySet()) {
			//Number in deck, number you want.
			BinomialCoefficient w = new BinomialCoefficient(deck.getQuantity(e.getKey()), e.getValue());
			wishCoefficients.add(w);
		}
		
		return HypergeometricMath.multivariateCumulativeProbability(d, wishCoefficients);
	}
/*	public static List<Map<String, Double>> calculateLandDrawChances(Deck deck, int maxDraws) {
		List<Map<String, Double>> output = new ArrayList<Map<String, Double>>();
		ManaTally baseTally = calculateManaLands(deck);
		int draws = 1;
		
		while(draws <= maxDraws) {
			Map<String, Double> chance = new HashMap<String, Double>();
			Double white = baseTally.getWhite();
			Double blue = baseTally.getBlue();
			Double black = baseTally.getBlack();
			Double red = baseTally.getRed();
			Double green = baseTally.getGreen();
			Double colorless = baseTally.getColorless();
			if(white > 0) {
				BinomialCoefficient land = new BinomialCoefficient(white.intValue(), 1);
				BinomialCoefficient total = new BinomialCoefficient(deck.size(), draws);
				chance.put("White: ", HypergeometricMath.nonVariateCumulativeProbability(land, total));
			}
			if(blue > 0) {
				BinomialCoefficient land = new BinomialCoefficient(white.intValue(), 1);
				BinomialCoefficient total = new BinomialCoefficient(deck.size(), draws);
				chance.put("Blue: ", HypergeometricMath.nonVariateCumulativeProbability(land, total));
			}
			if(black > 0) {
				BinomialCoefficient land = new BinomialCoefficient(white.intValue(), 1);
				BinomialCoefficient total = new BinomialCoefficient(deck.size(), draws);
				chance.put("Blue: ", HypergeometricMath.nonVariateCumulativeProbability(land, total));
			}
			if(red > 0) {
				BinomialCoefficient land = new BinomialCoefficient(white.intValue(), 1);
				BinomialCoefficient total = new BinomialCoefficient(deck.size(), draws);
				chance.put("Red: ", HypergeometricMath.nonVariateCumulativeProbability(land, total));
			}
			if(green > 0) {
				BinomialCoefficient land = new BinomialCoefficient(white.intValue(), 1);
				BinomialCoefficient total = new BinomialCoefficient(deck.size(), draws);
				chance.put("White: ", HypergeometricMath.nonVariateCumulativeProbability(land, total));
			}
			if(colorless > 0) {
				BinomialCoefficient land = new BinomialCoefficient(white.intValue(), 1);
				BinomialCoefficient total = new BinomialCoefficient(deck.size(), draws);
				chance.put("White: ", HypergeometricMath.nonVariateCumulativeProbability(land, total));
			}
			output.add(draws, chance);
			draws++;
		}
		
		
		return output;
	}
*/	
}

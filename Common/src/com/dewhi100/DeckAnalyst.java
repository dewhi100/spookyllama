package com.dewhi100;

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
	
}

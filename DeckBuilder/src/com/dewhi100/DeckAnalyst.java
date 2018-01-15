package com.dewhi100;

import io.magicthegathering.javasdk.resource.Card;

public class DeckAnalyst {

	private DeckAnalyst() {}
	
	public static ManaTally calculateDevotion(Deck deck) {
		
		ManaTally output = null;
		
		for(Card c:deck.getDeck()) {
			if(output == null) {
				output = new ManaTally(c);
			}else {
				output.add(new ManaTally(c));
			}
		}
		
		return output;
	}
	
	public static ManaTally calculateManaSourceCards(Deck deck) {
		ManaTally output = null;
		
		for(Card c:deck.getDeck()) {
			ManaSource ms = new ManaSource(c);
			if(!ms.isSource()) {
				continue;
			}
			if(output == null) {
				output = new ManaTally(ms);
			}else {
				output.add(ms);
			}
		}

		return output;
	}
	
	public static ManaTally calculateFetchLands(Deck deck) {
		ManaTally output = null;
				
		return output;
	}
	
}

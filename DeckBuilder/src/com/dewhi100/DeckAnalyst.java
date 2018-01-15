package com.dewhi100;

import io.magicthegathering.javasdk.resource.Card;

public class DeckAnalyst {

	public static ManaCost calculateDevotion(Deck deck) {
		
		ManaCost output = null;
		
		for(Card c:deck.getDeck()) {
			if(output == null) {
				output = new ManaCost(c);
			}else {
				output.add(new ManaCost(c));
			}
		}
		
		return output;
	}
	
	public static ManaCost calculateManaSourceCards(Deck deck) {
		ManaCost output = null;
		
		for(Card c:deck.getDeck()) {
			ManaSource ms = new ManaSource(c);
			if(!ms.isSource()) {
				continue;
			}
			if(output == null) {
				output = new ManaCost(ms);
			}else {
				output.add(ms);
			}
		}

		return output;
	}
}

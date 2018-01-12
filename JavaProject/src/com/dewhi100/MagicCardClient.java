package com.dewhi100;
import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.resource.Card;

public class MagicCardClient {

	private static Card currentCard;
	
	//Returns a Card if there is only one card matching the name. Otherwise returns null. Cest la vie!
	static void getCardByName(String name) {
		
		Card output = null;
		
		List<String> filters = new ArrayList<String>();
		filters.add("name="+name);
		
		List<Card> cardList = CardAPI.getAllCards(filters);
		
		if(cardList.size() == 1) {
			output = cardList.get(0);
		}
		currentCard = output;
	}
	
	//returns the CMC of the stored card
	static String getCMC() {
		if(currentCard != null) {
			return currentCard.getManaCost();
		}
		return null;
	}
	
	//returns the desc of the stored card
	static String getText() {
		if(currentCard != null) {
			return currentCard.getText();
		}
		return null;
	}
}

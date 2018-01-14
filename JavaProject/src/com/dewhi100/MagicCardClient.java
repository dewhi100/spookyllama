package com.dewhi100;
import java.util.ArrayList;
import java.util.List;

import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.resource.Card;

public class MagicCardClient {

	private static Card currentCard;
	
	//Returns a Card if there is only one card matching the name. Otherwise returns null. Cest la vie!
	static Card getCardByName(String name) {
				
		List<String> filters = new ArrayList<String>();
		filters.add("name="+name);
		
		List<Card> cardList = CardAPI.getAllCards(filters);
		
		if(cardList.isEmpty()) {
			System.out.println("No cards found");
		}
		
		int size = cardList.size();
		
		if(size >= 1) {
			if(size > 1) {
				System.out.println("Multiple cards retrieved.");
/*				for(Card c:cardList) {
					System.out.println("ID: " + c.getId());
					System.out.println("Name: " + c.getName());
					System.out.println("Text: " + c.getText());
				}*/
			}
			currentCard = cardList.get(size -1);
		}
			
		return currentCard;
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

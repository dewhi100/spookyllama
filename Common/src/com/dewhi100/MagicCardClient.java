package com.dewhi100;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.resource.Card;

public class MagicCardClient {

	private static Map<String, Card> cardCache = new HashMap<String, Card>();
	
	private static Card currentCard;
	
	//Looks in cache for cards, then asks the remote server.
	//Uses the first card returned if multiple come back.
	static Card getCardByName(String name) {
		
		if(cardCache.containsKey(name)) {
			currentCard = cardCache.get(name);
			return currentCard;
		}
		
		List<String> filters = new ArrayList<String>();
		filters.add("name="+name);
		
		List<Card> cardList = CardAPI.getAllCards(filters);
				
		int size = cardList.size();
		
		if(size >= 1) {
			if(size > 1) {
				System.out.println("Multiple cards retrieved.");
				currentCard = null;
				for(Card c:cardList) {
					if(c.getName().equalsIgnoreCase(name)) {
						System.out.println("Exact match found.");
						currentCard = c;
					}
				}
				if(currentCard == null) {
					System.out.println("No exact match found.");
					currentCard = cardList.get(cardList.size() - 1); 
				}
			}else {
				currentCard = cardList.get(cardList.size() - 1); 				
			}
		}else {
			System.out.println("No cards found");
			return null;
		}
		
		String cardName = currentCard.getName();
		//if they didnt search with the exact card name, it will also be stored by their search term.
		if(cardName != name) {
			cardCache.put(name, currentCard);			
		}
		cardCache.put(cardName, currentCard);						
		
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

package com.dewhi100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.resource.Card;

public class MagicCardClient {

	private static Map<String, Card> cardCache = new HashMap<String, Card>();

	// Looks in cache for cards, then asks the remote server.
	// Uses the first card returned if multiple come back.
	static private Card searchCardByName(String name) {

		if (cardCache.containsKey(name)) {
			return cardCache.get(name);
		}

		List<String> filters = new ArrayList<String>();
		filters.add("name=" + name);

		List<Card> cardList = CardAPI.getAllCards(filters);
		
		int size = cardList.size();

		if (size >= 1) {
			if (size > 1) {
				System.out.println("Multiple cards retrieved.");
				for (Card c : cardList) {			
					if (c.getName().equalsIgnoreCase(name)) {
						System.out.println("Exact match found.");
						return c;
					}
				}
				System.out.println("No exact match found.");
				return cardList.get(cardList.size() - 1);				
			}else {
				System.out.println("One match found.");
				return cardList.get(cardList.size() - 1);				
			}
		}
		System.out.println("No cards found");
		return null;
	}

	static Card getCardByName(String name) {
		Card c = searchCardByName(name);
		cacheCard(c, name);
		return c;
	}
	
	private static void cacheCard(Card c, String name) {
		if(c == null) {
			return;
		}
		String cardName = c.getName();
		if (cardName != name) {
			cardCache.put(name, c);
		}
		cardCache.put(cardName, c);
	}

	static Card getCardByMate(Card c) {
		if(c == null) {
			return null;
		}
		String cardNumber = c.getNumber();
		if(cardNumber == null) {
			return null;
		}

		if(!CardUtil.checkIsDualCard(c)) {
			return c;
		}
		
		String mateNumber = cardNumber.substring(0, cardNumber.length() - 1);

		if (Pattern.matches(".*a.*", cardNumber)) {
			mateNumber += "b";
		} else if (Pattern.matches(".*b.*", cardNumber)) {
			mateNumber += "a";
		} else {
			return c;
		}

		for (Card cacheCard : cardCache.values()) {
			if (cacheCard.getNumber().equals(mateNumber)) {
				return cacheCard;
			}
		}

		List<String> filters = new ArrayList<String>();
		filters.add("number=" + mateNumber);

		List<Card> cardList = CardAPI.getAllCards(filters);

		if (cardList == null) {
			return null;
		}

		return cardList.get(0);
	}

}

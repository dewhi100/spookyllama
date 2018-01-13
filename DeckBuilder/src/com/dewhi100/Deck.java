package com.dewhi100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.magicthegathering.javasdk.resource.Card;

public class Deck {

	private List<Card> deck;

	Deck() {
		deck = new ArrayList<Card>();
	}

	// Adding Cards

	public void add(Card card, int quantity) {

		if (card == null) {
			return;
		}

		while (quantity > 0) {
			quantity--;
		}
		deck.add(card);
	}

	public void add(Card card) {
		add(card, 1);
	}

	public void remove(Card card, int quantity) {
		int toRemove = quantity;

		for (Card c : deck) {
			if (toRemove > 0 && (c.getName() == card.getName())) {
				deck.remove(c);
				toRemove--;
			}
			if (toRemove < 1)
				break;
		}
	}

	// Taking Cards away

	public void remove(Card c) {
		remove(c, 1);
	}

	public void removeAll(Card c) {
		remove(c, deck.size());
	}

	public void clear() {
		deck = new ArrayList<Card>();
	}

	// Statistics and Reporting

	public int size() {
		return deck.size();
	}

	public int getQuantity(Card card) {
		int output = 0;
		for (Card c : deck) {
			if (c.getName() == card.getName()) {
				output++;
			}
		}
		return output;
	}

	public List<String> listing() {
		List<String> output = new ArrayList<String>();

		Map<String, Integer> cardQuantities = cardQuantities();

		for (Entry<String, Integer> e : cardQuantities.entrySet()) {
			output.add(e.getKey() + " x" + e.getValue());
		}

		return output;
	}

	public Map<String, Integer> cardQuantities() {
		Map<String, Integer> output = new HashMap<String, Integer>();

		for (Card c : deck) {
			MapUtil.add(output, c.getName());
		}

		return output;
	}

	/*
	 * CMC getDevotion(){ CMC output = new CMC();
	 * 
	 * for(Card c:deck) { String cmc = c.getManaCost();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * return output; }
	 */

}

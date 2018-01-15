package com.dewhi100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.magicthegathering.javasdk.resource.Card;

import com.dewhi100.MagicCardClient;

public class Deck {

	private List<Card> deck;

	public Deck() {
		deck = new ArrayList<Card>();
	}

	public Deck(String input) {

		if (input == null) {
			return;
		}

		deck = new ArrayList<Card>();

		// break up the input by line
		String[] lines = input.split(System.lineSeparator());

		for (String s : lines) {
			// split line into quantity and name
			int space = s.indexOf(" ");
			String numberString = s.substring(0, space);
			Integer number = Integer.parseInt(numberString);
			String cardName = s.substring(space + 1);

			// retrieve the card from the wizards
			Card c = MagicCardClient.getCardByName(cardName);

			// add however many to the deck.
			if(c != null) {
				add(c, number);
			}
		}
	}

	// Adding Cards

	public void add(Card card, int quantity) {

		if (card == null) {
			return;
		}

		while (quantity > 0) {
			deck.add(card);
			quantity--;
		}
	}

	public void add(Card card) {
		if(card == null) {
			return;
		}
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
			output.add(e.getValue() + " " + e.getKey());
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

	public String toString() {
		StringBuffer sb = new StringBuffer();

		List<String> listing = listing();

		for (String s : listing) {
			sb.append(s).append("\n");
		}
		return sb.toString();
	}

	public List<String> exportCMC() {
		List<String> output = new ArrayList<String>();
		for (Card c : deck) {
			String manaCost = c.getManaCost();
			if (manaCost != null) {
				output.add(manaCost);
			}
		}
		return output;
	}
	
	public List<String> exportNames(){
		List<String> output = new ArrayList<String>();
		for (Card c : deck) {
			String name = c.getManaCost();
			if (name != null) {
				output.add(name);
			}
		}
		return output;
	}
	
	public List<Card> getDeck(){
		return new ArrayList<Card>(deck);
	}
}

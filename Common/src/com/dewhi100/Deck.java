package com.dewhi100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import io.magicthegathering.javasdk.resource.Card;

import com.dewhi100.MagicCardClient;
import com.dewhi100.util.CardUtil;
import com.dewhi100.util.CollectionsUtil;

public class Deck {

	private List<Card> deck;

	public Deck() {
		deck = new ArrayList<Card>();
	}

	private int dualCards;
	
	public Deck(String input) {

		if (input == null) {
			return;
		}

		dualCards = 0;
		
		deck = new ArrayList<Card>();

		// break up the input by line
		String[] lines = input.split(System.lineSeparator());

		for (String s : lines) {
			if(Pattern.matches("(Total: \\d+)|\\s*", s)) {
				continue;
			}
			if(!Pattern.matches("\\d+.*", s)) {
				add(MagicCardClient.getCardByName(s));
				continue;
			}
			// split line into quantity and name
			int space = s.indexOf(" ");
			String numberString = s.substring(0, space).replaceAll("\\D", "");
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
			if(CardUtil.checkIsPrimary(card)) {
				deck.add(card);
				Card mate = MagicCardClient.getCardByMate(card);
				deck.add(mate);
				dualCards++;				
			}else if(!CardUtil.checkIsDualCard(card)) {
				deck.add(card);				
			}
			quantity--;
		}
	}

	public void add(Card card) {
		if(card == null) {
			return;
		}
		add(card, 1);
	}

	// Taking Cards away

	public void remove(Card card, int quantity) {
		int toRemove = quantity;

		for (Card c : deck) {
			String cName = c.getName();
			if (toRemove > 0 && (cName == card.getName())) {
				Card mate = MagicCardClient.getCardByMate(c);
				if(cName != mate.getName()) {
					deck.remove(mate);
					dualCards--;
				}
				deck.remove(c);
				toRemove--;
			}
			if (toRemove < 1)
				break;
		}
	}

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
		return deck.size() - dualCards;
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
			CollectionsUtil.add(output, c.getName());
		}

		return output;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		List<String> listing = listing();

		for (String s : listing) {
			sb.append(s).append("\n");
		}
		
		sb.append("\n\nTotal: ").append(size()).append("\n");
		
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

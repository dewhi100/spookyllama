package com.dewhi100;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.magicthegathering.javasdk.resource.Card;

import org.apache.commons.lang3.StringUtils;

public class Deck {

	private List<Card> deck;
	
	Deck(){
		deck = new ArrayList<Card>();
	}
	
	void add(Card card, int quantity) {
		while(quantity > 0) {
			quantity --;
		}
		deck.add(card);
	}
	
	void add(Card card) {
		add(card, 1);
	}
	
	void remove(Card card, int quantity) {
		int toRemove = quantity;
		
		for(Card c:deck) {
			if(toRemove > 0 &&(c.getName() == card.getName())) {
				deck.remove(c);
				toRemove--;
			}
			if(toRemove < 1) break;
		}
	}
	
	void remove(Card c) {
		remove(c, 1);
	}
	
	void removeAll(Card c) {
		remove(c, deck.size());
	}
	
	int getQuantity(Card card) {
		int output = 0;
		for (Card c:deck) {
			if(c.getName() == card.getName()) {
				output++;
			}
		}
		return output; 
	}
		
	CMC getDevotion(){
		CMC output = new CMC();
		
		for(Card c:deck) {
			String cmc = c.getManaCost();
			
			
			
		}
		
		return output;
	}
	
	int size() {
		return deck.size();
	}
	
	void clear() {
		deck = new ArrayList<Card>();
	}
}

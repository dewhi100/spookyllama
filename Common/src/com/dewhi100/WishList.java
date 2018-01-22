package com.dewhi100;

import java.util.Map;

import io.magicthegathering.javasdk.resource.Card;

public class WishList {

	private Map<Card, Integer> wishes;
	private int turn;
	
	public WishList(Map<Card, Integer> w, int t) {
		wishes = w;
		turn = t;
	}
	
	public Map<Card, Integer> getWishes(){
		return wishes;
	}

	public int getTurn(){
		return turn;
	}
	
}

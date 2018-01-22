package com.dewhi100;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import io.magicthegathering.javasdk.resource.Card;

public class Parser {

	private Parser() {}
	
	public static WishList parseWishList(String input) {
		String[] lines = input.split(System.lineSeparator());
		Map<Card, Integer> wishes = new HashMap<Card, Integer>();
		
		for(String s:lines) {
			int turn = parseTurn(s);
			if(turn != 0) {
				return new WishList(wishes, turn + 6);
			}
			wishes.put(parseCard(s), parseQuantity(s));
		}
		
		return null;
	}

	private static int parseTurn(String input) {
		if(Pattern.matches("(T|t)urn \\d+.*", input)) {
			String numberString = input.replaceAll("\\D", "");
			return Integer.parseInt(numberString);
		}
		return 0;
	}
	
	private static Card parseCard(String input) {
		Card output = null;
		if(!Pattern.matches("\\d+.*", input)) {
			output =  MagicCardClient.getCardByName(input);
		}else {
			int space = input.indexOf(" ");
			String cardName = input.substring(space + 1);
			output =  MagicCardClient.getCardByName(cardName);
		}
		return output;
	}
	
	private static Integer parseQuantity(String input) {
		if(!Pattern.matches("\\d+.*", input)) {
			return 1;
		}
		int space = input.indexOf(" ");
		String numberString = input.substring(0, space).replaceAll("\\D", "");
		return Integer.parseInt(numberString);
	}
}

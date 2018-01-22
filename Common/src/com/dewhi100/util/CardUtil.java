package com.dewhi100.util;

import java.util.regex.Pattern;

import io.magicthegathering.javasdk.resource.Card;

public class CardUtil {

	private CardUtil() {}
	
	public static boolean checkIsDualCard(Card c) {
		if(c == null) {
			return false;
		}
		String number = c.getNumber();
		if(number == null) {
			return false;
		}
		return Pattern.matches("\\d*(a|b)", number);
	}
	
	public static boolean checkIsPrimary(Card c) {
		if(c == null) {
			return false;
		}
		String number = c.getNumber();
		if(number == null) {
			return false;
		}
		return Pattern.matches("\\d*a", number);
	}
}

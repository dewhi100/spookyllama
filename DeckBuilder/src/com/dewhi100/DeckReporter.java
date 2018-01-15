package com.dewhi100;

import java.text.DecimalFormat;

public class DeckReporter {

	private DeckReporter() {}
	
	public static String reportDevotion(ManaTally mc) {
		if(mc == null) {
			return "Invalid Mana Cost";
		}		
		int total = mc.getTotal() - mc.getColorless();
		if(total < 1) {
			return "No colored spells in this deck.\n";
		}
		int white = mc.getWhite();
		int blue = mc.getBlue();
		int black = mc.getBlack();
		int red = mc.getRed();
		int green = mc.getGreen();
		StringBuffer sb = new StringBuffer("Devotion:\n");

		appendIfNonZero(white, total, "White", sb);
		appendIfNonZero(blue, total, "Blue", sb);
		appendIfNonZero(black, total, "Black", sb);
		appendIfNonZero(red, total, "Red", sb);
		appendIfNonZero(green, total, "Green", sb);
		
		return sb.toString();
	}

	public static String reportManaSources(ManaTally mc) {
		if(mc == null) {
			return "Invalid Mana Cost";
		}
		
		if(mc.getTotal() < 1) {
			return "No mana sources in this deck!";
		}
		
		int total = mc.getTotal();
		StringBuffer sb = new StringBuffer("Mana Sources: \n");
		
		appendIfNonZero(mc.getWhite(), total, "White", sb);
		appendIfNonZero(mc.getBlue(), total, "Blue", sb);
		appendIfNonZero(mc.getBlack(), total, "Black", sb);
		appendIfNonZero(mc.getRed(), total, "Red", sb);
		appendIfNonZero(mc.getGreen(), total, "Green", sb);
		appendIfNonZero(mc.getColorless(), total, "Colorless", sb);
		
		return sb.toString();
	}
	
	private static void appendIfNonZero(int part, int whole, String color, StringBuffer sb) {
		if(part > 0) {
			sb.append(color).append(": ").append(percent(part, whole)).append("\n");
		}
	}
	
	private static String percent(int part, int whole) {
		double percentage = (double)part / (double)whole * 100;
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(percentage) + "%";
	}
	
}

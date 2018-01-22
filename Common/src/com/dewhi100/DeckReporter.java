package com.dewhi100;

import java.text.DecimalFormat;

public class DeckReporter {

	private DeckReporter() {}
	
	public static String reportDevotion(ManaTally mc) {
		if(mc == null) {
			return "Invalid Mana Cost\n";
		}		
		int total = mc.getTotal();
		
		if(total < 1) {
			return "No non-generic mana spells in this deck.\n";
		}
		int white = mc.getWhite();
		int blue = mc.getBlue();
		int black = mc.getBlack();
		int red = mc.getRed();
		int green = mc.getGreen();
		int colorless= mc.getColorless();
		StringBuffer sb = new StringBuffer("Percentage of total mana costs:\n");

		appendIfNonZero(white, total, "White", sb);
		appendIfNonZero(blue, total, "Blue", sb);
		appendIfNonZero(black, total, "Black", sb);
		appendIfNonZero(red, total, "Red", sb);
		appendIfNonZero(green, total, "Green", sb);
		appendIfNonZero(colorless, total, "Colorless", sb);
		appendIfNonZero(mc.getGeneric(), total, "Generic", sb);
				
		int nonGenericTotal = mc.getNonGeneric();
		
		sb.append("\nPercentage of non-generic mana costs:\n");

		appendIfNonZero(white, nonGenericTotal, "White", sb);
		appendIfNonZero(blue, nonGenericTotal, "Blue", sb);
		appendIfNonZero(black, nonGenericTotal, "Black", sb);
		appendIfNonZero(red, nonGenericTotal, "Red", sb);
		appendIfNonZero(green, nonGenericTotal, "Green", sb);
		appendIfNonZero(colorless, nonGenericTotal, "Colorless", sb);

		return sb.toString();
	}

	public static String reportManaSources(ManaTally mc) {
		if(mc == null) {
			return "Invalid Mana Cost\n";
		}
		
		int total = mc.getTotal();

		if(total < 1) {
			return "No mana sources in this deck!\n";
		}
		
		StringBuffer sb = new StringBuffer("Mana Sources: \n");
		
		appendIfNonZero(mc.getWhite(), total, "White", sb);
		appendIfNonZero(mc.getBlue(), total, "Blue", sb);
		appendIfNonZero(mc.getBlack(), total, "Black", sb);
		appendIfNonZero(mc.getRed(), total, "Red", sb);
		appendIfNonZero(mc.getGreen(), total, "Green", sb);
		appendIfNonZero(mc.getColorless(), total, "Colorless", sb);
		
		return sb.toString();
	}
	
	public static String reportFetchLands(ManaTally mt) {
		if(mt == null) {
			return "No fetch lands in this deck!";
		}
		
		int total = mt.getTotal();

		if(total < 1) {
			return "No fetch lands in this deck!";
		}
		
		StringBuffer sb = new StringBuffer("Fetch Lands: \n");
		
		sb.append(Constants.PLAINS).append(": ").append(mt.getWhite()).append("\n");
		sb.append(Constants.ISLAND).append("s: ").append(mt.getBlue()).append("\n");
		sb.append(Constants.SWAMP).append("s: ").append(mt.getBlack()).append("\n");
		sb.append(Constants.MOUNTAIN).append("s: ").append(mt.getRed()).append("\n");
		sb.append(Constants.FOREST).append("s: ").append(mt.getGreen()).append("\n");
		sb.append("Any land: ").append(mt.getColorless()).append("\n");
		sb.append("Total: ").append(total).append("\n");
		
		return sb.toString();
	}
	
	public static String reportOddsOfDrawing(String originalRequest, Double d) {
		StringBuffer sb = new StringBuffer(originalRequest).append("\n");
		sb.append("Odds: ").append(doubleToString(d)).append("\n");
		return sb.toString();
	}
	
	private static void appendIfNonZero(int part, int whole, String color, StringBuffer sb) {
		if(part > 0) {
			sb.append(color).append(": ").append(percent(part, whole)).append("\n");
		}
	}
	
	private static String doubleToString(Double percentage) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(percentage) + "%";		
	}
	
	private static String percent(int part, int whole) {
		double percentage = (double)part / (double)whole * 100;
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(percentage) + "%";
	}
	
	
	
}

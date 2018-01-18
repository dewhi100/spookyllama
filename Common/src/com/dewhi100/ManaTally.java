package com.dewhi100;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import io.magicthegathering.javasdk.resource.Card;

public class ManaTally {

	private int white = 0;
	private int blue = 0;
	private int black = 0;
	private int red = 0;
	private int green = 0;
	private int colorless = 0;
	private int total = 0;

	private ManaTally() {
	}

	// totat # of cards that can pull plains, islands, etc. from the deck.
	// distinct from sorceries such as rampant growth
	public static ManaTally asFetchLand(Card c) {
		if(c == null) {
			return null;
		}
		ManaTally output = new ManaTally();
		List<String> types = Arrays.asList(c.getTypes());
		if(types.contains("Land")) {
			String text = c.getText();
			if(fetches(Constants.PLAINS + "|basic land", text)) {
				output.setWhite(1);
				output.setTotal(1);
			}
			if(fetches(Constants.ISLAND + "|basic land", text)) {
				output.setBlue(1);
				output.setTotal(1);
			}
			if(fetches(Constants.SWAMP + "|basic land", text)) {
				output.setBlack(1);
				output.setTotal(1);
			}
			if(fetches(Constants.MOUNTAIN + "|basic land", text)) {
				output.setRed(1);
				output.setTotal(1);
			}
			if(fetches(Constants.FOREST + "|basic land", text)) {
				output.setGreen(1);
				output.setTotal(1);
			}
			//If any land can be fetched.
			if(fetches("(^((?!basic).)*land)", text)) {
				output.setColorless(1);
				output.setTotal(1);
			}
		}
		return output;
	}

	// parse the CMC. works with vanilla colors and colorless. Others are buggy.
	public static ManaTally asManaCost(Card c) {
		if(c == null) {
			return null;
		}
		ManaTally mt = new ManaTally();

		String cmc = c.getManaCost();

		if (cmc == null) {
			return mt;
		}

		mt.setWhite(StringUtils.countMatches(cmc, "{W}"));
		mt.setBlue(StringUtils.countMatches(cmc, "{U}"));
		mt.setBlack(StringUtils.countMatches(cmc, "{B}"));
		mt.setRed(StringUtils.countMatches(cmc, "{R}"));
		mt.setGreen(StringUtils.countMatches(cmc, "{G}"));
		mt.setColorless(StringUtils.countMatches(cmc, "{C}"));
		mt.setTotal((int) c.getCmc());

		return mt;
	}

	// any card that lets you add mana to your pool.
	public static ManaTally asManaSource(Card c) {
		if(c == null) {
			return null;
		}
		ManaTally mt = new ManaTally();

		String text = c.getText();
		mt.setWhite(tapsFor("W", text) ? 1 : 0);
		mt.setBlue(tapsFor("U", text) ? 1 : 0);
		mt.setBlack(tapsFor("B", text) ? 1 : 0);
		mt.setRed(tapsFor("R", text) ? 1 : 0);
		mt.setGreen(tapsFor("G", text) ? 1 : 0);
		mt.setColorless(tapsForColorless(text) ? 1 : 0);

		// basic lands
		switch (c.getName()) {
		case "Plains":
			mt.setWhite(1);
			break;
		case "Island":
			mt.setBlue(1);
			break;
		case "Swamp":
			mt.setBlack(1);
			break;
		case "Mountain":
			mt.setRed(1);
			break;
		case "Forest":
			mt.setGreen(1);
			break;
		}

		if (mt.getWhite() > 0 || mt.getBlue() > 0 || mt.getBlack() > 0 || mt.getRed() > 0 || mt.getGreen() > 0
				|| mt.getColorless() > 0) {
			mt.setTotal(1);
		} else {
			mt.setTotal(0);
		}

		return mt;
	}

	public ManaTally add(ManaTally mt) {
		setWhite(white + mt.getWhite());
		setBlue(blue + mt.getBlue());
		setBlack(black + mt.getBlack());
		setRed(red + mt.getRed());
		setGreen(green + mt.getGreen());
		setColorless(colorless + mt.getColorless());
		setTotal(total + mt.getTotal());
		return this;
	}

	static boolean tapsFor(String color, String text) {
		if (text == null) {
			return false;
		}

		String noNewLinesText = text.replace(System.lineSeparator(), "");

		String regex = ".*Add.*(\\{" + color + "\\}|one mana of any color).*to your mana pool.*";
		boolean matches = Pattern.matches(regex, noNewLinesText);
		return matches;
	}

	static boolean tapsForColorless(String text) {
		if (text == null) {
			return false;
		}

		String noNewLinesText = text.replace(System.lineSeparator(), "");

		String regex = ".*Add.*\\{C\\}.*to your mana pool.*";
		boolean matches = Pattern.matches(regex, noNewLinesText);
		return matches;

	}
	
	static boolean fetches(String color, String text) {
		if (text == null) {
			return false;
		}
		String noNewLinesText = text.replace(System.lineSeparator(), "");

		String regex = ".*Search your library for.*(" + color + ").*put (it|them) onto the battlefield.*";
		boolean matches = Pattern.matches(regex, noNewLinesText);
		return matches;
	}

	public int getWhite() {
		return white;
	}

	private void setWhite(int white) {
		this.white = white;
	}

	public int getBlue() {
		return blue;
	}

	private void setBlue(int blue) {
		this.blue = blue;
	}

	public int getBlack() {
		return black;
	}

	private void setBlack(int black) {
		this.black = black;
	}

	public int getRed() {
		return red;
	}

	private void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	private void setGreen(int green) {
		this.green = green;
	}

	public int getColorless() {
		return colorless;
	}

	private void setColorless(int colorless) {
		this.colorless = colorless;
	}

	public int getTotal() {
		return total;
	}

	private void setTotal(int total) {
		this.total = total;
	}
	
	public int getGeneric() {
		return total - white - blue - black - red - green - colorless;
	}
	
	public int getNonGeneric() {
		return white + blue + black + red + green + colorless;
	}
}

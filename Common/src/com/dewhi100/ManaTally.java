package com.dewhi100;

import java.util.regex.Matcher;
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

	public static ManaTally asManaCost(Card c) {
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

		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(cmc);
		while (m.find()) {
			mt.setColorless(Integer.parseInt(m.group()));
		}

		mt.setTotal((int) c.getCmc());

		return mt;
	}

	public static ManaTally asManaSource(Card c) {
		ManaTally mt = new ManaTally();

		String text = c.getText();
		mt.setWhite(tapsFor("W", text) ? 1 : 0);
		mt.setBlue(tapsFor("U", text) ? 1 : 0);
		mt.setBlack(tapsFor("B", text) ? 1 : 0);
		mt.setRed(tapsFor("R", text) ? 1 : 0);
		mt.setGreen(tapsFor("G", text) ? 1 : 0);
		mt.setColorless(tapsFor("C", text) ? 1 : 0);

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
		}else {
			mt.setTotal(0);
		}

		return mt;
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

	public ManaTally add(ManaTally mc) {
		setWhite(white + mc.getWhite());
		setBlue(blue + mc.getBlue());
		setBlack(black + mc.getBlack());
		setRed(red + mc.getRed());
		setGreen(green + mc.getGreen());
		setColorless(colorless + mc.getColorless());
		setTotal(total + mc.getTotal());
		return this;
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
}

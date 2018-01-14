package com.dewhi100;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import io.magicthegathering.javasdk.resource.Card;

public class ManaCost {

	private int white;
	private int blue;
	private int black;
	private int red;
	private int green;
	private int colorless;
	private int total;
	
	public ManaCost(Card c) {
		String cmc = c.getManaCost();

		setWhite(StringUtils.countMatches(cmc, "{W}"));
		setBlue(StringUtils.countMatches(cmc, "{U}"));
		setBlack(StringUtils.countMatches(cmc, "{B}"));
		setRed(StringUtils.countMatches(cmc, "{R}"));
		setGreen(StringUtils.countMatches(cmc, "{G}"));		
		
		setColorless(0);
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(cmc);
		while (m.find()) {
		    setColorless(Integer.parseInt(m.group()));
		}
		
		setTotal((int)c.getCmc());
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

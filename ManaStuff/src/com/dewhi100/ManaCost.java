package com.dewhi100;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import io.magicthegathering.javasdk.resource.Card;

public class ManaCost {

	private int white = 0;
	private int blue = 0;
	private int black = 0;
	private int red = 0;
	private int green = 0;
	private int colorless = 0;
	private int total = 0;
	
	public ManaCost(Card c) {
		String cmc = c.getManaCost();

		if(cmc == null) {
			return;
		}
		
		setWhite(StringUtils.countMatches(cmc, "{W}"));
		setBlue(StringUtils.countMatches(cmc, "{U}"));
		setBlack(StringUtils.countMatches(cmc, "{B}"));
		setRed(StringUtils.countMatches(cmc, "{R}"));
		setGreen(StringUtils.countMatches(cmc, "{G}"));		
		
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
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("White Cost: ").append(white).append("\n");
		sb.append("Blue Cost: ").append(blue).append("\n");
		sb.append("Black Cost: ").append(black).append("\n");
		sb.append("Red Cost: ").append(red).append("\n");
		sb.append("Green Cost: ").append(green).append("\n");
		sb.append("Colorless Cost: ").append(colorless).append("\n");
		sb.append("Total Cost: ").append(total);
		
		return sb.toString();
	}
}

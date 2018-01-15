package com.dewhi100;

import java.util.regex.Pattern;

import io.magicthegathering.javasdk.resource.Card;

public class ManaSource {

	private boolean white;
	private boolean blue;
	private boolean black;
	private boolean red;
	private boolean green;
	private boolean colorless;
	private boolean isSource;

	public ManaSource(Card c) {
		String text = c.getText();
		setWhite(tapsFor("W", text));
		setBlue(tapsFor("U", text));
		setBlack(tapsFor("B", text));
		setRed(tapsFor("R", text));
		setGreen(tapsFor("G", text));
		setColorless(tapsFor("C", text));

		//basic lands
		switch (c.getName()) {
		case "Plains":
			setWhite(true);
			break;
		case "Island":
			setBlue(true);
			break;
		case "Swamp":
			setBlack(true);
			break;
		case "Mountain":
			setRed(true);
			break;
		case "Forest":
			setGreen(true);
			break;
		}
		
		setSource(isWhite() || isBlue() || isBlack() || isRed() || isGreen() || isColorless());
	}

	boolean tapsFor(String color, String text) {
		if(text == null) {
			return false;
		}
		
		String noNewLinesText = text.replace(System.lineSeparator(), "");
		
		String regex = ".*Add.*(\\{" + color + "\\}|one mana of any color).*to your mana pool.*";
		System.out.println(regex);
		System.out.println(text);
		boolean matches = Pattern.matches(regex, noNewLinesText);
		System.out.println(matches);
		return matches;
	}
	
	public boolean isWhite() {
		return white;
	}

	private void setWhite(boolean white) {
		this.white = white;
	}

	public boolean isBlue() {
		return blue;
	}

	private void setBlue(boolean blue) {
		this.blue = blue;
	}

	public boolean isBlack() {
		return black;
	}

	private void setBlack(boolean black) {
		this.black = black;
	}

	public boolean isRed() {
		return red;
	}

	private void setRed(boolean red) {
		this.red = red;
	}

	public boolean isGreen() {
		return green;
	}

	private void setGreen(boolean green) {
		this.green = green;
	}

	public boolean isColorless() {
		return colorless;
	}

	private void setColorless(boolean colorless) {
		this.colorless = colorless;
	}

	public boolean isSource() {
		return isSource;
	}

	private void setSource(boolean isSource) {
		this.isSource = isSource;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Mana source: ").append(isSource()).append("\n");
		sb.append("Taps for white: ").append(white).append("\n");
		sb.append("Taps for blue: ").append(blue).append("\n");
		sb.append("Taps for black: ").append(black).append("\n");
		sb.append("Taps for red: ").append(red).append("\n");
		sb.append("Taps for green: ").append(green).append("\n");
		sb.append("Taps for colorless: ").append(colorless);
		
		return sb.toString();
	}
}

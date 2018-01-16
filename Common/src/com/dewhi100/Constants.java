package com.dewhi100;

import com.google.common.collect.ImmutableList;

public class Constants {

	//Basic mana
	public static final String WHITE = "{W}";
	public static final String BLUE = "{U}";
	public static final String BLACK = "{B}";
	public static final String RED = "{R}";
	public static final String GREEN = "{G}";
	public static final String COLORLESS = "colorless";
	
	//Hybrid Mana
	public static final String WHITE_BLUE = "{W/U}";
	public static final String WHITE_BLACK = "{W/B}";
	public static final String BLUE_RED = "{U/R}";
	public static final String BLUE_BLACK = "{U/B}";
	public static final String BLACK_RED = "{B/R}";
	public static final String BLACK_GREEN = "{B/G}";
	public static final String RED_WHITE = "{R/W}";
	public static final String RED_GREEN = "{R/G}";
	public static final String GREEN_WHITE = "{G/W}";
	public static final String GREEN_BLUE = "{G/U}";
	
	//Variable Cost Mana
	public static final String VARIABLE = "{X}";
	
	//Lands
	public static final ImmutableList<String> LANDS = ImmutableList.of("Plains","Island","Swamp","Mountain","Forest");
	public static final String PLAINS = "Plains";
	public static final String ISLAND = "Island";
	public static final String SWAMP = "Swamp";
	public static final String MOUNTAIN = "Mountain";
	public static final String FOREST = "Forest";
	
}

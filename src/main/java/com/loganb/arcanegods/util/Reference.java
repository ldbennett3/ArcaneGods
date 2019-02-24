package com.loganb.arcanegods.util;

public class Reference {

	public static final String MOD_ID = "arcanegods";
	public static final String NAME = "Arcane Gods";
	public static final String VERSION = "0.1";
	public static final String ACCEPTED_VERSIONS = "[1.12.2]";
	public static final String CLIENT_PROXY_CLASS = "com.loganb.arcanegods.proxy.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "com.loganb.arcanegods.proxy.CommonProxy";
	
	public static final int GUI_BRICK_FURNACE = 0;
	public static final int GUI_ENCHANTED_FURNACE = 1;
	public static final int GUI_LARGE_CAPACITY_FURNACE = 2;
	public static final int GUI_MAGIC_INFUSER = 3;
	public static final int GUI_GRINDER = 4;
	
	// GUI's for books. Contents declared in the item class, ID does nothing more than assign the class. 
	// the GUI is universal across all books and dynamically made from item class contents
	public static final int GUI_TESTING_BOOK_1 = 50;
	
}

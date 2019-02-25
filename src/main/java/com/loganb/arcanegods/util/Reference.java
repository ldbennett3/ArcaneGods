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
	public static final int GUI_TRANSLATION_TABLE = 5;
	
	// GUI's for books. Contents declared in the item class, ID does nothing more than assign the class. 
	// the GUI is universal across all books and dynamically made from item class contents
	public static final int GUI_TESTING_BOOK_1 = 50;
	
	// Book language enum. Name is used for description as well as the letter description
	public static enum BOOK_LANGUAGE {
		DRACONIC("vertical and scratchy"),
		CASMODIAN("almost like cyrollic"),
		ELVISH("curvy and fluid"),
		ORCISH("sloppy and jagged"),
		PORALIAN("made of slanted lines");
		
		public final String letterDescription;
		
		private BOOK_LANGUAGE(String letterDescription) {
			this.letterDescription = letterDescription;
		}
	};
	
}

package com.loganb.arcanegods.items.books;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.items.guis.GuiCustomBookScreen;
import com.loganb.arcanegods.items.guis.GuiCustomBookScreen.*;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class BookRegistration {

	static String photoLoc = Reference.MOD_ID + ":textures/gui/book_pictures/";
	static int leftPageStart = -111; // Good starting pos for pics. More negative = more left
	static int rightPageStart = 10; // Good starting pos for pics. More positive = more right

	// Example untranslated book
	static String[] untranslated_example_book_tooltips = new String[] {
		TextFormatting.GRAY + "This is an example of a tooltip",
		TextFormatting.DARK_BLUE + "You can color it too. woag"
	};
	static Page[] untranslated_example_book_pages = new Page[] {
			new Page("Testing for the left side of page 1", "Testing for the right side of page 1"),
			
			new Page("Testing for the left side of page 2", "Testing for the right side of page 2"),
			
			new Page("This is a testing page for the left side of the book. Its longer and used to demonstrate what a full book page" + 
			"might look like if done to completion.", "I wonder what Im going to do to fill all these books. Maybe copy some texts from skyrim to make a library?" + 
			" Gotta give people something to read afterall right?"),
			
			new Page("Yet another testing bit", "I know these pages are gross and empty. At least its not lorem ispum right?")
	};
	static DisplayImage[] untranslated_example_book_pictures = new DisplayImage[] {
		new DisplayImage(new ResourceLocation(photoLoc + "madness.png"), leftPageStart, 100, 0, 0, 125 - 20, 45, 0)
	};
	public static final Item UNTRANSLATED_TEST_BOOK = new CustomBookBase("untranslated_example_book", Main.itemsTab, Reference.BOOK_LANGUAGE.DRACONIC, untranslated_example_book_tooltips, 
			untranslated_example_book_pages, untranslated_example_book_pictures);
	
}

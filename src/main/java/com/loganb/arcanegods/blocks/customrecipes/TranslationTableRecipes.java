package com.loganb.arcanegods.blocks.customrecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.loganb.arcanegods.init.ModItems;
import com.loganb.arcanegods.items.books.TranslationTomeBase;
import com.loganb.arcanegods.items.books.UntranslatedBookBase;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TranslationTableRecipes {

	/** 
	 *
	 * The way this works is it gives you a random book that matches the language,
	 * however if thats not a good system, may just convert it to defined recipes
	 * It mostly depends on whether its worth the random aspect. 
	 * Odds are this will all get deleted and go back to being defined recipes
	 * 
	 */
	
	private static final TranslationTableRecipes INSTANCE = new TranslationTableRecipes();
	private float translationExperience = 2.0F;
	
	public static TranslationTableRecipes getInstance() {
		return INSTANCE;
	}
	
	private TranslationTableRecipes() {
		
	}
	
	/**
	 * Gets a translation result when given an untranslated book and tome
	 * @param book The book to translate
	 * @param tome The tome to translate the book with
	 * @return Returns ItemStack.EMPTY if no result, otherwise returns a random translated book
	 */
	public ItemStack getTranslationResult(ItemStack book, ItemStack tome) {
		Item stack1 = book.getItem();
		Item stack2 = tome.getItem();
		UntranslatedBookBase uBook;
		TranslationTomeBase tBook;
		
		if(stack1 instanceof UntranslatedBookBase) {
			uBook = (UntranslatedBookBase)stack1;

			if(stack2 instanceof TranslationTomeBase) {
				tBook = (TranslationTomeBase)stack2;
				
				return getRandomBookByLanguage(uBook.getLanguage());
			}
			
		}
		
		return ItemStack.EMPTY;
	}
	
	/**
	 * Retrieves a book from the ModItems list that has the correct language
	 * @param lang Language of the book to retrieve
	 * @return Item of the book
	 */
	public ItemStack getRandomBookByLanguage(Reference.BOOK_LANGUAGE lang) {
		
		ArrayList<Item> books = new ArrayList<Item>();
		
		for(Item i : ModItems.ITEMS) {
			if(i instanceof UntranslatedBookBase) {
				UntranslatedBookBase temp = (UntranslatedBookBase)i;
				if(temp.getLanguage() == lang) {
					books.add(i);
				}
			}
		}
		
		if(!books.isEmpty()) {
			double index = Math.random() * books.size();
			return new ItemStack(books.get((int)index));
		}
		
		return ItemStack.EMPTY;
	}
	
	public boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public float getTranslatingExperience(ItemStack stack) {
		return translationExperience;
	}
	
}

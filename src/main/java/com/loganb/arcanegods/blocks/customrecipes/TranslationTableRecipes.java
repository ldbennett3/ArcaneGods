package com.loganb.arcanegods.blocks.customrecipes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.loganb.arcanegods.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TranslationTableRecipes {

	/** MOST OF THIS NEEDS TO BE REWORKED COMPLETELY. JUST COPIED GRINDER FOR THE TIME BEING TO GET CODE IN **/
	
	private static final TranslationTableRecipes INSTANCE = new TranslationTableRecipes();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	private final Map<ItemStack, ItemStack> grindingList = Maps.<ItemStack, ItemStack>newHashMap();
	
	public static TranslationTableRecipes getInstance() {
		return INSTANCE;
	}
	
	private TranslationTableRecipes() {
		
	}
	
	public void addRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if (getTranslationResult(input1, input2) != ItemStack.EMPTY && getTranslationResult(input2, input1) != ItemStack.EMPTY) {
			return; // Don't add an existing recipe
		}
		
		//this.grindingList.put(input1, result);
		//this.experienceList.put(result, Float.valueOf(experience));
		
	}
	
	public List<Item> getIngredients() {
		List<Item> temp = new ArrayList<>();
		
		for (Entry<ItemStack, ItemStack> ent : this.grindingList.entrySet()) {
			temp.add(ent.getKey().getItem());
		}
		
		return temp;
	}
	
	public ItemStack getTranslationResult(ItemStack input1, ItemStack input2) {
		return ItemStack.EMPTY;
	}
	
	public boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public float getTranslatingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
}

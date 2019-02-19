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

public class GrinderRecipes {

	private static final GrinderRecipes INSTANCE = new GrinderRecipes();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	private final Map<ItemStack, ItemStack> grindingList = Maps.<ItemStack, ItemStack>newHashMap();
	
	public static GrinderRecipes getInstance() {
		return INSTANCE;
	}
	
	private GrinderRecipes() {
		addRecipe(new ItemStack(ModItems.PALE_CRYSTAL), new ItemStack(ModItems.NEUTRALIZING_POWDER, 2), 5.0F);
		addRecipe(new ItemStack(ModItems.LAVENDER_CRYSTAL), new ItemStack(ModItems.LAVENDER_POWDER, 2), 5.0F);
		addRecipe(new ItemStack(ModItems.ICEY_CRYSTAL), new ItemStack(ModItems.ICEY_POWDER, 2), 5.0F);
		addRecipe(new ItemStack(ModItems.THUNDER_CRYSTAL), new ItemStack(ModItems.THUNDER_POWDER, 2), 5.0F);
		addRecipe(new ItemStack(ModItems.FIERY_CRYSTAL), new ItemStack(ModItems.FIERY_POWDER, 2), 5.0F);
	}
	
	public void addRecipe(ItemStack input, ItemStack result, float experience) {
		if (getGrindResult(input) != ItemStack.EMPTY) return; // Don't add an existing recipe
		
		this.grindingList.put(input, result);
		this.experienceList.put(result, Float.valueOf(experience));
		
	}
	
	public List<Item> getIngredients() {
		List<Item> temp = new ArrayList<>();
		
		for (Entry<ItemStack, ItemStack> ent : this.grindingList.entrySet()) {
			temp.add(ent.getKey().getItem());
		}
		
		return temp;
	}
	
	public ItemStack getGrindResult(ItemStack input) {
		
		for (Entry<ItemStack, ItemStack> ent : this.grindingList.entrySet()) {
			if(this.compareItemStacks(input, ent.getKey())) {
				return ent.getValue();
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	public boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getGrindingList() {
		return this.grindingList;
	}
	
	public float getGrindingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
}

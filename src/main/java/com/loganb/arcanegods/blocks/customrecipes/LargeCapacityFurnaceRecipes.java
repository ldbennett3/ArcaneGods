package com.loganb.arcanegods.blocks.customrecipes;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.loganb.arcanegods.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LargeCapacityFurnaceRecipes {

	private static final LargeCapacityFurnaceRecipes INSTANCE = new LargeCapacityFurnaceRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> alloyList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static LargeCapacityFurnaceRecipes getInstance() {
		return INSTANCE;
	}
	
	private LargeCapacityFurnaceRecipes() {
		addRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.COAL), new ItemStack(ModItems.STEEL_INGOT), 5.0F);
		addRecipe(new ItemStack(ModItems.COPPER_INGOT), new ItemStack(ModItems.STEEL_INGOT), new ItemStack(ModItems.BRONZE_INGOT), 10.0F);
		addRecipe(new ItemStack(Items.COAL), new ItemStack(Items.GOLD_NUGGET), new ItemStack(ModItems.ARCANE_COAL), 10.0F);
	}
	
	public void addRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if (getAlloyResult(input1, input2) != ItemStack.EMPTY) return; // Don't add an existing recipe
		
		// Insert input1, input2 and vice versa to allow putting either ingredient in either slot
		this.alloyList.put(input1, input2, result);
		this.alloyList.put(input2, input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
		
	}
	
	public List<Item> getIngredients() {
		List<Item> temp = new ArrayList<>();
		
		for (Cell<ItemStack, ItemStack, ItemStack> cell : this.alloyList.cellSet()) {
			temp.add(cell.getRowKey().getItem());
		}
		
		return temp;
	}
	
	public ItemStack getAlloyResult(ItemStack input1, ItemStack input2) {
		for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.alloyList.columnMap().entrySet()) {
			if (this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if (this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getAlloySmeltingList() {
		return this.alloyList;
	}
	
	public float getAlloyingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
}

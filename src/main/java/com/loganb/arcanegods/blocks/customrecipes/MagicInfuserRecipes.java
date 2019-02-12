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

public class MagicInfuserRecipes {

	private static final MagicInfuserRecipes INSTANCE = new MagicInfuserRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> infuseList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static MagicInfuserRecipes getInstance() {
		return INSTANCE;
	}
	
	private MagicInfuserRecipes() {
		addRecipe(new ItemStack(ModItems.FIERY_CRYSTAL), new ItemStack(ModItems.NEUTRALIZING_POWDER), new ItemStack(ModItems.PALE_CRYSTAL), 10.0F);
		addRecipe(new ItemStack(ModItems.LAVENDER_CRYSTAL), new ItemStack(ModItems.NEUTRALIZING_POWDER), new ItemStack(ModItems.PALE_CRYSTAL), 10.0F);
		addRecipe(new ItemStack(ModItems.THUNDER_CRYSTAL), new ItemStack(ModItems.NEUTRALIZING_POWDER), new ItemStack(ModItems.PALE_CRYSTAL), 10.0F);
		addRecipe(new ItemStack(ModItems.ICEY_CRYSTAL), new ItemStack(ModItems.NEUTRALIZING_POWDER), new ItemStack(ModItems.PALE_CRYSTAL), 10.0F);
		
		addRecipe(new ItemStack(ModItems.PALE_CRYSTAL), new ItemStack(ModItems.FIERY_POWDER), new ItemStack(ModItems.FIERY_CRYSTAL), 10.0F);
		addRecipe(new ItemStack(ModItems.PALE_CRYSTAL), new ItemStack(ModItems.LAVENDER_POWDER), new ItemStack(ModItems.LAVENDER_CRYSTAL), 10.0F);
		addRecipe(new ItemStack(ModItems.PALE_CRYSTAL), new ItemStack(ModItems.THUNDER_POWDER), new ItemStack(ModItems.THUNDER_CRYSTAL), 10.0F);
		addRecipe(new ItemStack(ModItems.PALE_CRYSTAL), new ItemStack(ModItems.ICEY_POWDER), new ItemStack(ModItems.ICEY_CRYSTAL), 10.0F);
	}
	
	public void addRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if (getInfuseResult(input1, input2) != ItemStack.EMPTY) return; // Don't add an existing recipe
		
		// Insert input1, input2 and vice versa to allow putting either ingredient in either slot
		this.infuseList.put(input1, input2, result);
		this.infuseList.put(input2, input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
		
	}
	
	public List<Item> getIngredients() {
		List<Item> temp = new ArrayList<>();
		
		for (Cell<ItemStack, ItemStack, ItemStack> cell : this.infuseList.cellSet()) {
			temp.add(cell.getRowKey().getItem());
		}
		
		return temp;
	}
	
	public ItemStack getInfuseResult(ItemStack input1, ItemStack input2) {
		for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.infuseList.columnMap().entrySet()) {
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
	
	public Table<ItemStack, ItemStack, ItemStack> getInfusingSmeltingList() {
		return this.infuseList;
	}
	
	public float getInfusingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
	
}

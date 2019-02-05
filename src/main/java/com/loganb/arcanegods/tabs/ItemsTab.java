package com.loganb.arcanegods.tabs;

import com.loganb.arcanegods.init.ModBlocks;
import com.loganb.arcanegods.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemsTab extends CreativeTabs {
	
	public ItemsTab(String name) {
		super("itemsTab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.TEST_CRYSTAL, 1);
	}
	
}


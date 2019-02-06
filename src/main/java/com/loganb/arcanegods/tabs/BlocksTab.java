package com.loganb.arcanegods.tabs;

import com.loganb.arcanegods.init.ModBlocks;
import com.loganb.arcanegods.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlocksTab extends CreativeTabs {

	public BlocksTab(String name) {
		super("blocksTab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(ModBlocks.PALE_CRYSTAL_BLOCK), 1);
	}
	
}


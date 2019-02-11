package com.loganb.arcanegods.blocks.slots;

import com.loganb.arcanegods.blocks.tileentities.TileEntityLargeCapacityFurnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLargeCapacityFurnaceFuel extends Slot {

	public SlotLargeCapacityFurnaceFuel(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityLargeCapacityFurnace.isItemFuel(stack);
	}
	
}

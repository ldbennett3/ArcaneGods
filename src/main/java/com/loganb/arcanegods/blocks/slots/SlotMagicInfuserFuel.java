package com.loganb.arcanegods.blocks.slots;

import com.loganb.arcanegods.blocks.tileentities.TileEntityMagicInfuser;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMagicInfuserFuel extends Slot {

	public SlotMagicInfuserFuel(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityMagicInfuser.isItemFuel(stack);
	}
	
}

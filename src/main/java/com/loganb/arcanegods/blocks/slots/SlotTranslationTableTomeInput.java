package com.loganb.arcanegods.blocks.slots;

import com.loganb.arcanegods.items.books.TranslationTomeBase;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTranslationTableTomeInput extends Slot {

	public SlotTranslationTableTomeInput(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof TranslationTomeBase;
	}

	
}

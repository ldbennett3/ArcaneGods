package com.loganb.arcanegods.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class FuelItemBase extends ItemBase {

	private int burnTime;
	
	public FuelItemBase(String name, CreativeTabs tab, int burnTime) {
		super(name, tab);
		this.burnTime = burnTime;
	}
	
	@Override
	public int getItemBurnTime(ItemStack itemStack) {
		return burnTime;
	}

}

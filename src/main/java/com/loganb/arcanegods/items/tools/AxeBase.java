package com.loganb.arcanegods.items.tools;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.init.ModItems;
import com.loganb.arcanegods.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;

public class AxeBase extends ItemAxe implements IHasModel {
	
	public AxeBase(String name, ToolMaterial material, CreativeTabs tab) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}

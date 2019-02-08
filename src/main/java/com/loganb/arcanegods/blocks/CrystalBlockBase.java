package com.loganb.arcanegods.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class CrystalBlockBase extends BlockBase {

	public CrystalBlockBase(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
		
		setSoundType(SoundType.GLASS);
		setHardness(0.6F);
		setResistance(2.0F);
		setHarvestLevel("pickaxe", 0);
		setLightLevel(8 / 16F);
		
	}
	
}

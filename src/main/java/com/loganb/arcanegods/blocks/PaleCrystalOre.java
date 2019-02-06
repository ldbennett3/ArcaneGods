package com.loganb.arcanegods.blocks;

import java.util.Random;

import com.loganb.arcanegods.init.ModItems;
import com.loganb.arcanegods.util.IHasModel;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PaleCrystalOre extends BlockBase implements IHasModel {

	public PaleCrystalOre(String name, Material material, CreativeTabs tab) {
		super(name, material, tab);
		setSoundType(SoundType.STONE);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.PALE_CRYSTAL;
	}

	@Override
	public int quantityDropped(Random rand) {
		int max = 4;
		int min = 1;
		return rand.nextInt(max) + min;
	}
	
}

package com.loganb.arcanegods.init;

import java.util.ArrayList;
import java.util.List;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.blocks.PaleCrystalBlock;
import com.loganb.arcanegods.blocks.PaleCrystalOre;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block PALE_CRYSTAL_BLOCK = new PaleCrystalBlock("pale_crystal_block", Material.GLASS, Main.blocksTab);
	public static final Block PALE_CRYSTAL_ORE = new PaleCrystalOre("pale_crystal_ore", Material.ROCK, Main.blocksTab);
	
}

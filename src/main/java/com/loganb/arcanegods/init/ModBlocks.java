package com.loganb.arcanegods.init;

import java.util.ArrayList;
import java.util.List;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.blocks.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block TEST_CRYSTAL_BLOCK = new TestCrystalBlock("test_crystal_block", Material.GLASS, Main.blocksTab);
	public static final Block TEST_CRYSTAL_ORE = new TestCrystalOre("test_crystal_ore", Material.ROCK, Main.blocksTab);
	
}

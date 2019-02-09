package com.loganb.arcanegods.init;

import java.util.ArrayList;
import java.util.List;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.blocks.CrystalOreBase;
import com.loganb.arcanegods.blocks.devices.BrickFurnace;
import com.loganb.arcanegods.blocks.BlockBase;
import com.loganb.arcanegods.blocks.CrystalBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	// Blocks
	public static final Block PALE_CRYSTAL_BLOCK = new CrystalBlockBase("pale_crystal_block", Material.GLASS, Main.blocksTab);
	public static final Block FIERY_CRYSTAL_BLOCK = new CrystalBlockBase("fiery_crystal_block", Material.GLASS, Main.blocksTab);
	public static final Block LAVENDER_CRYSTAL_BLOCK = new CrystalBlockBase("lavender_crystal_block", Material.GLASS, Main.blocksTab);
	public static final Block ICEY_CRYSTAL_BLOCK = new CrystalBlockBase("icey_crystal_block", Material.GLASS, Main.blocksTab);
	public static final Block THUNDER_CRYSTAL_BLOCK = new CrystalBlockBase("thunder_crystal_block", Material.GLASS, Main.blocksTab);
	
	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Material.IRON, Main.blocksTab);
	public static final Block BRONZE_BLOCK = new BlockBase("bronze_block", Material.IRON, Main.blocksTab);
	public static final Block STEEL_BLOCK  = new BlockBase("steel_block",  Material.IRON, Main.blocksTab);
	public static final Block SILVER_BLOCK = new BlockBase("silver_block", Material.IRON, Main.blocksTab);
	
	public static final Block MARBLE_BLOCK  = new BlockBase("marble_block", Material.ROCK, Main.blocksTab);
	public static final Block MARBLE_BRICKS = new BlockBase("marble_bricks", Material.ROCK, Main.blocksTab);
	public static final Block MARBLE_COLUMN = new BlockBase("marble_column", Material.ROCK, Main.blocksTab);
	
	// Devices
	public static final Block BRICK_FURNACE = new BrickFurnace("brick_furnace");
	
	// Ore
	public static final Block PALE_CRYSTAL_ORE = new CrystalOreBase("pale_crystal_ore", Material.ROCK, Main.blocksTab, ModItems.PALE_CRYSTAL);
	public static final Block FIERY_CRYSTAL_ORE = new CrystalOreBase("fiery_crystal_ore", Material.ROCK, Main.blocksTab, ModItems.FIERY_CRYSTAL);
	public static final Block LAVENDER_CRYSTAL_ORE = new CrystalOreBase("lavender_crystal_ore", Material.ROCK, Main.blocksTab, ModItems.LAVENDER_CRYSTAL);
	public static final Block ICEY_CRYSTAL_ORE = new CrystalOreBase("icey_crystal_ore", Material.ROCK, Main.blocksTab, ModItems.ICEY_CRYSTAL);
	public static final Block THUNDER_CRYSTAL_ORE = new CrystalOreBase("thunder_crystal_ore", Material.ROCK, Main.blocksTab, ModItems.THUNDER_CRYSTAL);
	
	public static final Block COPPER_ORE = new BlockBase("copper_ore", Material.ROCK, Main.blocksTab);
	public static final Block SILVER_ORE = new BlockBase("silver_ore", Material.ROCK, Main.blocksTab);
	
}

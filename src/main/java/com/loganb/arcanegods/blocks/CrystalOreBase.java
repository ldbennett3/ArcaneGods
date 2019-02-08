package com.loganb.arcanegods.blocks;

import java.util.Random;

import com.loganb.arcanegods.init.ModItems;
import com.loganb.arcanegods.util.IHasModel;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class CrystalOreBase extends BlockBase implements IHasModel {

	public static final AxisAlignedBB CRYSTAL_ORE_AABB = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 1D);
	
	public static Item drop = null;
	
	public CrystalOreBase(String name, Material material, CreativeTabs tab, Item drop) {
		super(name, material, tab);
		setSoundType(SoundType.STONE);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(5 / 16f);
		
		this.drop = drop;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return drop;
	}

	@Override
	public int quantityDropped(Random rand) {
		int max = 4;
		int min = 1;
		return rand.nextInt(max) + min;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CRYSTAL_ORE_AABB;
	}
	
}

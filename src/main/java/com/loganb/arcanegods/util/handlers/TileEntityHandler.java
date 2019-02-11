package com.loganb.arcanegods.util.handlers;

import com.loganb.arcanegods.blocks.tileentities.TileEntityBrickFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityEnchantedFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityLargeCapacityFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityMagicInfuser;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBrickFurnace.class, "brick_furnace");
		GameRegistry.registerTileEntity(TileEntityEnchantedFurnace.class, "enchanted_furnace");
		GameRegistry.registerTileEntity(TileEntityLargeCapacityFurnace.class, "large_capacity_furnace");
		GameRegistry.registerTileEntity(TileEntityMagicInfuser.class, "magic_infuser");
	}
	
}

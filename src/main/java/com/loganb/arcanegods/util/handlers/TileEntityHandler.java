package com.loganb.arcanegods.util.handlers;

import com.loganb.arcanegods.blocks.tileentities.TileEntityBrickFurnace;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBrickFurnace.class, "brick_furnace");
	}
	
}

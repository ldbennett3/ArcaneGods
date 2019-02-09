package com.loganb.arcanegods.util.handlers;

import com.loganb.arcanegods.blocks.containers.ContainerBrickFurnace;
import com.loganb.arcanegods.blocks.guis.GuiBrickFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityBrickFurnace;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_BRICK_FURNACE) return new ContainerBrickFurnace(player.inventory, (TileEntityBrickFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_BRICK_FURNACE) return new GuiBrickFurnace(player.inventory, (TileEntityBrickFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	
	
}

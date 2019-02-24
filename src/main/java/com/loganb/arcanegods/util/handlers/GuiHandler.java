package com.loganb.arcanegods.util.handlers;

import com.loganb.arcanegods.blocks.containers.ContainerBrickFurnace;
import com.loganb.arcanegods.blocks.containers.ContainerEnchantedFurnace;
import com.loganb.arcanegods.blocks.containers.ContainerGrinder;
import com.loganb.arcanegods.blocks.containers.ContainerLargeCapacityFurnace;
import com.loganb.arcanegods.blocks.containers.ContainerMagicInfuser;
import com.loganb.arcanegods.blocks.guis.GuiBrickFurnace;
import com.loganb.arcanegods.blocks.guis.GuiEnchantedFurnace;
import com.loganb.arcanegods.blocks.guis.GuiGrinder;
import com.loganb.arcanegods.blocks.guis.GuiLargeCapacityFurnace;
import com.loganb.arcanegods.blocks.guis.GuiMagicInfuser;
import com.loganb.arcanegods.blocks.tileentities.TileEntityBrickFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityEnchantedFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityGrinder;
import com.loganb.arcanegods.blocks.tileentities.TileEntityLargeCapacityFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityMagicInfuser;
import com.loganb.arcanegods.items.books.UntranslatedCustomBook;
import com.loganb.arcanegods.items.guis.GuiCustomBookScreen;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_BRICK_FURNACE) return new ContainerBrickFurnace(player.inventory, (TileEntityBrickFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_ENCHANTED_FURNACE) return new ContainerEnchantedFurnace(player.inventory, (TileEntityEnchantedFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_LARGE_CAPACITY_FURNACE) return new ContainerLargeCapacityFurnace(player.inventory, (TileEntityLargeCapacityFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_MAGIC_INFUSER) return new ContainerMagicInfuser(player.inventory, (TileEntityMagicInfuser)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_GRINDER) return new ContainerGrinder(player.inventory, (TileEntityGrinder)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		// Devices
		if(ID == Reference.GUI_BRICK_FURNACE) return new GuiBrickFurnace(player.inventory, (TileEntityBrickFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_ENCHANTED_FURNACE) return new GuiEnchantedFurnace(player.inventory, (TileEntityEnchantedFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_LARGE_CAPACITY_FURNACE) return new GuiLargeCapacityFurnace(player.inventory, (TileEntityLargeCapacityFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_MAGIC_INFUSER) return new GuiMagicInfuser(player.inventory, (TileEntityMagicInfuser)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_GRINDER) return new GuiGrinder(player.inventory, (TileEntityGrinder)world.getTileEntity(new BlockPos(x, y, z)));
		
		// Books
		if(ID == Reference.GUI_TESTING_BOOK_1) return new GuiCustomBookScreen(player, UntranslatedCustomBook.getPages(), UntranslatedCustomBook.getImages());
		
		return null;
	}

	
	
}

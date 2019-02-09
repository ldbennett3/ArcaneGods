package com.loganb.arcanegods.util.handlers;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.init.ModBlocks;
import com.loganb.arcanegods.init.ModItems;
import com.loganb.arcanegods.init.ModRecipes;
import com.loganb.arcanegods.util.IHasModel;
import com.loganb.arcanegods.util.compat.OreDictionaryCompat;
import com.loganb.arcanegods.world.ModWorldGen;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) 
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) 
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		// Register items models
		for(Item item : ModItems.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		// Register blocks models
		for(Block block : ModBlocks.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegisters() {
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
	}
	
	public static void initRegisters() {
		ModRecipes.init();
		OreDictionaryCompat.registerOres();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void postInitRegister() {
	
	}
	
}

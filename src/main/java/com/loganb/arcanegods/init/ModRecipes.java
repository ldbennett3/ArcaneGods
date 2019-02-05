package com.loganb.arcanegods.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init() {
		// Test Crystal -> Stone block
		GameRegistry.addSmelting(ModItems.TEST_CRYSTAL, new ItemStack(Blocks.STONE, 1), 5.0F);
	}
	
}

package com.loganb.arcanegods.util.compat;

import com.loganb.arcanegods.init.ModBlocks;
import com.loganb.arcanegods.init.ModItems;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryCompat {

	public static void registerOres() {
		OreDictionary.registerOre("ingotCopper", ModItems.COPPER_INGOT);
		OreDictionary.registerOre("steelCopper", ModItems.STEEL_INGOT);
		OreDictionary.registerOre("bronzeCopper", ModItems.BRONZE_INGOT);
		OreDictionary.registerOre("silverCopper", ModItems.SILVER_INGOT);
		
		OreDictionary.registerOre("oreCopper", ModBlocks.COPPER_ORE);
		OreDictionary.registerOre("oreSilver", ModBlocks.SILVER_ORE);
	}
	
}

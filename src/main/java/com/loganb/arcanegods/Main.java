package com.loganb.arcanegods;

import com.loganb.arcanegods.init.ModItems;
import com.loganb.arcanegods.proxy.CommonProxy;
import com.loganb.arcanegods.tabs.BlocksTab;
import com.loganb.arcanegods.tabs.ItemsTab;
import com.loganb.arcanegods.util.Reference;
import com.loganb.arcanegods.util.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	@Instance
	public static Main instance;
	
	public static final CreativeTabs blocksTab = new BlocksTab("blocksTab");
	public static final CreativeTabs itemsTab = new ItemsTab("itemsTab");
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInitRegisters();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		RegistryHandler.initRegisters();
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegister();
	}
	
	
}

package com.loganb.arcanegods.init;

import java.util.ArrayList;
import java.util.List;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.items.ItemBase;
import com.loganb.arcanegods.items.armor.ArmorBase;
import com.loganb.arcanegods.items.tools.AxeBase;
import com.loganb.arcanegods.items.tools.HoeBase;
import com.loganb.arcanegods.items.tools.PickaxeBase;
import com.loganb.arcanegods.items.tools.ShovelBase;
import com.loganb.arcanegods.items.tools.SwordBase;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	// Materials
	public static final ToolMaterial MATERIAL_PALE_CRYSTAL = EnumHelper.addToolMaterial("material_pale_crystal", 2, 300, 8.0F, 2.0F, 20);
	public static final ArmorMaterial ARMOR_MATERIAL_PALE_CRYSTAL = EnumHelper.addArmorMaterial("armor_material_pale_crystal", Reference.MOD_ID + ":pale_crystal", 11, new int[] {2,3,5,1}, 
			30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	
	// Items
	public static final Item PALE_CRYSTAL = new ItemBase("pale_crystal", Main.itemsTab);
	
	// Tools
	public static final ItemSword PALE_CRYSTAL_SWORD = new SwordBase("pale_crystal_sword", MATERIAL_PALE_CRYSTAL, Main.itemsTab);
	
	// Armor
	// RenderIndexID (3rd param) is always 1 except for legs, which are 2
	public static final Item PALE_CRYSTAL_HELMET = new ArmorBase("pale_crystal_helmet", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.HEAD, Main.itemsTab);
	public static final Item PALE_CRYSTAL_CHESTPLATE = new ArmorBase("pale_crystal_chestplate", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.CHEST, Main.itemsTab);
	public static final Item PALE_CRYSTAL_LEGGINGS = new ArmorBase("pale_crystal_leggings", ARMOR_MATERIAL_PALE_CRYSTAL, 2, EntityEquipmentSlot.LEGS, Main.itemsTab);
	public static final Item PALE_CRYSTAL_BOOTS = new ArmorBase("pale_crystal_boots", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.FEET, Main.itemsTab);
	
}

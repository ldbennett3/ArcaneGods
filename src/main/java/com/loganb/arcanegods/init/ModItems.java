package com.loganb.arcanegods.init;

import java.util.ArrayList;
import java.util.List;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.items.FuelItemBase;
import com.loganb.arcanegods.items.ItemBase;
import com.loganb.arcanegods.items.armor.ArmorBase;
import com.loganb.arcanegods.items.books.ItemTestingBookOne;
import com.loganb.arcanegods.items.tools.SwordBase;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	// Materials
	public static final ToolMaterial MATERIAL_PALE_CRYSTAL = EnumHelper.addToolMaterial("material_pale_crystal", 2, 300, 8.0F, 2.0F, 20);
	
	public static final ArmorMaterial ARMOR_MATERIAL_PALE_CRYSTAL = EnumHelper.addArmorMaterial("armor_material_pale_crystal", Reference.MOD_ID + ":pale_crystal", 11, new int[] {2,3,5,1}, 
			30, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	
	// Items
	public static final Item PALE_CRYSTAL     = new ItemBase("pale_crystal", Main.itemsTab);
	public static final Item FIERY_CRYSTAL    = new ItemBase("fiery_crystal", Main.itemsTab);
	public static final Item LAVENDER_CRYSTAL = new ItemBase("lavender_crystal", Main.itemsTab);
	public static final Item ICEY_CRYSTAL     = new ItemBase("icey_crystal", Main.itemsTab);
	public static final Item THUNDER_CRYSTAL  = new ItemBase("thunder_crystal", Main.itemsTab);
	
	public static final Item COPPER_INGOT     = new ItemBase("copper_ingot", Main.itemsTab);
	public static final Item BRONZE_INGOT     = new ItemBase("bronze_ingot", Main.itemsTab);
	public static final Item STEEL_INGOT      = new ItemBase("steel_ingot", Main.itemsTab);
	public static final Item SILVER_INGOT     = new ItemBase("silver_ingot", Main.itemsTab);
	
	public static final Item NEUTRALIZING_POWDER = new ItemBase("neutralizing_powder", Main.itemsTab);
	public static final Item FIERY_POWDER = new ItemBase("fiery_powder", Main.itemsTab);
	public static final Item LAVENDER_POWDER = new ItemBase("lavender_powder", Main.itemsTab);
	public static final Item ICEY_POWDER = new ItemBase("icey_powder", Main.itemsTab);
	public static final Item THUNDER_POWDER = new ItemBase("thunder_powder", Main.itemsTab);
	
	public static final Item METALLURGIC_COAL = new FuelItemBase("metallurgic_coal", Main.itemsTab, 1600 * 2);
	public static final Item ARCANE_COAL = new FuelItemBase("arcane_coal", Main.itemsTab, 1600 * 4);
	public static final Item ALCHEMICAL_FUEL = new ItemBase("alchemical_fuel", Main.itemsTab);
	public static final Item ENCHANCED_ALCHEMICAL_FUEL = new ItemBase("enhanced_alchemical_fuel", Main.itemsTab);
	
	public static final Item UNTRANSLATED_TEST_BOOK = new ItemTestingBookOne("untranslated_custom_book", Main.itemsTab);
	
	// Tools
	public static final ItemSword PALE_CRYSTAL_SWORD = new SwordBase("pale_crystal_sword", MATERIAL_PALE_CRYSTAL, Main.itemsTab);
	
	// Armor
	// RenderIndexID (3rd param) is always 1 except for legs, which are 2
	public static final Item PALE_CRYSTAL_HELMET = new ArmorBase("pale_crystal_helmet", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.HEAD, Main.itemsTab);
	public static final Item PALE_CRYSTAL_CHESTPLATE = new ArmorBase("pale_crystal_chestplate", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.CHEST, Main.itemsTab);
	public static final Item PALE_CRYSTAL_LEGGINGS = new ArmorBase("pale_crystal_leggings", ARMOR_MATERIAL_PALE_CRYSTAL, 2, EntityEquipmentSlot.LEGS, Main.itemsTab);
	public static final Item PALE_CRYSTAL_BOOTS = new ArmorBase("pale_crystal_boots", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.FEET, Main.itemsTab);

	
}

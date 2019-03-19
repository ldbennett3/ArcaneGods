package com.loganb.arcanegods.init;

import java.util.ArrayList;
import java.util.List;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.items.FuelItemBase;
import com.loganb.arcanegods.items.ItemBase;
import com.loganb.arcanegods.items.armor.ArmorBase;
import com.loganb.arcanegods.items.books.CustomBookBase;
import com.loganb.arcanegods.items.guis.GuiCustomBookScreen.*;
import com.loganb.arcanegods.items.books.TranslationTomeBase;
import com.loganb.arcanegods.items.books.UntranslatedBookBase;
import com.loganb.arcanegods.items.tools.SwordBase;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
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
	
	// Untranslated default books
	public static final Item UNTRANSLATED_CASMODIAN = new UntranslatedBookBase("untranslated_casmodian", Main.itemsTab, Reference.BOOK_LANGUAGE.CASMODIAN);
	public static final Item UNTRANSLATED_DRACONIC = new UntranslatedBookBase("untranslated_draconic", Main.itemsTab, Reference.BOOK_LANGUAGE.DRACONIC);
	public static final Item UNTRANSLATED_ELVISH = new UntranslatedBookBase("untranslated_elvish", Main.itemsTab, Reference.BOOK_LANGUAGE.ELVISH);
	public static final Item UNTRANSLATED_ORCISH = new UntranslatedBookBase("untranslated_orcish", Main.itemsTab, Reference.BOOK_LANGUAGE.ORCISH);
	public static final Item UNTRANSLATED_PORALIAN = new UntranslatedBookBase("untranslated_poralian", Main.itemsTab, Reference.BOOK_LANGUAGE.PORALIAN);
	
	// Translation Tomes
	public static final Item TRANSLATION_TOME_CASMODIAN = new TranslationTomeBase("translation_tome_casmodian", Main.itemsTab, Reference.BOOK_LANGUAGE.CASMODIAN);
	public static final Item TRANSLATION_TOME_DRACONIC = new TranslationTomeBase("translation_tome_draconic", Main.itemsTab, Reference.BOOK_LANGUAGE.DRACONIC);
	public static final Item TRANSLATION_TOME_ELVISH = new TranslationTomeBase("translation_tome_elvish", Main.itemsTab, Reference.BOOK_LANGUAGE.ELVISH);
	public static final Item TRANSLATION_TOME_ORCISH = new TranslationTomeBase("translation_tome_orcish", Main.itemsTab, Reference.BOOK_LANGUAGE.ORCISH);
	public static final Item TRANSLATION_TOME_PORALIAN = new TranslationTomeBase("translation_tome_poralian", Main.itemsTab, Reference.BOOK_LANGUAGE.PORALIAN);
	
	// Tools
	public static final ItemSword PALE_CRYSTAL_SWORD = new SwordBase("pale_crystal_sword", MATERIAL_PALE_CRYSTAL, Main.itemsTab);
	
	// Armor
	// RenderIndexID (3rd param) is always 1 except for legs, which are 2
	public static final Item PALE_CRYSTAL_HELMET = new ArmorBase("pale_crystal_helmet", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.HEAD, Main.itemsTab);
	public static final Item PALE_CRYSTAL_CHESTPLATE = new ArmorBase("pale_crystal_chestplate", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.CHEST, Main.itemsTab);
	public static final Item PALE_CRYSTAL_LEGGINGS = new ArmorBase("pale_crystal_leggings", ARMOR_MATERIAL_PALE_CRYSTAL, 2, EntityEquipmentSlot.LEGS, Main.itemsTab);
	public static final Item PALE_CRYSTAL_BOOTS = new ArmorBase("pale_crystal_boots", ARMOR_MATERIAL_PALE_CRYSTAL, 1, EntityEquipmentSlot.FEET, Main.itemsTab);
	
	// Translated Books with content
	private static final String PHOTO_LOC = Reference.MOD_ID + ":textures/gui/book_pictures/";
	
	// Test book, photos are defined first then the actual book to keep it clean.
	private static ResourceLocation madnessPhoto = new ResourceLocation(PHOTO_LOC + "madness.png");
	public static final Item TEST_BOOK = new CustomBookBase("test_book", Main.itemsTab, Reference.BOOK_LANGUAGE.CASMODIAN, 
			new String[] {
					"This is a tooltip.", 
					TextFormatting.BLUE + "They can be colored too."
			}, 
			new Page[] { 
					new Page("Left page text is here. You can add as much as you want.", "Right page text is here, it can also be as long as you want."),
					new Page("Woah a second page. Fancy.", "This is the test book, I can literally just put anything I want in it.")
			}, 
			new DisplayImage[] { 
					new DisplayImage(madnessPhoto, -100, 100, 0, 0, 0, 0, 0) 
			}
	) ;

	
}

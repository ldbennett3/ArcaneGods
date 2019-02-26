package com.loganb.arcanegods.blocks.tileentities;

import java.util.ArrayList;
import java.util.List;

import com.loganb.arcanegods.blocks.customrecipes.GrinderRecipes;
import com.loganb.arcanegods.blocks.devices.Grinder;
import com.loganb.arcanegods.items.books.TranslationTomeBase;
import com.loganb.arcanegods.items.books.UntranslatedBookBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityTranslationTable extends TileEntity implements IInventory, ITickable {
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;
	
	public static final int INPUT_1 = 0, INPUT_2 = 1, OUTPUT = 2;

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "arcanegods.container.translation_table";
	}
	
	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	/**
	 * Get the amount of slots that are in the inventory
	 */
	@Override
	public int getSizeInventory() {
		return this.inventory.size();
	}
	
	/**
	 * Is there anything in any of the inventory slots
	 */
	@Override
	public boolean isEmpty() {
		for (ItemStack stack : inventory) {
			if (!stack.isEmpty()) return false;
		}
		return true;
	}
	
	/**
	 * Get the stack of items in a slot
	 * @param index the slot index to lookup
	 */
	@Override
	public ItemStack getStackInSlot(int index) {
		return (ItemStack)this.inventory.get(index);
	}
	
	/**
	 * Decrease the stack by a given amount
	 * @param index the stack to modify
	 * @param count the number of items to remove from the stack
	 */
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(inventory, index, count);
	}
	
	/**
	 * Remove an entire stack from a slot
	 * @param index the inventory slot to remove from
	 */
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(inventory, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack indexStack = (ItemStack)this.inventory.get(index); 
		boolean flag = !stack.isEmpty() && stack.isItemEqual(indexStack) && ItemStack.areItemStackTagsEqual(stack, indexStack);
		this.inventory.set(index, stack);
		
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		
		if (index == 0 && !flag) {
			this.markDirty();
		}
	}
	
	// REMEMBER PROCESSES HAPPENING WHEN RE-ENTERING THE 
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		
		if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	
	// STORE PROCESSES HAPPENING WHEN EXITING THE FURNACE
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if (this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public void update() {
		boolean flag1 = false;
		
		if(!this.world.isRemote) {
			ItemStack input1 = (ItemStack)this.inventory.get(INPUT_1);
			ItemStack input2 = (ItemStack)this.inventory.get(INPUT_2);
			ItemStack output = (ItemStack)this.inventory.get(OUTPUT);
			
			if(!input1.isEmpty() || !input2.isEmpty() || !output.isEmpty()) {
				flag1 = true;
			}
			
		}
		
		if (flag1) {
			this.markDirty();
		}
	}
	
	public static boolean isItemTome(ItemStack stack) {
		return stack.getItem() instanceof TranslationTomeBase;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, 
				(double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {}
	
	@Override
	public void closeInventory(EntityPlayer player) {}
	
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == OUTPUT) {
			return false;
		} else if (index == INPUT_1) {
			return stack.getItem() instanceof UntranslatedBookBase;
		} else {
			return stack.getItem() instanceof TranslationTomeBase;
 		}
	}
	
	public String getGuiID() {
		return "arcanegods:translation_table";
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void setField(int id, int value) {
		
	}
	
	@Override
	public int getFieldCount() {
		return 3;
	}
	
	@Override
	public void clear() {
		this.inventory.clear();
	}
}
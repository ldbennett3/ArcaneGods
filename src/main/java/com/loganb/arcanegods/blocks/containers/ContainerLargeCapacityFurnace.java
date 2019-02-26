package com.loganb.arcanegods.blocks.containers;

import com.loganb.arcanegods.blocks.customrecipes.LargeCapacityFurnaceRecipes;
import com.loganb.arcanegods.blocks.slots.SlotLargeCapacityFurnaceFuel;
import com.loganb.arcanegods.blocks.slots.SlotLargeCapacityFurnaceOutput;
import com.loganb.arcanegods.blocks.tileentities.TileEntityLargeCapacityFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerLargeCapacityFurnace extends Container {
	private final TileEntityLargeCapacityFurnace tileEntity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	
	public ContainerLargeCapacityFurnace(InventoryPlayer player, TileEntityLargeCapacityFurnace tileEntity) {
		this.tileEntity = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, TileEntityLargeCapacityFurnace.INPUT_1, 41, 12));
		this.addSlotToContainer(new Slot(tileEntity, TileEntityLargeCapacityFurnace.INPUT_2, 63, 12));
		this.addSlotToContainer(new SlotLargeCapacityFurnaceFuel(tileEntity, TileEntityLargeCapacityFurnace.FUEL, 52, 56));
		this.addSlotToContainer(new SlotLargeCapacityFurnaceOutput(player.player, tileEntity, TileEntityLargeCapacityFurnace.OUTPUT, 126, 35));
		
		// Get the player's inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}
		
		// Player's hotbar
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileEntity);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if (this.cookTime != this.tileEntity.getField(TileEntityLargeCapacityFurnace.FUEL)) 
				listener.sendWindowProperty(this, TileEntityLargeCapacityFurnace.FUEL, this.tileEntity.getField(TileEntityLargeCapacityFurnace.FUEL));
			
			if (this.burnTime != this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_1)) 
				listener.sendWindowProperty(this, TileEntityLargeCapacityFurnace.INPUT_1, this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_1));
			
			if (this.currentBurnTime != this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_2)) 
				listener.sendWindowProperty(this, TileEntityLargeCapacityFurnace.INPUT_2, this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_2));
			if (this.totalCookTime != this.tileEntity.getField(TileEntityLargeCapacityFurnace.OUTPUT)) 
				listener.sendWindowProperty(this, TileEntityLargeCapacityFurnace.OUTPUT, this.tileEntity.getField(TileEntityLargeCapacityFurnace.OUTPUT));
		}
		
		this.cookTime = this.tileEntity.getField(TileEntityLargeCapacityFurnace.FUEL);
		this.burnTime = this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_1);
		this.currentBurnTime = this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_2);
		this.totalCookTime = this.tileEntity.getField(TileEntityLargeCapacityFurnace.OUTPUT);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileEntity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileEntity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == TileEntityLargeCapacityFurnace.OUTPUT)
            {
                if (!this.mergeItemStack(itemstack1, 4, 40, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != TileEntityLargeCapacityFurnace.INPUT_1 && index != TileEntityLargeCapacityFurnace.INPUT_2 && index != TileEntityLargeCapacityFurnace.FUEL) {
                
            	// Fuel comes first because some fuels like coal are ingredients
                if (TileEntityLargeCapacityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, TileEntityLargeCapacityFurnace.FUEL, TileEntityLargeCapacityFurnace.OUTPUT, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityLargeCapacityFurnace.isItemIngredient(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, TileEntityLargeCapacityFurnace.INPUT_1, TileEntityLargeCapacityFurnace.FUEL, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 4  && index < 31) 
                {
					if (!this.mergeItemStack(itemstack1, 31, 40, false)) return ItemStack.EMPTY;
				}
				else if (index >= 31 && index < 40 && !this.mergeItemStack(itemstack1, 4, 31, false))
				{
					return ItemStack.EMPTY;
				}
                
            } 
            else if (!this.mergeItemStack(itemstack1, 4, 40, false)) 
            {
                return ItemStack.EMPTY;
            } 
            
            if (itemstack1.isEmpty()) 
            {
                slot.putStack(ItemStack.EMPTY);
            } 
            else 
            {
                slot.onSlotChanged();
            }
            
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
	}

}
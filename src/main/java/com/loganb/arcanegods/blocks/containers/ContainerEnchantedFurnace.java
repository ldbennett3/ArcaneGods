package com.loganb.arcanegods.blocks.containers;

import com.loganb.arcanegods.blocks.tileentities.TileEntityEnchantedFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerEnchantedFurnace extends Container {
	private final TileEntityEnchantedFurnace tileEntity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	
	public ContainerEnchantedFurnace(InventoryPlayer player, TileEntityEnchantedFurnace tileEntity) {
		this.tileEntity = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, TileEntityEnchantedFurnace.INPUT_1, 56, 17));
		this.addSlotToContainer(new SlotFurnaceFuel(tileEntity, TileEntityEnchantedFurnace.FUEL, 56, 53));
		this.addSlotToContainer(new SlotFurnaceOutput(player.player, tileEntity, TileEntityEnchantedFurnace.OUTPUT, 116, 35));
		
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
			
			if (this.cookTime != this.tileEntity.getField(TileEntityEnchantedFurnace.FUEL)) 
				listener.sendWindowProperty(this, TileEntityEnchantedFurnace.FUEL, this.tileEntity.getField(TileEntityEnchantedFurnace.FUEL));
			
			if (this.burnTime != this.tileEntity.getField(TileEntityEnchantedFurnace.INPUT_1)) 
				listener.sendWindowProperty(this, TileEntityEnchantedFurnace.INPUT_1, this.tileEntity.getField(TileEntityEnchantedFurnace.INPUT_1));
			
			if (this.currentBurnTime != this.tileEntity.getField(TileEntityEnchantedFurnace.INPUT_1)) 
				listener.sendWindowProperty(this, TileEntityEnchantedFurnace.INPUT_1, this.tileEntity.getField(TileEntityEnchantedFurnace.INPUT_1));
			if (this.totalCookTime != this.tileEntity.getField(TileEntityEnchantedFurnace.OUTPUT)) 
				listener.sendWindowProperty(this, TileEntityEnchantedFurnace.OUTPUT, this.tileEntity.getField(TileEntityEnchantedFurnace.OUTPUT));
		}
		
		this.cookTime = this.tileEntity.getField(TileEntityEnchantedFurnace.FUEL);
		this.burnTime = this.tileEntity.getField(TileEntityEnchantedFurnace.INPUT_1);
		this.currentBurnTime = this.tileEntity.getField(TileEntityEnchantedFurnace.INPUT_1);
		this.totalCookTime = this.tileEntity.getField(TileEntityEnchantedFurnace.OUTPUT);
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
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!FurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
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

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

}
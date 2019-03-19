package com.loganb.arcanegods.blocks.containers;

import com.loganb.arcanegods.blocks.customrecipes.TranslationTableRecipes;
import com.loganb.arcanegods.blocks.slots.SlotTranslationTableBookInput;
import com.loganb.arcanegods.blocks.slots.SlotTranslationTableOutput;
import com.loganb.arcanegods.blocks.slots.SlotTranslationTableTomeInput;
import com.loganb.arcanegods.blocks.tileentities.TileEntityTranslationTable;
import com.loganb.arcanegods.items.books.TranslationTomeBase;
import com.loganb.arcanegods.items.books.UntranslatedBookBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerTranslationTable extends Container {
	private final TileEntityTranslationTable tileEntity;
	
	public ContainerTranslationTable(InventoryPlayer player, TileEntityTranslationTable tileEntity2) {
		this.tileEntity = tileEntity2;
		this.addSlotToContainer(new SlotTranslationTableBookInput(tileEntity2, TileEntityTranslationTable.INPUT_1, 24, 34));
		this.addSlotToContainer(new SlotTranslationTableTomeInput(tileEntity2, TileEntityTranslationTable.INPUT_2, 58, 34));
		this.addSlotToContainer(new SlotTranslationTableOutput(player.player, tileEntity2, TileEntityTranslationTable.OUTPUT, 126, 35));
		
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

            if (index == TileEntityTranslationTable.OUTPUT)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != TileEntityTranslationTable.INPUT_1 && index != TileEntityTranslationTable.INPUT_2)
            {
                if (itemstack1.getItem() instanceof UntranslatedBookBase)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (itemstack1.getItem() instanceof TranslationTomeBase)
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
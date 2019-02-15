package com.loganb.arcanegods.blocks.tileentities;

import com.loganb.arcanegods.blocks.devices.Grinder;

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

public class TileEntityGrinder extends TileEntity implements IInventory, ITickable {
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	
	public static final int INPUT_1 = 0, FUEL = 1, OUTPUT = 2;
	
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime;
	
	private final int cookingSpeed = 100;
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "arcanegods.container.grinder";
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
		ItemStack itemStack = (ItemStack)this.inventory.get(index); 
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && ItemStack.areItemStackTagsEqual(stack, itemStack);
		this.inventory.set(index, stack);
		
		if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if (index == 0 && index + 1 == 1 && !flag) {
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}
	
	// REMEMBER PROCESSES HAPPENING WHEN RE-ENTERING THE FURNACE
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("TotalCookTime");
		this.currentBurnTime = getItemBurnTime((ItemStack)this.inventory.get(FUEL));
		
		if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	
	// STORE PROCESSES HAPPENING WHEN EXITING THE FURNACE
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("TotalCookTime", (short)this.totalCookTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if (this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		return inventory.getField(INPUT_1) > 0;
	}
	
	@Override
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		if (this.isBurning()) --this.burnTime;
		
		if (!this.world.isRemote) {
			ItemStack fuel = (ItemStack)this.inventory.get(FUEL);
			
			// Consume Fuel
			if (this.isBurning() || !fuel.isEmpty() &&  !((ItemStack)this.inventory.get(INPUT_1)).isEmpty()) {
				if (!this.isBurning() && this.canAlloy()) {
					this.burnTime = getItemBurnTime(fuel);
					this.currentBurnTime = this.burnTime;
					
					if (this.isBurning()) {
						flag1 = true;
						
						if (!fuel.isEmpty()) {
							Item item = fuel.getItem();
							fuel.shrink(1);
							
							if (fuel.isEmpty()) {
								ItemStack item1 = item.getContainerItem(fuel);
								this.inventory.set(FUEL, item1);
							}
						}
					}
				}
				
				// Alloying process
				if (this.isBurning() && this.canAlloy()) {
					++this.cookTime;
					
					if (this.cookTime == this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime((ItemStack)this.inventory.get(INPUT_1));
						this.alloyItem();
						flag1 = true;
					}
				}
				else this.cookTime = 0;
			}
			else if (!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			}
			if (flag != this.isBurning()) {
				flag1 = true;
				Grinder.setState(this.isBurning(), this.world, this.pos);
			}
		}
		if (flag1)
			this.markDirty();
	}
	
	public int getCookTime(ItemStack input1) {
		// Decreasing the value will increase the speed
		// Increasing the value will decrease the speed
		return cookingSpeed;
	}
	
	private boolean canAlloy() {
		if (((ItemStack)this.inventory.get(INPUT_1)).isEmpty()) return false;
		else {
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult((ItemStack)this.inventory.get(INPUT_1));
			if (result.isEmpty()) return false;
			else {
				ItemStack output = (ItemStack)this.inventory.get(OUTPUT);
				if (output.isEmpty()) return true;
				if (!output.isItemEqual(result)) return false;
				int resultCount = output.getCount() + result.getCount();
				return resultCount <= getInventoryStackLimit() && resultCount <= output.getMaxStackSize();
			}
		}
	}
	
	public void alloyItem() {
		if (this.canAlloy()) {
			ItemStack input1 = (ItemStack)this.inventory.get(INPUT_1);
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult((ItemStack)this.inventory.get(INPUT_1));
			ItemStack output = (ItemStack)this.inventory.get(OUTPUT);
			
			if (output.isEmpty()) this.inventory.set(OUTPUT, result.copy());
			else if (output.getItem() == result.getItem()) output.grow(result.getCount());
			
			// TODO: Maybe shrink the inputs by an amount determined by the recipe
			input1.shrink(1);
		}
	}
	
	public static int getItemBurnTime(ItemStack stack) {
		if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
            {
                return 150;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOOL))
            {
                return 100;
            }
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
            {
                return 67;
            }
            else if (item == Item.getItemFromBlock(Blocks.LADDER))
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
            {
                return 100;
            }
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
            {
                return 16000;
            }
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
            {
                return 200;
            }
            else if (item == Items.STICK)
            {
                return 100;
            }
            else if (item != Items.BOW && item != Items.FISHING_ROD)
            {
                if (item == Items.SIGN)
                {
                    return 200;
                }
                else if (item == Items.COAL)
                {
                    return 1600;
                }
                else if (item == Items.LAVA_BUCKET)
                {
                    return 20000;
                }
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if (item == Items.BLAZE_ROD)
                    {
                        return 2400;
                    }
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
                    {
                        return 200;
                    }
                    else
                    {
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else
                {
                    return 100;
                }
            }
            else
            {
                return 300;
            }
        }
	}
	
	public static boolean isItemFuel(ItemStack fuel) {
		return getItemBurnTime(fuel) > 0;
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
		if (index == OUTPUT) return false;
		else if (index != FUEL) return true;
		else {
			return isItemFuel(stack);
		}
	}
	
	public String getGuiID() {
		return "arcanegods:brick_furnace";
	}
	
	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}
	
	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
			break;
		}
	}
	
	@Override
	public int getFieldCount() {
		return 4;
	}
	
	@Override
	public void clear() {
		this.inventory.clear();
	}
}
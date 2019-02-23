package com.loganb.arcanegods.items;

import java.util.List;

import javax.annotation.Nullable;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.util.IHasModel;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCustomBook extends ItemBase implements IHasModel {

	private boolean translated = false;
	
	public ItemCustomBook(String name, CreativeTabs tab) {
		super(name, tab);	
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
	}
	
	public void setTranslated(boolean b) {
		this.translated = b;
	}
	
	public boolean getTranslated() {
		return this.translated;
	}
	
	 @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		tooltip.add(TextFormatting.GRAY + "This is a test tooltip for a book");
    }
	
	/**
     * Called when the equipped item is right clicked.
     */
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (worldIn.isRemote)
        {
            playerIn.sendMessage(new TextComponentString("This book has not been translated yet!"));
            playerIn.openGui((Object)Main.instance, Reference.GUI_CUSTOM_BOOK, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
}

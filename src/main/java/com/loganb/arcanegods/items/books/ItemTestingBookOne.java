package com.loganb.arcanegods.items.books;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.items.ItemBase;
import com.loganb.arcanegods.items.guis.GuiCustomBookScreen;
import com.loganb.arcanegods.util.IHasModel;
import com.loganb.arcanegods.util.Reference;

import it.unimi.dsi.fastutil.Arrays;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTestingBookOne extends ItemBase implements IHasModel {

	private boolean translated = true;
	private int GuiReference = Reference.GUI_TESTING_BOOK_1;
	
	private String notTranslatedText = "This book has not been translated yet!";
	private String[] toolTips = {TextFormatting.GRAY + "This is a test tooltip for a book"};
	private static GuiCustomBookScreen.Page[] pages;
	private static GuiCustomBookScreen.DisplayImage[] images;
	
	public ItemTestingBookOne(String name, CreativeTabs tab) {
		super(name, tab);	
		setPages();
		setImages();
	}
	
	private void setPages() {
		pages = new GuiCustomBookScreen.Page[]{
			new GuiCustomBookScreen.Page("Testing for the left side of page 1", "Testing for the right side of page 1"),
			new GuiCustomBookScreen.Page("Testing for the left side of page 2", "Testing for the right side of page 2"),
			new GuiCustomBookScreen.Page("This is a testing page for the left side of the book. Its longer and used to demonstrate what a full book page" + 
			"might look like if done to completion.", "I wonder what Im going to do to fill all these books. Maybe copy some texts from skyrim to make a library?" + 
			" Gotta give people something to read afterall right?"),
			new GuiCustomBookScreen.Page("Yet another testing bit", "I know these pages are gross and empty. At least its not lorem ispum right?")
		};
	}
	
	private void setImages() {
		String rL = Reference.MOD_ID + ":textures/gui/book_pictures/";
		int leftPageStart = -111; // Good starting pos for pics. More negative = more left
		int rightPageStart = 10; // Good starting pos for pics. More positive = more right
		
		ResourceLocation pageOneMountainSide = new ResourceLocation(rL + "madness.png");
		
		images = new GuiCustomBookScreen.DisplayImage[] {
			new GuiCustomBookScreen.DisplayImage(pageOneMountainSide, leftPageStart, 100, 0, 0, 125 - 20, 45, 0)
		};
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
	
	public static GuiCustomBookScreen.Page[] getPages() {
		return pages;
	}
	
	public static GuiCustomBookScreen.DisplayImage[] getImages() {
		return images;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		for(String s : toolTips) {
			tooltip.add(s);
		}
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
        	if(!translated) {
        		playerIn.sendMessage(new TextComponentString(notTranslatedText));
        	} else {
	            playerIn.openGui((Object)Main.instance, GuiReference, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
        	}
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
}

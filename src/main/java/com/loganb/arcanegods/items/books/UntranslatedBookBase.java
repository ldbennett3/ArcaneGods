package com.loganb.arcanegods.items.books;

import java.util.List;

import javax.annotation.Nullable;

import com.loganb.arcanegods.Main;
import com.loganb.arcanegods.items.ItemBase;
import com.loganb.arcanegods.items.guis.GuiCustomBookScreen;
import com.loganb.arcanegods.util.IHasModel;
import com.loganb.arcanegods.util.Reference;

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

public class UntranslatedBookBase extends ItemBase implements IHasModel {

	private static GuiCustomBookScreen.Page[] pages;
	private static GuiCustomBookScreen.DisplayImage[] images;
	private static Reference.BOOK_LANGUAGE language;
	
	// Book information
	private int GuiReference = Reference.GUI_TESTING_BOOK_1;
	
	private String messageText = "View this volume in your inventory for more information.";
	
	private String[] toolTips;

	/**
	 * Constructor to define a custom book
	 * @param name Unlocalized book name.
	 * @param tab Creative tab to be placed in
	 * @param language Language the book is in
	 */
	public UntranslatedBookBase(String name, CreativeTabs tab, Reference.BOOK_LANGUAGE language) {
		super(name, tab);	
		setPages();
		setImages();
		this.language = language;
		
		// Default tooltips. Shows when untranslated.
		toolTips = new String[]{ 
			TextFormatting.BOLD + "The book appears to be written in an unknown language.",
			"The lettering appears " + language.letterDescription + "."
		};
		
	}
	
	protected void setPages() {
		pages = new GuiCustomBookScreen.Page[]{};
	}
	
	private void setImages() {
		String rL = Reference.MOD_ID + ":textures/gui/book_pictures/";
		int leftPageStart = -111; // Good starting pos for pics. More negative = more left
		int rightPageStart = 10; // Good starting pos for pics. More positive = more right
		
		//ResourceLocation pageOneMountainSide = new ResourceLocation(rL + "madness.png");
		
		images = new GuiCustomBookScreen.DisplayImage[] {
			//new GuiCustomBookScreen.DisplayImage(pageOneMountainSide, leftPageStart, 100, 0, 0, 125 - 20, 45, 0)
		};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return false;
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
        	//playerIn.openGui((Object)Main.instance, GuiReference, worldIn, (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
        	playerIn.sendMessage(new TextComponentString(messageText));
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
}

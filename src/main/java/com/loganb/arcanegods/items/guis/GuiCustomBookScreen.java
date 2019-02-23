package com.loganb.arcanegods.items.guis;

import com.loganb.arcanegods.util.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiCustomBookScreen extends GuiScreen {

	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/book.png");
	private int bookTotalPages = 1;
	private int bookSize = 250;
	private ItemStack book;
	private EntityPlayer player;
	private int currentPage = 0;
	
	private GuiButton nextPage, previousPage, close;
	
	private String leftText = "";
	private String rightText = "";
	
	
	public GuiCustomBookScreen(EntityPlayer player, ItemStack book) {
        this.player = player;
        this.book = book;
	}
		
	public String getLeftText() {
		return leftText;
	}

	public void setLeftText(String leftText) {
		this.leftText = leftText;
	}

	public String getRightText() {
		return rightText;
	}

	public void setRightText(String rightText) {
		this.rightText = rightText;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int i = (this.width / 2) - (bookSize / 2);
        int j = 16;
        this.drawTexturedModalRect(i, j, 0, 0, bookSize, bookSize);
        
    	int leftPageTextStart = (this.width / 2) - (bookSize / 2) + 14;
    	int rightPageTextStart = (this.width / 2) + 10;
    	int topTextStart = 44;
    	
        this.fontRenderer.drawSplitString(leftText, leftPageTextStart, topTextStart, bookSize / 2 - 18, 0);
        this.fontRenderer.drawSplitString(rightText, rightPageTextStart, topTextStart, bookSize / 2 - 20, 0);
        
        
    
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
	
}

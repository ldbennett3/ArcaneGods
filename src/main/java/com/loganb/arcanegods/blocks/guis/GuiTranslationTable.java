package com.loganb.arcanegods.blocks.guis;

import com.loganb.arcanegods.blocks.containers.ContainerTranslationTable;
import com.loganb.arcanegods.blocks.customrecipes.TranslationTableRecipes;
import com.loganb.arcanegods.blocks.tileentities.TileEntityTranslationTable;
import com.loganb.arcanegods.items.guis.GuiCustomBookScreen;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo.Color;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiTranslationTable extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/translation_table.png");
	private final InventoryPlayer player;
	private final TileEntityTranslationTable tileEntity;
	private TranslateButton translateButton;
	
	public GuiTranslationTable(InventoryPlayer player, TileEntityTranslationTable tileEntity) {
		super(new ContainerTranslationTable(player, tileEntity));
		this.player = player;
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileEntity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
		this.addButton(translateButton = new TranslateButton(0, 0, 0));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@SideOnly(Side.CLIENT)
    public class TranslateButton extends GuiButton {

	    public TranslateButton(int buttonId, int x, int y)
	    {
	        super(buttonId, x, y, 23, 13, "");
	    }
	
	    /**
	     * Draws this button to the screen.
	     */
	    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	    {
	    	boolean active = false;
	    	
	        if (this.visible)
	        {
	        	active = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            mc.getTextureManager().bindTexture(GuiTranslationTable.TEXTURES);
	
	            if(active) {
	                this.drawTexturedModalRect(this.x, this.y, 0, 200, 16, 16);
	                this.drawString(mc.fontRenderer, "BUTTON", this.x, this.y, 0);
	            } else {
	                this.drawTexturedModalRect(this.x, this.y, 0, 250, 16, 16);
	            }
	        }
	    }
	    
	    @Override
	    public void mouseReleased(int mouseX, int mouseY) {
	    	
	    	boolean clicked = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	    	
	    	tileEntity.translateContents();
	    	
	    	super.mouseReleased(mouseX, mouseY);
	    }
	    
	}
}

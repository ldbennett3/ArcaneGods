package com.loganb.arcanegods.blocks.guis;

import com.loganb.arcanegods.blocks.containers.ContainerLargeCapacityFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityLargeCapacityFurnace;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiLargeCapacityFurnace extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/large_capacity_furnace.png");
	private final InventoryPlayer player;
	private final TileEntityLargeCapacityFurnace tileEntity;
	
	public GuiLargeCapacityFurnace(InventoryPlayer player, TileEntityLargeCapacityFurnace tileEntity) {
		super(new ContainerLargeCapacityFurnace(player, tileEntity));
		
		this.player = player;
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//String tileName = this.tileEntity.getDisplayName().getUnformattedText();
		String tileNameFirst = "Large Capacity";
		String tileNameSecond = "Furnace";
		this.fontRenderer.drawString(tileNameFirst, (this.xSize - this.fontRenderer.getStringWidth(tileNameFirst))  - 8, 8, 4210752);
		this.fontRenderer.drawString(tileNameSecond, (this.xSize - this.fontRenderer.getStringWidth(tileNameSecond)) - 8, 17, 4210752);
		// Inventory text
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 118, this.ySize - 96 + 2, 4210752);
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
		
		if (TileEntityLargeCapacityFurnace.isBurning(tileEntity)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 52, this.guiTop + 35 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 84, this.guiTop + 35, 176, 14, l + 1, 16);
	}
	
	private int getBurnLeftScaled(int pixels) {
		int i = this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_2);
		if (i == 0) i = 200;
		
		return this.tileEntity.getField(TileEntityLargeCapacityFurnace.INPUT_1) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.tileEntity.getField(TileEntityLargeCapacityFurnace.FUEL), j = this.tileEntity.getField(TileEntityLargeCapacityFurnace.OUTPUT);
		
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}

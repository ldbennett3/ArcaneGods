package com.loganb.arcanegods.blocks.guis;

import com.loganb.arcanegods.blocks.containers.ContainerBrickFurnace;
import com.loganb.arcanegods.blocks.tileentities.TileEntityBrickFurnace;
import com.loganb.arcanegods.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiBrickFurnace extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/brick_furnace.png");
	private final InventoryPlayer player;
	private final TileEntityBrickFurnace tileEntity;
	
	public GuiBrickFurnace(InventoryPlayer player, TileEntityBrickFurnace tileEntity) {
		super(new ContainerBrickFurnace(player, tileEntity));
		this.player = player;
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileEntity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityBrickFurnace.isBurning(tileEntity)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		
		int l = this.getCookProgressScaled(22);
		this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 14, l + 1, 16);
		
	}
	
	private int getBurnLeftScaled(int pixels) {
		int i = this.tileEntity.getField(1);
		if(i == 0) i = 200;
		return this.tileEntity.getField(0) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.tileEntity.getField(2);
		int j = this.tileEntity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}

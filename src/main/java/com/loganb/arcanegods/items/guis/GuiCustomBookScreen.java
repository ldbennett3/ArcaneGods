package com.loganb.arcanegods.items.guis;

import java.io.IOException;
import java.util.ArrayList;

import com.loganb.arcanegods.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiCustomBookScreen extends GuiScreen {

	// Resource locations
	public static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/book.png");
	private int bookTextureSize = 250;
	
	// Book information
	private Page[] pages;
	private DisplayImage[] images;
	private int currentPage;
	
	private EntityPlayer player;
	
	// Buttons
	private GuiCustomBookScreen.CloseButton closeButton;
	private GuiCustomBookScreen.NextPageButton nextPageButton, previousPageButton;
	
	// Screen values
	public int screenWidth = this.width / 2;
	public int screenHeight = this.height / 2;
	
	/**
	 * Constructor to create the GUI for a custom book 
	 * @param player The player who created the GUI
	 * @param totalPages The number pages the book will contain
	 */
	public GuiCustomBookScreen(EntityPlayer player, Page[] pages, DisplayImage[] images) {
		this.currentPage = 0;
		this.pages = pages;
		this.images = images;
		this.player = player;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
		
		this.buttonList.clear();
		
		// Draw the book background
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int posX = (this.width / 2) - (bookTextureSize / 2);
        int posY = 16;
        this.drawTexturedModalRect(posX, posY, 0, 0, bookTextureSize, 376 / 2);
        
        // Positioning for text
    	int leftPageTextStart = (this.width / 2) - (bookTextureSize / 2) + 18;
    	int rightPageTextStart = (this.width / 2) + 14;
    	int topTextStart = 44;
    	
        this.fontRenderer.drawSplitString(pages[currentPage].getLeftText(), leftPageTextStart, topTextStart, bookTextureSize / 2 - 24, 0);
        this.fontRenderer.drawSplitString(pages[currentPage].getRightText(), rightPageTextStart, topTextStart, bookTextureSize / 2 - 28, 0);
        
        // Positioning for buttons
    	int closeButtonX = (this.width / 2) + (bookTextureSize / 2) - 32;
    	int closeButtonY = 28;
    	
        this.closeButton = this.addButton(new CloseButton(0, closeButtonX, closeButtonY));
        
        int previousButtonX = (this.width / 2) - (bookTextureSize / 2) + 12;
        int nextButtonX     = (this.width / 2) + (bookTextureSize / 2) - 34;
        int buttonY = 167;
        
        this.previousPageButton = this.addButton(new NextPageButton(1, previousButtonX, buttonY, true));
        this.nextPageButton = this.addButton(new NextPageButton(2, nextButtonX, buttonY, false));
        
        if(currentPage >= pages.length - 1) {
        	nextPageButton.visible = false;
        }
        if(currentPage <= 0) {
        	previousPageButton.visible = false;
        }
    
        // Draw all the images
        drawImages();
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
	
	public void updateBook() {
		
		this.buttonList.clear();
		
		System.out.println("\n\nSHowing screen for page " + currentPage);
		
		// Draw the book background
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
        int posX = (this.width / 2) - (bookTextureSize / 2);
        int posY = 16;
        this.drawTexturedModalRect(posX, posY, 0, 0, bookTextureSize, 376 / 2);
        
        // Positioning for text
    	int leftPageTextStart = (this.width / 2) - (bookTextureSize / 2) + 18;
    	int rightPageTextStart = (this.width / 2) + 14;
    	int topTextStart = 44;
    	
        this.fontRenderer.drawSplitString(pages[currentPage].getLeftText(), leftPageTextStart, topTextStart, bookTextureSize / 2 - 24, 0);
        this.fontRenderer.drawSplitString(pages[currentPage].getRightText(), rightPageTextStart, topTextStart, bookTextureSize / 2 - 28, 0);
        
        // Positioning for buttons
    	int closeButtonX = (this.width / 2) + (bookTextureSize / 2) - 32;
    	int closeButtonY = 28;
    	
        this.closeButton = this.addButton(new CloseButton(0, closeButtonX, closeButtonY));
        
        int previousButtonX = (this.width / 2) - (bookTextureSize / 2) + 12;
        int nextButtonX     = (this.width / 2) + (bookTextureSize / 2) - 34;
        int buttonY = 167;
        
        this.previousPageButton = this.addButton(new NextPageButton(1, previousButtonX, buttonY, true));
        this.nextPageButton = this.addButton(new NextPageButton(2, nextButtonX, buttonY, false));
        
        if(currentPage >= pages.length - 1) {
        	nextPageButton.visible = false;
        }
        if(currentPage <= 0) {
        	previousPageButton.visible = false;
        }
    
        // Draw all the images
        drawImages();
	}
	
	public void nextPage() {
		if(currentPage < pages.length - 1) {
			currentPage++;
			updateBook();
		}
	}
	
	public void previousPage() {
		if(currentPage > 0) {
			currentPage--;
			updateBook();
		}
	}
	
	/**
	 * Draws all images from the images array. Images are page dependent as defined in the object class
	 */
	public void drawImages() {
		for(DisplayImage i : images) {
			if(currentPage == i.page) {
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(i.texture);

                this.drawTexturedModalRect((this.width / 2) + i.posX, i.posY, i.texturePosX, i.texturePosY, i.textureSizeWidth, i.textureSizeHeight);
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		if(button.enabled) {
			if(button.visible) {
				
				// Close button
				if(button.id == 0) {
					player.closeScreen();
				}
				if(button.id == 1) {
					previousPage();
				}
				if(button.id == 2) {
					nextPage();
				}
				
			}
		}
		
		super.actionPerformed(button);
	}

	@SideOnly(Side.CLIENT)
	public static class Page {
		
		private String leftText = "";
		private String rightText = "";
		
		public Page(String leftText, String rightText) {
			this.leftText = leftText;
			this.rightText = rightText;
		}
		
		public String getLeftText() {
			return this.leftText;
		}
		
		public String getRightText() {
			return this.rightText;
		}
		
		public void setLeftText(String s) {
			this.leftText = s;
		}
		
		public void setRightText(String s) {
			this.rightText = s;
		}
		
	}
	
	
	@SideOnly(Side.CLIENT)
	public static class DisplayImage {
		
		public ResourceLocation texture;
		public int posX;
		public int posY;
		public int texturePosX;
		public int texturePosY;
		public int textureSizeWidth;
		public int textureSizeHeight;
		public int page;
		
		/**
		 * Display object constructor. READ PARAMETERS
		 * @param texture Resource location of the texture for the image.
		 * @param posX X Position. This value is *added* to a default value of screen width / 2
		 * @param posY Y Position. This value is read as "down from the top of the screen"
		 * @param texturePosX X position of the texture in the photo its from
		 * @param texturePosY Y position of the texture in the photo its from
		 * @param textureSizeWidth Width of the texture
		 * @param textureSizeHeight Height of the texture
		 * @param page Page that this image will appear on 
		 */
		public DisplayImage(ResourceLocation texture, int posX, int posY, int texturePosX, int texturePosY, 
				int textureSizeWidth, int textureSizeHeight, int page) {
			this.texture = texture;
			this.posX = posX;
			this.posY = posY;
			this.texturePosX = texturePosX;
			this.texturePosY = texturePosY;
			this.textureSizeWidth = textureSizeWidth;
			this.textureSizeHeight = textureSizeHeight;
			this.page = page;
		}
		
	}
	
	@SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton {

		boolean isPreviousPageButton = false;
		
	    public NextPageButton(int buttonId, int x, int y, boolean isPreviousPageButton)
	    {
	        super(buttonId, x, y, 23, 13, "");
	        this.isPreviousPageButton = isPreviousPageButton;
	    }
	    
	    /**
	     * Draws this button to the screen.
	     */
	    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	    {
	    	int bookSize = 250;
	    	
	    	boolean active = false;
	    	
	        if (this.visible)
	        {
	        	active = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            mc.getTextureManager().bindTexture(GuiCustomBookScreen.BOOK_GUI_TEXTURES);
	
	            if(isPreviousPageButton) {
		            if(active) {
		                this.drawTexturedModalRect(this.x, this.y, 41 / 2, 410 / 2, 42 / 2, 32 / 2);
		            } else {
		                this.drawTexturedModalRect(this.x, this.y, 41 / 2, 376 / 2, 42 / 2, 32 / 2);
		            }
	            } else {
	            	if(active) {
		                this.drawTexturedModalRect(this.x, this.y, 85 / 2, 410 / 2, 42 / 2, 32 / 2);
		            } else {
		                this.drawTexturedModalRect(this.x, this.y, 85 / 2, 376 / 2, 42 / 2, 32 / 2);
		            }
	            }
	        }
	    }
	}
	
	@SideOnly(Side.CLIENT)
    static class CloseButton extends GuiButton {

	    public CloseButton(int buttonId, int x, int y)
	    {
	        super(buttonId, x, y, 23, 13, "");
	    }
	
	    /**
	     * Draws this button to the screen.
	     */
	    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	    {
	    	int bookSize = 250;
	    	
	    	boolean active = false;
	    	
	        if (this.visible)
	        {
	        	active = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            mc.getTextureManager().bindTexture(GuiCustomBookScreen.BOOK_GUI_TEXTURES);
	
	            if(active) {
	                this.drawTexturedModalRect(this.x, this.y, 0, 409 / 2, 16, 16);
	            } else {
	                this.drawTexturedModalRect(this.x, this.y, 0, 376 / 2, 16, 16);
	            }
	        }
	    }
	}
	
}

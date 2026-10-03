// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            GuiContainer, ContainerChest, IInventory, FontRenderer, 
//            RenderEngine

public class GuiPithos extends GuiContainer
{

    public GuiPithos(IInventory iinventory, IInventory iinventory1)
    {
        super(new ContainerPithos(iinventory, iinventory1));
        inventoryRows = 0;
        upperPithosInventory = iinventory;
        lowerPithosInventory = iinventory1;
        field_948_f = false;
        char c = '\336';
        int i = c - 108;
        inventoryRows = iinventory1.getSizeInventory() / 9;
        ySize = 131;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString(lowerPithosInventory.getInvName(), 8, 6, 0x404040);
        fontRenderer.drawString(upperPithosInventory.getInvName(), 8, (ySize - 96) + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f)
    {
        int i = mc.renderEngine.getTexture("/gui/pithos.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
    }

    private IInventory upperPithosInventory;
    private IInventory lowerPithosInventory;
    private int inventoryRows;
}

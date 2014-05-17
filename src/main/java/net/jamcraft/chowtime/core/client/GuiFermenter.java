package net.jamcraft.chowtime.core.client;

import net.jamcraft.chowtime.core.container.ContainterFermenter;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class GuiFermenter extends GuiContainer
{
    TEFermenter te;
    public GuiFermenter(InventoryPlayer playerInv, TEFermenter te)
    {
        super(new ContainterFermenter(playerInv,te));
        ySize=200;
        this.te=te;
    }

    @Override protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture();

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}

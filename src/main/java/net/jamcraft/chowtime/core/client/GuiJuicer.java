package net.jamcraft.chowtime.core.client;

import net.jamcraft.chowtime.core.container.ContainterFermenter;
import net.jamcraft.chowtime.core.container.ContainterJuicer;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.jamcraft.chowtime.core.tileentities.TEJuicer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class GuiJuicer extends GuiContainer
{
    TEJuicer te;
    public GuiJuicer(InventoryPlayer playerInv, TEJuicer te)
    {
        super(new ContainterJuicer(playerInv, te));
        ySize = 200;
        this.te = te;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui_Juicer);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int i1 = this.te.getScaledProgress(24);
        this.drawTexturedModalRect(xStart + 79, yStart + 34, 176, 14, i1 + 1, 16);
    }
}

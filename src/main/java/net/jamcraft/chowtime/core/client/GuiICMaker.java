package net.jamcraft.chowtime.core.client;

import net.jamcraft.chowtime.core.container.ContainerFermenter;
import net.jamcraft.chowtime.core.container.ContainerICMaker;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.jamcraft.chowtime.core.tileentities.TEIceCreamMaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class GuiICMaker extends GuiContainer
{
    TEIceCreamMaker te;
    public GuiICMaker(InventoryPlayer playerInv, TEIceCreamMaker te)
    {
        super(new ContainerICMaker(playerInv, te));
        ySize = 200;
        this.te = te;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(te.getInventoryName());
        String invName=StatCollector.translateToLocal("container.inventory");
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(invName, xSize - fontRendererObj.getStringWidth(invName)-5, ySize - 128 + 2, 4210752);
        GL11.glPushMatrix();
        GL11.glScaled(0.51F,0.51F,0.51F);
        double tempD=te.getTemp()/1000;
        String temp=Double.toString(tempD)+"°C";
        fontRendererObj.drawString(temp,18 , 55, 4210752);
        GL11.glPopMatrix();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui_ICMaker);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        int i1 = this.te.getScaledProgress(24);
        this.drawTexturedModalRect(xStart + 79, yStart + 34, 176, 14, i1 + 1, 16);

        i1=this.te.getScaledTemp(46);
        this.drawTexturedModalRect(xStart+ 8, yStart + 7, 176, 31, 15, i1+1);
    }
}

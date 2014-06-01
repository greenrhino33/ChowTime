/*
 * ChowTime - Dynamically updating food mod for Minecraft
 *     Copyright (C) 2014  Team JamCraft
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jamcraft.chowtime.core.client.gui;

import net.jamcraft.chowtime.core.client.GuiHelper;
import net.jamcraft.chowtime.core.client.Textures;
import net.jamcraft.chowtime.core.container.ContainerICMaker;
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
        String invName = StatCollector.translateToLocal("container.inventory");
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(invName, xSize - fontRendererObj.getStringWidth(invName) - 5, ySize - 128 + 2, 4210752);
        GL11.glPushMatrix();
        GL11.glScaled(0.51F, 0.51F, 0.51F);
        double tempD = ((double) te.getTemp()) / 1000;
        String temp = Double.toString(tempD) + "\u00B0C";
        int width=fontRendererObj.getStringWidth(temp);
        fontRendererObj.drawString(temp, width / 2 - 12, 55, 4210752);
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

        i1 = this.te.getScaledTemp(47);
        this.drawTexturedModalRect(xStart + 8, yStart + 7, 176, 31, 15, i1 + 1);

        i1 = this.te.getScaledFreezeTemp(47);
        this.drawHorizontalLine(xStart+8,xStart+23,yStart+7+i1, GuiHelper.getIntFromRGBA(255, 0, 0, 255));
    }
}

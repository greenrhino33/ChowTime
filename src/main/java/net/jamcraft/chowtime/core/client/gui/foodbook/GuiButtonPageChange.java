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

package net.jamcraft.chowtime.core.client.gui.foodbook;

import net.jamcraft.chowtime.core.client.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by James Hollowell on 6/1/2014.
 */
public class GuiButtonPageChange extends GuiButton
{
    private final boolean previous;

    public GuiButtonPageChange(int id, int x, int y, boolean previous) {
        super(id, x, y, 23, 13, "");
        this.previous = previous;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            boolean mouseOver = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
            glColor4f(1, 1, 1, 1);
            mc.renderEngine.bindTexture(Textures.Gui_FoodBook);
            int u = 0;
            int v = 192;

            if (mouseOver) {
                u += 23;
            }

            if (previous) {
                v += 13;
            }

            drawTexturedModalRect(xPosition, yPosition, u, v, 23, 13);
        }
    }
}

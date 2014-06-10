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
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by James Hollowell on 6/1/2014.
 */
public class GuiButtonPageChange extends GuiButton
{
    private final boolean previous;

    public GuiButtonPageChange(int id, int x, int y, boolean previous) {
        super(id, x, y, 16, 16, "");
        this.previous = previous;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            boolean mouseOver = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
            glColor4f(1, 1, 1, 1);
            mc.renderEngine.bindTexture(Textures.Gui_FoodBook);
            int u = 175;
            int v = 0;

            if (mouseOver) {
                v += 17;
            }

            if (previous) {
                u += 17;
            }

            GL11.glPushMatrix();

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glColor4f(1, 1, 1, 1);

            drawTexturedModalRect(xPosition, yPosition, u, v, width, height);

            GL11.glEnable(GL11.GL_LIGHTING);

            GL11.glPopMatrix();
        }
    }
}

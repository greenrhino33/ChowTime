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

package net.jamcraft.chowtime.core.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityFermenterRenderer extends TileEntitySpecialRenderer
{

    @Override
    public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8)
    {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x + 1D, y, z);
        GL11.glVertex3d(x + 1D, y + 1D, z);
        GL11.glVertex3d(x, y + 1D, z);
        GL11.glEnd();
    }

}

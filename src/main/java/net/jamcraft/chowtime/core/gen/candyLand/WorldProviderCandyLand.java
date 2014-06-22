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

package net.jamcraft.chowtime.core.gen.candyLand;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Kayla Marie on 5/17/14.
 */
public class WorldProviderCandyLand extends WorldProviderSurface
{

    public WorldProviderCandyLand()
    {
        this.setDimension(ModConstants.Dim_ID_CandyLand);
    }

    public BiomeGenBase getBiomeGenForCoords(int x, int z)
    {
        BiomeGenBase biome = super.getBiomeGenForCoords(x, z);
        if (biome == null) {
        }
        return biome;
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        return 1.0F;
    }

    public double getHorizon()
    {
        return 32.0D;
    }

    public String getDimentionName()
    {
        return "Candy Land";
    }

    public String getWelcomeMessage()
    {
        return "Entering Candy Land";
    }

    public String getDepartMessage()
    {
        return "Leaving Candy Land";
    }

    public boolean isDaytime()
    {
        return true;
    }

}

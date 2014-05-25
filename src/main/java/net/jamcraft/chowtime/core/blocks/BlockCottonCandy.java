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

package net.jamcraft.chowtime.core.blocks;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class BlockCottonCandy extends CTBlock
{

    public BlockCottonCandy()
    {
        super(ChowTime.cloud);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        if (entity.motionY < 0.0D)
        {
            entity.motionY *= 0.005D;
        }
        entity.fallDistance = 0.0F;
    }

    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess iblockaccess, int x, int y, int z, int l)
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y - 1, z) == CTInits.CottonCandyBLUE)
        {
            return null;
        }
        else
        {
            return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.0625D, z + 1.0D);
        }
    }

//    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
//    {
//        return CTInits.ItemCottonCandy;
//    }

}

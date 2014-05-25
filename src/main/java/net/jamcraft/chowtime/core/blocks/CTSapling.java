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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class CTSapling extends BlockSapling
{
    public CTSapling()
    {
        super();
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setHardness(0.0F);
        this.setStepSound(Block.soundTypeGrass);
        this.setCreativeTab(ChowTime.creativeTab);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(ModConstants.MODID + ":" + this.getUnlocalizedName().substring(5));
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        if (block == null || block.isReplaceable(world, x, y, z))
        {
            Block lowerID = world.getBlock(x, y - 1, z);
            // return canThisPlantGrowOnThisBlockID(lowerID);
            if (!canThisPlantGrowOnThisBlock(lowerID))
            {
                Block upperID = world.getBlock(x, y + 1, z);
                return canThisPlantGrowOnThisBlock(upperID);
            }
            else return true;
        }
        return false;
    }
    public boolean canThisPlantGrowOnThisBlock(Block id)
    {
        return id == Blocks.grass || id == Blocks.dirt;
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z) % 8;
        switch (meta)
        {
            case 0:
            case 1:
            case 2:
            case 3:
                Block soil = world.getBlock(x, y - 1, z);
                return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && (soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
            case 4:
            case 6:
            case 7:
                Block netherSoil = world.getBlock(x, y - 1, z);
                return netherSoil != null && (netherSoil == Blocks.netherrack || netherSoil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
            case 5:
                Block nSoil = world.getBlock(x, y + 1, z);
                return nSoil != null && (nSoil == Blocks.netherrack || nSoil == Blocks.soul_sand);
            default:
                return true;
        }
    }
    
    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z) % 8;
        if (meta <= 3) return EnumPlantType.Plains;
        else return EnumPlantType.Nether;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (world.isRemote) { return; }
        super.updateTick(world, x, y, z, random);
        int md = world.getBlockMetadata(x, y, z);
        if (md % 8 == 0)
        {
            if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(120) == 0)
            {
                if ((md & 8) == 0) world.setBlockMetadataWithNotify(x, y, z, md | 8, 4);
                
                else
                {
                    int numSaplings = 0;
                    for (int xPos = -3; xPos <= 3; xPos++)
                    {
                        for (int zPos = -3; zPos <= 3; zPos++)
                        {
                            int ecks = x + xPos, zee = z + zPos;
                            if (world.getBlock(x + xPos, y, z + zPos) == this && world.getBlockMetadata(x + xPos, y, z + zPos) % 8 == 0)
                            {
                                numSaplings++;
                            }
                        }
                    }
                    
                    if (numSaplings >= 40)
                    {
                        for (int xPos = -4; xPos <= 4; xPos++)
                        {
                            for (int zPos = -4; zPos <= 4; zPos++)
                            {
                                int ecks = x + xPos, zee = z + zPos;
                                if (world.getBlock(ecks, y, zee) == this && world.getBlockMetadata(ecks, y, zee) % 8 == 0)
                                {
                                    world.setBlock(ecks, y, zee, Blocks.air, 0, 4);
                                }
                            }
                        }
                        func_149879_c(world, x, y, z, random);
                    }
                }
            }
        }
        else if (md % 8 <= 3)
        {
            if (random.nextInt(10) == 0 && world.getBlockLightValue(x, y + 1, z) >= 9)// &&
                                                                                      // random.nextInt(120)
                                                                                      // ==
                                                                                      // 0)
            {
                if ((md & 8) == 0) world.setBlockMetadataWithNotify(x, y, z, md | 8, 4);
                
                else func_149879_c(world, x, y, z, random);
            }
        }
        else if (random.nextInt(10) == 0)
        {
            if ((md & 8) == 0) world.setBlockMetadataWithNotify(x, y, z, md | 8, 4);
            
            else func_149879_c(world, x, y, z, random);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
    }
    
}

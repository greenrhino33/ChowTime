package net.jamcraft.chowtime.core.gen;

import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.blocks.CTSapling;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class BasicTreeGen extends WorldGenerator
{

    private final int minTreeHeight;
    private final int metaWood;
    private final boolean seekGround;

    public BasicTreeGen(boolean par1)
    {
        this(par1, 4, 0);
    }

    public BasicTreeGen(boolean par1, int par2, int par3)
    {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        seekGround = !par1;
    }

    int findGround(World world, int x, int y, int z)
    {
        boolean foundGround = false;
        int height = y;
        do
        {
            height--;
            Block underId = world.getBlock(x, height, z);
            if (underId == Blocks.dirt || underId == Blocks.grass || height < 0)
                foundGround = true;
        } while (!foundGround);
        return height + 1;
    }

    public boolean generate(World world, Random random, int posX, int posY, int posZ)
    {
        int treeHeight = random.nextInt(3) + this.minTreeHeight;
        if (treeHeight < 4)
            treeHeight = 4;
        boolean flag = true;

        if (this.seekGround)
            posY = findGround(world, posX, posY, posZ);

        if (posY >= 1 && posY + treeHeight + 1 <= 256)
        {
            int i1;
            byte b0;
            int j1;
            int k1;

            for (i1 = posY; i1 <= posY + 1 + treeHeight; i1++)
            {
                b0 = 1;

                if (i1 == posY)
                {
                    b0 = 0;
                }

                if (i1 >= posY + 1 + treeHeight - 2)
                {
                    b0 = 2;
                }

                for (int l1 = posX - b0; l1 <= posX + b0 && flag; ++l1)
                {
                    for (j1 = posZ - b0; j1 <= posZ + b0 && flag; ++j1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {

                            Block block = world.getBlock(l1, i1, j1);

                            if (block != Blocks.air && !block.isLeaves(world, l1, i1, j1) && block != Blocks.dirt && block != Blocks.grass && !block.isWood(world, l1, i1, j1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }
            if (!flag)
            {
                return false;
            }
            else
            {
                Block soil = world.getBlock(posX, posY - 1, posZ);
                boolean isSoil = (soil != null && soil.canSustainPlant(world, posX, posY - 1, posZ, ForgeDirection.UP, (CTSapling) CTInits.CTSapling)) || soil == Blocks.dirt;

                if (isSoil && posY < 256 - treeHeight - 1)
                {
                    soil.onPlantGrow(world, posX, posY - 1, posZ, posX, posY, posZ);
                    b0 = 3;
                    byte b1 = 0;
                    int i2;
                    int j2;
                    int k2;

                    for (j1 = posY - b0 + treeHeight; j1 <= posY + treeHeight; ++j1)
                    {
                        k1 = j1 - (posY + treeHeight);
                        i2 = b1 + 1 - k1 / 2;

                        for (j2 = posX - i2; j2 <= posX + i2; ++j2)
                        {
                            k2 = j2 - posX;

                            for (int l2 = posZ - i2; l2 <= posZ + i2; ++l2)
                            {
                                int i3 = l2 - posZ;

                                if (Math.abs(k2) != i2 || Math.abs(i3) != i2 || random.nextInt(2) != 0 && k1 != 0)
                                {
                                    Block block = world.getBlock(j2, j1, l2);

                                    if (block == null || block.canBeReplacedByLeaves(world, j2, j1, l2))
                                    {
                                        this.setBlockAndNotifyAdequately(world, j2, j1, l2, CTInits.CTLeaves, random.nextInt(25) == 0 ? 2 : random.nextInt(15) == 0 ? 1 : 0);
                                    }
                                }
                            }
                        }
                    }

                    for (j1 = 0; j1 < treeHeight; ++j1)
                    {
                        Block block = world.getBlock(posX, posY + j1, posZ);

                        if (block == Blocks.air || block == null || block.isLeaves(world, posX, posY + j1, posZ))
                        {
                            this.setBlockAndNotifyAdequately(world, posX, posY + j1, posZ, CTInits.CTLog, this.metaWood);

                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }

    }
}

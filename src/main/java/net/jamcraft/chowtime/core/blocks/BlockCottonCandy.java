package net.jamcraft.chowtime.core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class BlockCottonCandy extends Block{

    public BlockCottonCandy(){
        super(ChowTime.cloud);
        this.setCreativeTab(ChowTime.creativeTab);
    }

    @Override
    public void onEntityCollidedWithBlock (World world, int x, int y, int z, Entity entity)
    {
        if (entity.motionY < 0.0D)
        {
            entity.motionY *= 0.005D;
        }
        entity.fallDistance = 0.0F;
    }

    @Override
    public int getRenderBlockPass ()
    {
        return 1;
    }

    @Override
    public boolean isBlockSolid (IBlockAccess iblockaccess, int x, int y, int z, int l)
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
    public boolean isOpaqueCube ()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x, int y, int z)
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

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Items.snowball;
    }

}

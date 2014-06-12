package net.jamcraft.chowtime.core.blocks;

import java.util.Random;

import net.jamcraft.chowtime.core.CTInits;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CTFarmland extends BlockFarmland
{
    public CTFarmland()
    {
        super();
        setHardness(0.6F);
        setStepSound(Block.soundTypeGravel);
        setBlockName("farmland");
        setBlockTextureName("farmland");
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance)
    {
        if (!world.isRemote && world.rand.nextFloat() < distance - 0.5F)
        {
            if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing") || (entity instanceof EntityPlayer && ((EntityPlayer)entity).inventory.armorInventory[0] != null && ((EntityPlayer)entity).inventory.armorInventory[0].getItem() == CTInits.FarmerBoots))
            {
                return;
            }

            world.setBlock(x, y, z, Blocks.dirt);
        }
    }
}
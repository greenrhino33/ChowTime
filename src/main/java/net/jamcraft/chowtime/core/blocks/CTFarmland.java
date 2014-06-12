package net.jamcraft.chowtime.core.blocks;

import net.jamcraft.chowtime.core.CTInits;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

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
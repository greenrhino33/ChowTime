package net.jamcraft.chowtime.core.materials;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class CloudMaterial extends Material
{

    public CloudMaterial()
    {
        super(MapColor.snowColor);
        this.setNoPushMobility();
    }

    @Override
    public boolean isLiquid()
    {
        return false;
    }

    @Override
    public boolean blocksMovement()
    {
        return false;
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }

    @Override
    public boolean getCanBlockGrass()
    {
        return false;
    }
}

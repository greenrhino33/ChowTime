package net.jamcraft.chowtime.core.crops;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import java.util.Random;

public class CTCrops extends BlockCrops {

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public CTCrops(){
        super();
        float f = 0.5F;
        this.setTickRandomly(true);
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.disableStats();
    }

    public int getRenderType()
    {
        return 6;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 > 7)
        {
            par2 = 7;
        }

        return this.icons[par2];
    }

    public Item getItemDropped(int par1, Random par2, int par3)
    {
        return par1 == 7 ? CTInits.BarleyCrop : CTInits.BarleySeeds;
    }

}
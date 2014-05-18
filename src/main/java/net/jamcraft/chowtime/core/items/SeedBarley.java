package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class SeedBarley extends ItemSeeds
{

    public SeedBarley()
    {
        super(CTInits.CropBarley, Blocks.farmland);
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":" + "ItemSeed_Barley");
        setUnlocalizedName("seedBarley");
    }

}

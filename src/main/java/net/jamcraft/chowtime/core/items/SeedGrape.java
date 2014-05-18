package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

/**
 * Created by Kayla Marie on 5/17/14.
 */
public class SeedGrape extends ItemSeeds {

    public SeedGrape(){
        super(CTInits.CropGrape, Blocks.farmland);
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":" + "grapeSeeds");
        setUnlocalizedName("grapeSeeds");
    }
}

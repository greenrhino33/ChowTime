package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.minecraft.item.ItemSeedFood;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class SeedBarley extends ItemSeedFood {

    public SeedBarley(){
        super(2, 2.0F, null, null);
        this.setCreativeTab(ChowTime.creativeTab);
    }
}

package net.jamcraft.chowtime.core.gen.candyLand;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Kayla Marie on 5/17/14.
 */
public class WorldProviderCandyLand extends WorldProviderSurface {

    public WorldProviderCandyLand() {
        this.setDimension(ModConstants.Dim_ID_CandyLand);
    }

    public BiomeGenBase getBiomeGenForCoords(int x, int z)
    {
        BiomeGenBase biome = super.getBiomeGenForCoords(x, z);
        if (biome == null) {
<<<<<<< HEAD
=======
            //biome = TFBiomeBase.twilightForest;
>>>>>>> f4ddf769ec35d230348f29bc0fe6b515100668a9
        }
        return biome;
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        return 1.0F;
    }

    public double getHorizon()
    {
        return 32.0D;
    }

    public String getDimentionName() {
        return "Candy Land";
    }

    public String getWelcomeMessage()
    {
        return "Entering Candy Land";
    }

    public String getDepartMessage()
    {
        return "Leaving Candy Land";
    }

    public boolean isDaytime()
    {
        return true;
    }

}

package net.jamcraft.chowtime.core.mobs.SeedMob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class RenderSeedMob extends RenderLiving
{

    public RenderSeedMob(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    @Override protected ResourceLocation getEntityTexture(Entity var1)
    {
        return null;
    }
}

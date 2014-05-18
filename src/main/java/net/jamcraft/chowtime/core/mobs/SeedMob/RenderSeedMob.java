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

    public static ResourceLocation texture = new ResourceLocation("chowtime:textures/mobs/seedMob/texture1.png");

    public RenderSeedMob(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(EntitySeedMob par1Entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntitySeedMob) par1Entity);
    }
}

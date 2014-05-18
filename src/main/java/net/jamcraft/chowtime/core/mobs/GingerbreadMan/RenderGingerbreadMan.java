package net.jamcraft.chowtime.core.mobs.GingerbreadMan;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class RenderGingerbreadMan extends RenderLiving
{

    public static ResourceLocation texture = new ResourceLocation("chowtime:textures/mobs/gingerbreadMan/texture.png");

    public RenderGingerbreadMan(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(EntityGingerbreadMan par1Entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityGingerbreadMan) par1Entity);
    }
}

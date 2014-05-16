package net.jamcraft.chowtime.core.mobs.SeedMob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class RenderSeedMob extends RenderCow {

    public static ResourceLocation texture = "chowtime:textures/mobs/seedMob/texture.png";
    public RenderSeedMob(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    public void renderSeedMob(EntitySeedMob par1EntityCow, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderLiving(par1EntityCow, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderSeedMob((EntitySeedMob) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderSeedMob((EntitySeedMob) par1Entity, par2, par4, par6, par8, par9);
    }
}

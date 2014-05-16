package net.jamcraft.chowtime.core.mobs.SeedMob;

import net.minecraft.client.model.ModelBase;
<<<<<<< HEAD
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
=======
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
>>>>>>> 01d30b20c4d1587fa3cabf3b656ee61e5dc3a56c
import net.minecraft.util.ResourceLocation;

/**
 * Created by Kayla Marie on 5/16/14.
 */
<<<<<<< HEAD
public class RenderSeedMob extends RenderCow {

    public static ResourceLocation texture = "chowtime:textures/mobs/seedMob/texture.png";
=======
public class RenderSeedMob extends RenderLiving
{

>>>>>>> 01d30b20c4d1587fa3cabf3b656ee61e5dc3a56c
    public RenderSeedMob(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

<<<<<<< HEAD
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
=======
    @Override protected ResourceLocation getEntityTexture(Entity var1)
    {
        return null;
>>>>>>> 01d30b20c4d1587fa3cabf3b656ee61e5dc3a56c
    }
}

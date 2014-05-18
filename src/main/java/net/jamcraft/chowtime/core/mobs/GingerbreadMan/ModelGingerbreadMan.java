package net.jamcraft.chowtime.core.mobs.GingerbreadMan;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ModelGingerbreadMan extends ModelBase {
    //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer leg1;
    ModelRenderer lag2;

    public ModelGingerbreadMan()
    {
        textureWidth = 64;
        textureHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2F, -4F, -1F, 8, 8, 4);
        head.setRotationPoint(-3F, -9F, 0F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 0, 12);
        body.addBox(-6F, 0F, -1F, 12, 16, 4);
        body.setRotationPoint(-1F, -5F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        arm1 = new ModelRenderer(this, 32, 13);
        arm1.addBox(-2F, 0F, -1F, 4, 15, 4);
        arm1.setRotationPoint(5F, -5F, 0F);
        arm1.setTextureSize(64, 32);
        arm1.mirror = true;
        setRotation(arm1, 0F, 0F, -0.2974289F);
        arm2 = new ModelRenderer(this, 32, 13);
        arm2.addBox(-2F, 0F, -1F, 4, 15, 4);
        arm2.setRotationPoint(-7F, -5F, 0F);
        arm2.setTextureSize(64, 32);
        arm2.mirror = true;
        setRotation(arm2, 0F, 0F, 0.2602503F);
        leg1 = new ModelRenderer(this, 47, 14);
        leg1.addBox(-3F, 0F, -1F, 5, 14, 4);
        leg1.setRotationPoint(3F, 10F, 0F);
        leg1.setTextureSize(64, 32);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, -0.1115358F);
        lag2 = new ModelRenderer(this, 46, 14);
        lag2.addBox(-2F, 0F, -1F, 5, 14, 4);
        lag2.setRotationPoint(-5F, 10F, 0F);
        lag2.setTextureSize(64, 32);
        lag2.mirror = true;
        setRotation(lag2, 0F, 0F, 0.1115358F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        head.render(f5);
        body.render(f5);
        arm1.render(f5);
        arm2.render(f5);
        leg1.render(f5);
        lag2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}

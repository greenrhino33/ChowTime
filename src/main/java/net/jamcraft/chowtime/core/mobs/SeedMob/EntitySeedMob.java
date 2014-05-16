package net.jamcraft.chowtime.core.mobs.SeedMob;

import net.jamcraft.chowtime.core.CTInits;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class EntitySeedMob extends EntityAnimal{

    public EntitySeedMob(World par1World) {
        super(par1World);
        this.setHealth(10.0F);
        this.getNavigator().setSpeed(0.222);
        this.setSize(0.5F, 0.5F);
        this.isImmuneToFire = false;
        float var2 = 0.27F;

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.33F));
        this.tasks.addTask(2, new EntityAIMate(this,var2));
        this.tasks.addTask(3, new EntityAITempt(this, 0.3F, Items.apple, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(5, new EntityAIWander(this, 0.25F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    public boolean isAIEnabled(){
        return true;
    }

    protected String getLivingSound() {
        return "mob.glog.say";
    }

    protected String getHurtSound() {
        return "mob.glog.say";
    }

    protected String getDeathSound() {
        return "mob.glog.death";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4){
        this.worldObj.playSoundAtEntity(this, "mob.glog.step", 0.1F, 1.0F);
    }

    protected Item getDropItem() {
        return CTInits.BarleySeeds;
    }

    public EntityAgeable createChild(EntityAgeable var1) {
        return null;
    }
}

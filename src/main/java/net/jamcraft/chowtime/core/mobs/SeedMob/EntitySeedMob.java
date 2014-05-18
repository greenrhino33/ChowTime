package net.jamcraft.chowtime.core.mobs.SeedMob;

import net.jamcraft.chowtime.core.CTInits;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class EntitySeedMob extends EntityAnimal
{

    private int inLove;
    private int breeding;
    private EntityPlayer field_146084_br;

    public EntitySeedMob(World par1World)
    {
        super(par1World);

        this.setHealth(10.0F);
        this.getNavigator().setSpeed(0.222);
        this.setSize(0.5F, 0.5F);
        this.isImmuneToFire = false;
        float var2 = 0.27F;

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.33F));
        this.tasks.addTask(2, new EntityAIMate(this, var2));
        this.tasks.addTask(3, new EntityAITempt(this, 0.3F, CTInits.Strawberry, false));
        //this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 0.28F));
        this.tasks.addTask(5, new EntityAIWander(this, 0.25F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 5.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    public boolean isAIEnabled()
    {
        return true;
    }

    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItem() == CTInits.Strawberry;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.getGrowingAge() != 0)
        {
            this.inLove = 0;
        }

        if (this.inLove > 0)
        {
            --this.inLove;
            String s = "heart";

            if (this.inLove % 10 == 0)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle(s, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
        }
        else
        {
            this.breeding = 0;
        }
    }

    private void procreate(EntitySeedMob par1EntityAnimal)
    {
        EntityAgeable entityageable = this.createChild(par1EntityAnimal);

        if (entityageable != null)
        {
            if (this.field_146084_br == null && par1EntityAnimal.func_146083_cb() != null)
            {
                this.field_146084_br = par1EntityAnimal.func_146083_cb();
            }

            if (this.field_146084_br != null)
            {
                this.field_146084_br.triggerAchievement(StatList.field_151186_x);

            }

            this.setGrowingAge(6000);
            par1EntityAnimal.setGrowingAge(6000);
            this.inLove = 0;
            this.breeding = 0;
            par1EntityAnimal.breeding = 0;
            par1EntityAnimal.inLove = 0;
            entityageable.setGrowingAge(-24000);
            entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);

            for (int i = 0; i < 7; ++i)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("heart", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }

            this.worldObj.spawnEntityInWorld(entityageable);
        }
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public boolean isChild()
    {
        return this.getGrowingAge() < 0;
    }

    protected String getLivingSound()
    {
        return "mob.glog.say";
    }

    protected String getHurtSound()
    {
        return "mob.glog.say";
    }

    protected String getDeathSound()
    {
        return "mob.glog.death";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.glog.step", 0.1F, 1.0F);
    }

    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

        Random random = new Random();
        int n = random.nextInt(9);
        int produce = random.nextInt(10) + 6;

//        if(){
        if (par1EntityPlayer.getCurrentEquippedItem() == null){
            this.mountEntity(par1EntityPlayer);
        }
        if (itemstack != null && itemstack.getItem() == Items.wheat_seeds && !par1EntityPlayer.capabilities.isCreativeMode)
        {
                switch(n){
                    case 0:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.BarleySeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 1:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.StrawberrySeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 2:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.GrapeSeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 3:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.CornSeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 4:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.RaspberrySeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 5:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.BlueberrySeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 6:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.CranberrySeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 7:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.CornSeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    case 8:
                        par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(CTInits.TomatoSeeds, 1, 0));
                        par1EntityPlayer.inventory.consumeInventoryItem(Items.wheat_seeds);
                        break;
                    default:
                        break;
                }
            return true;
          }
        else
        {
            return super.interact(par1EntityPlayer);
        }
       //}

    }

    @Override
    protected Item getDropItem()
    {
        return null;
    }

    public EntityAgeable createChild(EntityAgeable var1)
    {
        return new EntitySeedMob(this.worldObj);
    }
//
//    @Override
//    public String getOwnerName() {
//        return null;
//    }
}

package net.jamcraft.chowtime.core.lib;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by James Hollowell on 5/18/2014.
 */
public class ItemHelper
{
    /**Bucket intellegent decrease of stack si
     *
     * @param itemStack Stack to decrease.
     * @param world World object in case of spawning items on ground
     * @param x Spawn center x
     * @param y Spawn center y
     * @param z Spawn center z
     * @return The resultant itemstack
     */
    public static ItemStack decreaseStack(ItemStack itemStack, World world, int x, int y, int z)
    {
        ItemStack stack=itemStack.copy();
        stack.stackSize--;
        if(stack.getItem() instanceof ItemBucket || stack.getItem() instanceof ItemBucketMilk)
        {
            if(stack.stackSize<=0)
            {
                stack=new ItemStack(Items.bucket);
            }
            else
            {
                ItemHelper.spawnItemStackInWorld(new ItemStack(Items.bucket),world,x,y,z);
            }

        }
        else
        {
            if (stack.stackSize <= 0)
            {
                stack = null;
            }
        }
        return stack;
    }

    public static void spawnItemStackInWorld(ItemStack stack, World world, int x, int y, int z)
    {
        if(!world.isRemote)
        {
            Random rand = new Random();
            final float f = rand.nextFloat() * 0.8F + 0.1F;
            final float f1 = rand.nextFloat() * 0.8F + 0.1F;
            final float f2 = rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), stack);
            entityitem.motionX = (double) ((float) rand.nextGaussian() * .05F);
            entityitem.motionY = (double) ((float) rand.nextGaussian() * 0.25F);
            entityitem.motionZ = (double) ((float) rand.nextGaussian() * .05F);
            world.spawnEntityInWorld(entityitem);
        }
    }
}

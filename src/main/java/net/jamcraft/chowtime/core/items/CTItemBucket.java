package net.jamcraft.chowtime.core.items;

import java.io.IOException;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event;

public class CTItemBucket extends Item
{
	Block liquid;
	Item bucket;
	public CTItemBucket(Block liquid, Item bucket, String icon)
	{
		this.maxStackSize = 1;
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":" + icon);
        this.liquid = liquid;
        this.bucket = bucket;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
	{
		boolean flag = liquid == Blocks.air;
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);

		if (movingobjectposition == null)
		{
			return stack;
		}
		else
		{
			FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, stack, par2World, movingobjectposition);
			if (MinecraftForge.EVENT_BUS.post(event))
			{
				return stack;
			}

			if (event.getResult() == Event.Result.ALLOW)
			{
				if (par3EntityPlayer.capabilities.isCreativeMode)
				{
					return stack;
				}

				if (--stack.stackSize <= 0)
				{
					return event.result;
				}

				if (!par3EntityPlayer.inventory.addItemStackToInventory(event.result))
				{
					par3EntityPlayer.dropPlayerItemWithRandomChoice(event.result, false);
				}

				return stack;
			}
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
				{
					return stack;
				}

				if (flag)
				{
					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
					{
						return stack;
					}

					par2World.setBlockToAir(i, j, k);
					return this.placeBucketInInv(stack, par3EntityPlayer, bucket);
				}
				else
				{
					if (liquid == Blocks.air)
					{
						return new ItemStack(Items.bucket);
					}

					if (movingobjectposition.sideHit == 0)
					{
						--j;
					}

					if (movingobjectposition.sideHit == 1)
					{
						++j;
					}

					if (movingobjectposition.sideHit == 2)
					{
						--k;
					}

					if (movingobjectposition.sideHit == 3)
					{
						++k;
					}

					if (movingobjectposition.sideHit == 4)
					{
						--i;
					}

					if (movingobjectposition.sideHit == 5)
					{
						++i;
					}

					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
					{
						return stack;
					}

					try {
						if (this.tryPlaceContainedLiquid(par2World, i, j, k, stack) && !par3EntityPlayer.capabilities.isCreativeMode)
						{
							return new ItemStack(Items.bucket);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			return stack;
		}
	}

	private ItemStack placeBucketInInv(ItemStack stack, EntityPlayer player, Item item)
	{
		if (player.capabilities.isCreativeMode)
		{
			return stack;
		}
		else if (--stack.stackSize <= 0)
		{
			return new ItemStack(item);
		}
		else
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(item)))
			{
				player.dropPlayerItemWithRandomChoice(new ItemStack(item, 1, 0), false);
			}

			return stack;
		}
	}

	/**
	 * Attempts to place the liquid contained inside the bucket.
	 * @throws IOException 
	 */
	public boolean tryPlaceContainedLiquid(World par1World, int par2, int par3, int par4, ItemStack stack) throws IOException
	{
		if (liquid == Blocks.air)
		{
			return false;
		}
		else
		{
			Material material = par1World.getBlock(par2, par3, par4).getMaterial();
			boolean flag = !material.isSolid();

			if (!par1World.isAirBlock(par2, par3, par4) && !flag) return false;
			else
			{
				if (!par1World.isRemote && flag && !material.isLiquid()) par1World.func_147480_a(par2, par3, par4, true);
				
				par1World.setBlock(par2, par3, par4, liquid, 0, 3);
				return true;
			}
		}
	}
}

package net.jamcraft.chowtime.core.events;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.Config;
import net.jamcraft.chowtime.core.crops.CropBarley;
import net.jamcraft.chowtime.core.crops.CropBlueberry;
import net.jamcraft.chowtime.core.crops.CropCorn;
import net.jamcraft.chowtime.core.crops.CropCranberry;
import net.jamcraft.chowtime.core.crops.CropGrape;
import net.jamcraft.chowtime.core.crops.CropRaspberry;
import net.jamcraft.chowtime.core.crops.CropStrawberry;
import net.jamcraft.chowtime.core.crops.CropTomato;
import net.jamcraft.chowtime.core.items.SeedCorn;
import net.jamcraft.chowtime.core.items.SeedCranberry;
import net.jamcraft.chowtime.core.items.SeedGrape;
import net.jamcraft.chowtime.core.items.SeedRaspberry;
import net.jamcraft.chowtime.core.items.SeedStrawberry;
import net.jamcraft.chowtime.core.items.SeedTomato;
import net.jamcraft.chowtime.remote.RemoteMain;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class EntityEventHandler
{
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            
            ChowTime.harvestXP = ChowTime.saveData.getInteger("harvestXP" + (player).getCommandSenderName());
            ChowTime.harvestLVL = ChowTime.saveData.getInteger("harvestLVL" + (player).getCommandSenderName());
            
            if (event.world.isRemote)
            {
                if (RemoteMain.hasUpdated)
                {
                    player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("string.updated")));
                    RemoteMain.hasUpdated = false;
                }
                if (Config.forceLocal)
                {
                    player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("string.warnlocal")));
                }
                RemoteMain.player=player;
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event)
    {
        if (!event.world.isRemote)
        {
            new File(ChowTime.dir + File.separator + "ChowTime").mkdirs();
            ChowTime.harvestingLVL = new File(ChowTime.dir + File.separator + "ChowTime", "CT" + event.world.getWorldInfo().getWorldName() + ".cfg");
            try
            {
                if (!ChowTime.harvestingLVL.exists()) ChowTime.harvestingLVL.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            try
            {
                if (ChowTime.harvestingLVL.exists()) ChowTime.saveData = CompressedStreamTools.readCompressed(new FileInputStream(ChowTime.harvestingLVL));
            }
            catch (EOFException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @SubscribeEvent
    public void onWorldSave(WorldEvent.Save event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            try
            {
                if (ChowTime.harvestingLVL.exists()) CompressedStreamTools.writeCompressed(ChowTime.saveData, new FileOutputStream(ChowTime.harvestingLVL));
                int i = event.world.playerEntities.size();
                for (int j = 0; j < i; j++)
                {
                    ChowTime.saveData.setInteger("harvestXP" + ((EntityPlayer) event.world.playerEntities.get(j)).getCommandSenderName(), ChowTime.harvestXP);
                    ChowTime.saveData.setInteger("harvestLVL" + ((EntityPlayer) event.world.playerEntities.get(j)).getCommandSenderName(), ChowTime.harvestLVL);
                }
            }
            catch (EOFException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            if (event.entityLiving instanceof EntityPlayer)
            {
            }
        }
    }
    
    @SubscribeEvent
    public void onItemUseStart(PlayerUseItemEvent.Start event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            
        }
    }
    
    @SubscribeEvent
    public void onItemUseTick(PlayerUseItemEvent.Tick event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            
        }
    }
    
    @SubscribeEvent
    public void onItemUseStopBeforeFinish(PlayerUseItemEvent.Stop event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            
        }
    }
    
    @SubscribeEvent
    public void onItemUseFinish(PlayerUseItemEvent.Finish event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            // if (event.item.getItem() instanceof ItemAppleGold ||
            // event.item.getItem() instanceof ItemFood || event.item.getItem()
            // instanceof ItemSoup || event.item.getItem() instanceof
            // ItemFishFood) event.entityPlayer.addChatMessage(new
            // ChatComponentText("Munch munch munch"));
        }
    }
    
    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            if (!(event.entityPlayer instanceof FakePlayer))
            {
                boolean canHarvest = true;
                if (ChowTime.harvestXP < 20)
                {
                    if ((event.block instanceof CropTomato || event.entityPlayer.worldObj.getBlock(event.x, event.y + 1, event.z) instanceof CropTomato) || (event.block instanceof CropCranberry || event.entityPlayer.worldObj.getBlock(event.x, event.y + 1, event.z) instanceof CropCranberry) || (event.block instanceof CropRaspberry || event.entityPlayer.worldObj.getBlock(event.x, event.y + 1, event.z) instanceof CropRaspberry)) canHarvest = false;
                }
                if (ChowTime.harvestXP < 100)
                {
                    if ((event.block instanceof CropCorn || event.entityPlayer.worldObj.getBlock(event.x, event.y + 1, event.z) instanceof CropCorn) || (event.block instanceof CropGrape || event.entityPlayer.worldObj.getBlock(event.x, event.y + 1, event.z) instanceof CropGrape)) canHarvest = false;
                }
                if (ChowTime.harvestXP < 300)
                {
                    if ((event.block instanceof CropStrawberry || event.entityPlayer.worldObj.getBlock(event.x, event.y + 1, event.z) instanceof CropStrawberry)) canHarvest = false;
                }
                if (!canHarvest)
                {
                    event.entityPlayer.worldObj.markBlockForUpdate(event.x, event.y, event.z);
                    event.setCanceled(true);
                }
            }
            if (event.metadata == 7 && !(event.entityPlayer instanceof FakePlayer))
            {
                if (ChowTime.harvestXP >= 0 && event.block instanceof CropBarley || event.block instanceof CropBlueberry) ChowTime.harvestXP++;
                if (ChowTime.harvestXP >= 20 && event.block instanceof CropTomato || event.block instanceof CropCranberry || event.block instanceof CropRaspberry) ChowTime.harvestXP += 2;
                if (ChowTime.harvestXP >= 100 && event.block instanceof CropGrape) ChowTime.harvestXP += 4;
                if (ChowTime.harvestXP >= 100 && event.block instanceof CropCorn) ChowTime.harvestXP += 5;
                if (ChowTime.harvestXP >= 300 && event.block instanceof CropStrawberry) ChowTime.harvestXP += 10;
                // event.entityPlayer.addChatMessage(new
                // ChatComponentText(Integer.toString(ChowTime.harvestXP)));
            }
        }
    }
    
    @SubscribeEvent
    public void harvestCheck(PlayerEvent.HarvestCheck event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
        }
    }
    
    @SubscribeEvent
    public void blockInteraction(PlayerInteractEvent event)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer())
        {
            if (event.action == event.action.LEFT_CLICK_BLOCK && !(event.entityPlayer instanceof FakePlayer))
            {
                if(event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropStrawberry ||
                        event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropBlueberry ||
                        event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropCranberry ||
                        event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropRaspberry ||
                        event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropTomato ||
                        event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropCorn ||
                        event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropGrape ||
                        event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropBarley)
                {
                    if(ChowTime.harvestXP == 20) event.entityPlayer.addChatMessage(new ChatComponentText("Congratulation! You have gained 20 harvesting experience! You can now plant & harvest Tomatoes, Raspberries and Cranberries."));
                    if(ChowTime.harvestXP == 100) event.entityPlayer.addChatMessage(new ChatComponentText("Congratulation! You have gained 100 harvesting experience! You can now plant & harvest Corn, Grapes."));
                    if(ChowTime.harvestXP == 300) event.entityPlayer.addChatMessage(new ChatComponentText("Congratulation! You have gained 300 harvesting experience! You can now plant & harvest Strawberries."));
                }
            }
            if (event.action == event.action.RIGHT_CLICK_BLOCK && !(event.entityPlayer instanceof FakePlayer) && event.entityPlayer.getHeldItem() != null && event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof BlockFarmland)
            {
                
                boolean canPlant = true;
                if (ChowTime.harvestXP < 20)
                {
                    if (event.entityPlayer.getHeldItem().getItem() instanceof SeedCranberry || event.entityPlayer.getHeldItem().getItem() instanceof SeedTomato || event.entityPlayer.getHeldItem().getItem() instanceof SeedRaspberry) canPlant = false;
                }
                if (ChowTime.harvestXP < 100)
                {
                    if (event.entityPlayer.getHeldItem().getItem() instanceof SeedCorn || event.entityPlayer.getHeldItem().getItem() instanceof SeedGrape) canPlant = false;
                }
                if (ChowTime.harvestXP < 300)
                {
                    if (event.entityPlayer.getHeldItem().getItem() instanceof SeedStrawberry) canPlant = false;
                }
                if (!canPlant)
                {
                    event.setCanceled(true);
                    event.entityPlayer.addChatMessage(new ChatComponentText("You are not experienced enough to plant these seeds. Try gaining more levels first."));
                    event.entityPlayer.addChatMessage(new ChatComponentText("To gaing more experience break fully grown crops that are on your level."));
                }
            }
        }
    }
}
package net.jamcraft.chowtime.core.events;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.Config;
import net.jamcraft.chowtime.core.crops.CropBarley;
import net.jamcraft.chowtime.core.crops.CropStrawberry;
import net.jamcraft.chowtime.remote.RemoteMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.io.*;

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
                }
                if (Config.forceLocal)
                {
                    player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("string.warnlocal")));
                }
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
                if (!ChowTime.harvestingLVL.exists())
                    ChowTime.harvestingLVL.createNewFile();
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
                if (ChowTime.harvestingLVL.exists())
                    ChowTime.saveData = CompressedStreamTools.readCompressed(new FileInputStream(ChowTime.harvestingLVL));
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
                if (ChowTime.harvestingLVL.exists())
                    CompressedStreamTools.writeCompressed(ChowTime.saveData, new FileOutputStream(ChowTime.harvestingLVL));
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
            if (event.block instanceof CropStrawberry && ChowTime.harvestXP < 130){
                event.newSpeed = -1;
                event.entityPlayer.worldObj.scheduleBlockUpdate(event.x, event.y, event.z, event.block, 0);
            }
            if (event.block instanceof CropBarley && event.metadata == 7)
            {
                ChowTime.harvestXP++;
                event.entityPlayer.addChatMessage(new ChatComponentText(Integer.toString(ChowTime.harvestXP)));
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
}
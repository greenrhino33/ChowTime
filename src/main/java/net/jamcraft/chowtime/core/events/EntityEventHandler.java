package net.jamcraft.chowtime.core.events;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.Config;
import net.jamcraft.chowtime.core.crops.CropBarley;
import net.jamcraft.chowtime.core.crops.CropStrawberry;
import net.jamcraft.chowtime.core.items.SeedStrawberry;
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
            if ((event.block instanceof CropStrawberry || event.entityPlayer.worldObj.getBlock(event.x, event.y + 1, event.z) instanceof CropStrawberry) && !(event.entityPlayer instanceof FakePlayer) && ChowTime.harvestXP < 130){
                event.entityPlayer.worldObj.markBlockForUpdate(event.x, event.y, event.z);
                event.setCanceled(true);
//                event.entityPlayer.addChatMessage(new ChatComponentText("You are not experienced enough to harvest this crop. Try gaining more levels first."));
//                event.entityPlayer.addChatMessage(new ChatComponentText("To gaing more experience break fully grown barley."));
//                event.setCanceled(true);
            }
            if (event.block instanceof CropBarley && event.metadata == 7 && !(event.entityPlayer instanceof FakePlayer)){
                ChowTime.harvestXP++;
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
            if(event.action == event.action.RIGHT_CLICK_BLOCK && !(event.entityPlayer instanceof FakePlayer) && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem() instanceof SeedStrawberry && event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof BlockFarmland && ChowTime.harvestXP < 130){
                event.setCanceled(true);
                event.entityPlayer.addChatMessage(new ChatComponentText("You are not experienced enough to plant strawberry seeds. Try gaining more levels first."));
                event.entityPlayer.addChatMessage(new ChatComponentText("To gaing more experience break fully grown barley."));
            }
        }
    }
}
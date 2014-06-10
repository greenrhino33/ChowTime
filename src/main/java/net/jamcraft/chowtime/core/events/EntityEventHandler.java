/*
 * ChowTime - Dynamically updating food mod for Minecraft
 *     Copyright (C) 2014  Team JamCraft
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jamcraft.chowtime.core.events;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
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
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.WorldEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class EntityEventHandler
{
    private static boolean HasBeenNotified = false;
    Minecraft mc = Minecraft.getMinecraft();
    
    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Text event)
    {
        ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
        FontRenderer fontrenderer = this.mc.fontRenderer;
        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int j2;
        int k2;
        String s3 = "ChowTime";
        j2 = (k - fontrenderer.getStringWidth(s3)) / 2 - 75 - fontrenderer.getStringWidth(s3);
        k2 = l - 31 - 4 - 10;
        fontrenderer.drawString(s3, j2 + 1, k2, 0);
        fontrenderer.drawString(s3, j2 - 1, k2, 0);
        fontrenderer.drawString(s3, j2, k2 + 1, 0);
        fontrenderer.drawString(s3, j2, k2 - 1, 0);
        for(int i=0; i<=7; i++) fontrenderer.drawString("_", j2 + i*6 - 2, k2 + 2, 0);
        fontrenderer.drawString(s3, j2, k2, 0x00c2e0);
        
        s3 = "XP: " + ChowTime.harvestXP;
        j2 = (k - fontrenderer.getStringWidth(s3)) / 2 - 80 - fontrenderer.getStringWidth(s3);
        k2 += 12;
        fontrenderer.drawString(s3, j2 + 1, k2, 0);
        fontrenderer.drawString(s3, j2 - 1, k2, 0);
        fontrenderer.drawString(s3, j2, k2 + 1, 0);
        fontrenderer.drawString(s3, j2, k2 - 1, 0);
        fontrenderer.drawString(s3, j2, k2, 0x22c530);
        
        int xp=0;
        if(ChowTime.harvestXP < 20) xp = 20;
        if(ChowTime.harvestXP >= 20 && ChowTime.harvestXP < 100) xp = 100;
        if(ChowTime.harvestXP >= 100 && ChowTime.harvestXP < 300) xp = 300;
        if(ChowTime.harvestXP < 300)
        {            
            s3 = "Next farming upgrade:";
            j2 = (k - fontrenderer.getStringWidth(s3)) / 2 - 150;
            k2 += 12;
            fontrenderer.drawString(s3, j2 + 1, k2, 0);
            fontrenderer.drawString(s3, j2 - 1, k2, 0);
            fontrenderer.drawString(s3, j2, k2 + 1, 0);
            fontrenderer.drawString(s3, j2, k2 - 1, 0);
            fontrenderer.drawString(s3, j2, k2, 0xfeee00);
            
            s3 = "" + xp;
            j2 = (k - fontrenderer.getStringWidth(s3)) / 2 - 120;
            k2 += 12;
            fontrenderer.drawString(s3, j2 + 1, k2, 0);
            fontrenderer.drawString(s3, j2 - 1, k2, 0);
            fontrenderer.drawString(s3, j2, k2 + 1, 0);
            fontrenderer.drawString(s3, j2, k2 - 1, 0);
            fontrenderer.drawString(s3, j2, k2, 0xfe0000);
            
            s3 = " XP";
            j2 = (k - fontrenderer.getStringWidth(s3)) / 2 - 102;
            fontrenderer.drawString(s3, j2 + 1, k2, 0);
            fontrenderer.drawString(s3, j2 - 1, k2, 0);
            fontrenderer.drawString(s3, j2, k2 + 1, 0);
            fontrenderer.drawString(s3, j2, k2 - 1, 0);
            fontrenderer.drawString(s3, j2, k2, 0xfeee00);
        }
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            
            ChowTime.harvestXP = ChowTime.saveData.getInteger("harvestXP" + (player).getCommandSenderName());
            ChowTime.harvestLVL = ChowTime.saveData.getInteger("harvestLVL" + (player).getCommandSenderName());
            
            if (event.world.isRemote && !HasBeenNotified)
            {
                if (ChowTime.harvestXP == 0) player.addChatMessage(new ChatComponentTranslation("chat.welcomeMessage"));
                if (RemoteMain.hasUpdated)
                {
                    player.addChatComponentMessage(new ChatComponentTranslation("string.updated"));
                    RemoteMain.hasUpdated = false;
                }
                if (Config.forceLocal)
                {
                    player.addChatComponentMessage(new ChatComponentTranslation("string.warnlocal"));
                }
                if (Config.useDev)
                {
                    player.addChatComponentMessage(new ChatComponentTranslation("string.usedev"));
                }
                RemoteMain.player = player;
                HasBeenNotified = true;
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
            if (event.action == Action.LEFT_CLICK_BLOCK && !(event.entityPlayer instanceof FakePlayer))
            {
                // if(HarvestLevelRegistry.IsInList(event.entityPlayer.worldObj.getBlock(event.x,
                // event.y, event.z)))
                if (event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropStrawberry || event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropBlueberry || event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropCranberry || event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropRaspberry || event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropTomato || event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropCorn || event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropGrape || event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof CropBarley)
                {
                    // This needs to change slightly... if you go from 99 to
                    // 101, you never get the message...
                    if (ChowTime.harvestXP == 20) event.entityPlayer.addChatMessage(new ChatComponentText("chat.HXPGain20"));
                    if (ChowTime.harvestXP == 100) event.entityPlayer.addChatMessage(new ChatComponentText("chat.HXPGain100"));
                    if (ChowTime.harvestXP == 300) event.entityPlayer.addChatMessage(new ChatComponentText("chat.HXPGain300"));
                }
            }
            if (event.action == Action.RIGHT_CLICK_BLOCK && !(event.entityPlayer instanceof FakePlayer) && event.entityPlayer.getHeldItem() != null && event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z) instanceof BlockFarmland)
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
                    event.entityPlayer.addChatMessage(new ChatComponentText("chat.notExperienced"));
                    event.entityPlayer.addChatMessage(new ChatComponentText("chat.gainExperience"));
                }
            }
        }
    }
    
    @SubscribeEvent
    public void useHoe(UseHoeEvent event)
    {
        Block block = event.world.getBlock(event.x, event.y, event.z);
        if ((block instanceof BlockDirt || block instanceof BlockGrass) && event.world.getBlock(event.x, event.y + 1, event.z) instanceof BlockAir)
        {
            event.world.playSoundEffect((double)((float)event.x + 0.5F), (double)((float)event.y + 0.5F), (double)((float)event.z + 0.5F), Blocks.farmland.stepSound.getStepResourcePath(), (Blocks.farmland.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.farmland.stepSound.getPitch() * 0.8F);
            event.current.damageItem(1, event.entityPlayer);
            event.world.setBlock(event.x, event.y, event.z, CTInits.CTFarmland);
            event.current.useItemRightClick(event.world, event.entityPlayer);
        }
    }
}

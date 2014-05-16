package net.jamcraft.chowtime.core.events;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.jamcraft.chowtime.ChowTime;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Code taken from OpenBlocks
 */

public class EntityEventHandler
{
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
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
}
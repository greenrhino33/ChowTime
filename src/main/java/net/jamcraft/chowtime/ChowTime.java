package net.jamcraft.chowtime;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.core.CommonProxy;
import net.jamcraft.chowtime.core.Config;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.dyn.DynMain;
import net.jamcraft.chowtime.remote.RemoteMain;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by James Hollowell on 5/14/2014.
 */
@Mod(modid = ModConstants.MODID, name = ModConstants.NAME)
public class ChowTime
{
    public static CreativeTabs creativeTab = new CreativeTabs("PrisonCraft")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.chainmail_chestplate;
        }
    };

    @Mod.Instance(ModConstants.MODID)
    public static ChowTime instance;

    @SidedProxy(clientSide = "net.jamcraft.chowtime.core.client.ClientProxy", serverSide = "net.jamcraft.chowtime.core.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    //    private File configBase;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
//        FMLInterModComms.sendMessage("Waila", "register", "allout58.mods.prisoncraft.compat.waila.WailaProvider.callbackRegister");

//        channels = NetworkRegistry.INSTANCE.newChannel(ModConstants.MODID, new ChannelHandler());
//        proxy.registerRenderers();
        logger = event.getModLog();

        Config.init(new Configuration(event.getSuggestedConfigurationFile()));
        DynMain.init();
        RemoteMain.init();
        //        configBase=event.getModConfigurationDirectory();

//        MinecraftForge.EVENT_BUS.register(new ConfigToolHighlightHandler());

//        BlockList.init();
//        ItemList.init();
//        TileEntityList.init();

        //NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //FMLInterModComms.sendMessage("prisoncraft", "blacklist", Block.blockRegistry.getNameForObject(Blocks.bookshelf));
    }

    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent event)
    {
        //IMCHandler.HandleIMC(event.getMessages());
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
//        event.registerServerCommand(new JailCommand());
    }

    @Mod.EventHandler
    public void serverUnload(FMLServerStoppingEvent event)
    {
    }
}

package net.jamcraft.chowtime;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.core.*;
import net.jamcraft.chowtime.core.effects.CTPotionEffectHandler;
import net.jamcraft.chowtime.core.events.BucketHandler;
import net.jamcraft.chowtime.core.events.EntityEventHandler;
import net.jamcraft.chowtime.core.items.CTPotions;
//import net.jamcraft.chowtime.core.gen.candyLand.BiomeGenCandyLand;
import net.jamcraft.chowtime.core.materials.CloudMaterial;
import net.jamcraft.chowtime.core.mobs.SeedMob.EntitySeedMob;
import net.jamcraft.chowtime.dyn.DynItems;
import net.jamcraft.chowtime.dyn.DynMain;
import net.jamcraft.chowtime.remote.RemoteMain;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
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
    public static CreativeTabs creativeTab = new CreativeTabs("ChowTime")
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
    
    public static Material cloud = new CloudMaterial();
    
    public static NBTTagCompound saveData = new NBTTagCompound();
    public static File harvestingLVL;
    public static File dir;
    public static int harvestXP = 0;
    public static int harvestLVL = 0;
    
    @SidedProxy(clientSide = "net.jamcraft.chowtime.core.client.ClientProxy", serverSide = "net.jamcraft.chowtime.core.CommonProxy")
    public static CommonProxy proxy;
    
    public static Logger logger;
    
    // private File configBase;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // FMLInterModComms.sendMessage("Waila", "register",
        // "allout58.mods.prisoncraft.compat.waila.WailaProvider.callbackRegister");
        
        // channels = NetworkRegistry.INSTANCE.newChannel(ModConstants.MODID,
        // new ChannelHandler());
        // proxy.registerRenderers();
        logger = event.getModLog();
        
        ObfHelper.init();
        // logger.error("Running in "+ (ObfHelper.isObf?"obf":"deobf") +
        // " environment");
        
        Config.init(new Configuration(event.getSuggestedConfigurationFile()));
        
        RemoteMain.init();
        DynMain.init();
        
        CTRegistry.CTBlocks();
        CTRegistry.CTMachines();
        CTRegistry.CTLiquids();
        CTRegistry.CTCrops();
        CTRegistry.CTItems();
        CTRegistry.CTTileEntities();
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        BucketHandler.INSTANCE.buckets.put(CTInits.ChocolateMilk, CTInits.ItemBucketChoco);
        dir = event.getModConfigurationDirectory();
        // configBase=event.getModConfigurationDirectory();
        
        // MinecraftForge.EVENT_BUS.register(new ConfigToolHighlightHandler());
        
        // BlockList.init();
        // ItemList.init();
        // TileEntityList.init();
        
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // FMLInterModComms.sendMessage("prisoncraft", "blacklist",
        // Block.blockRegistry.getNameForObject(Blocks.bookshelf));
        DynItems.registerRecipes();
        
        proxy.registerRenderers();
        EntityRegistry.registerModEntity(EntitySeedMob.class, "SeedMob", 2, this, 40, 3, true);
        EntityRegistry.addSpawn(EntitySeedMob.class, 5, 10, 30, EnumCreatureType.monster, BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.plains);
    }
    
    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent event)
    {
        // IMCHandler.HandleIMC(event.getMessages());
    }
    
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        // event.registerServerCommand(new JailCommand());
    }
    
    @Mod.EventHandler
    public void serverUnload(FMLServerStoppingEvent event)
    {
    }
}

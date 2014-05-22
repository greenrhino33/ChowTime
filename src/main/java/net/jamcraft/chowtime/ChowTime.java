package net.jamcraft.chowtime;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.core.*;
import net.jamcraft.chowtime.core.events.ConnectionHandler;
import net.jamcraft.chowtime.core.events.EntityEventHandler;
import net.jamcraft.chowtime.core.materials.CloudMaterial;
import net.jamcraft.chowtime.core.mobs.SeedMob.EntitySeedMob;
import net.jamcraft.chowtime.core.network.PacketHandler;
import net.jamcraft.chowtime.dyn.DynItems;
import net.jamcraft.chowtime.dyn.DynMain;
import net.jamcraft.chowtime.remote.RemoteMain;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.util.EnumMap;

//import net.jamcraft.chowtime.core.gen.candyLand.BiomeGenCandyLand;

/**
 * Created by James Hollowell on 5/14/2014.
 */
@Mod(modid = ModConstants.MODID, name = ModConstants.NAME)
public class ChowTime
{
    public static EnumMap<Side, FMLEmbeddedChannel> channels;

    public static CreativeTabs creativeTab = new CreativeTabs("ChowTime")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return CTInits.BarleyCrop;
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
        try
        {
            Class<?> c = Loader.class;
            Field rplField = c.getDeclaredField("canonicalModsDir");
            rplField.setAccessible(true);
            Object rpList = rplField.get(Loader.instance());
            if (rpList instanceof File)
            {
                ModConstants.DYN_LOC = ((File) rpList).getCanonicalPath() + "/ChowTimeDyn";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // FMLInterModComms.sendMessage("Waila", "register",
        // "allout58.mods.prisoncraft.compat.waila.WailaProvider.callbackRegister");

        channels = NetworkRegistry.INSTANCE.newChannel(ModConstants.MODID, new PacketHandler());
        // proxy.registerRenderers();
        logger = event.getModLog();

        ObfHelper.init();
        // logger.error("Running in "+ (ObfHelper.isObf?"obf":"deobf") +
        // " environment");

        Config.init(new Configuration(event.getSuggestedConfigurationFile()));

        RemoteMain.init();
        DynMain.init();

        OreDictionary.registerOre("ingotIron", Items.iron_ingot);

        CTRegistry.CTBlocks();
        CTRegistry.CTMachines();
        CTRegistry.CTLiquids();
        CTRegistry.CTCrops();
        CTRegistry.CTItems();
        CTRegistry.CTTileEntities();
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        //        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        //        BucketHandler.INSTANCE.buckets.put(CTInits.ChocolateMilk, CTInits.ItemBucketChoco);
        dir = event.getModConfigurationDirectory();

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
        FMLCommonHandler.instance().bus().register(new ConnectionHandler());

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CTInits.Juicer, 1, 0), "WBW", "WPW", "ISI", 'W', "plankWood", 'B', Items.glass_bottle, 'P', Blocks.piston, 'I', "ingotIron", 'S', Blocks.stone));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CTInits.Fermenter, 1, 0), "WBW", "WBW", "ISI", 'W', "plankWood", 'B', Items.glass_bottle, 'I', "ingotIron", 'S', Blocks.stone));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CTInits.IceCreamMaker, 1, 0), "CBC","C C","SIS",'C',Blocks.ice,'B', Items.glass_bottle,'I',"ingotIron", 'S',Blocks.stone));

//        GameRegistry.addRecipe(new ItemStack(CTInits.Juicer, 1, 0), "WBW", "WPW", "ISI", 'W', Blocks.planks, 'B', Items.glass_bottle, 'P', Blocks.piston, 'I', Items.iron_ingot, 'S', Blocks.stone);
//        GameRegistry.addRecipe(new ItemStack(CTInits.Fermenter, 1, 0), "WBW", "WBW", "ISI", 'W', Blocks.planks, 'B', Items.glass_bottle, 'I', Items.iron_ingot, 'S', Blocks.stone);
//        GameRegistry.addRecipe(new ItemStack(CTInits.IceCreamMaker, 1, 0), "CBC", "C C", "SIS", 'C', Blocks.ice, 'B', Items.glass_bottle, 'I', Items.iron_ingot, 'S', Blocks.stone);


        DynItems.registerRecipes();

        proxy.registerRenderers();
        EntityRegistry.registerModEntity(EntitySeedMob.class, "SeedMob", 2, this, 40, 3, true);
        EntityRegistry.addSpawn(EntitySeedMob.class, 1, 5, 10, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, BiomeGenBase.plains, BiomeGenBase.beach, BiomeGenBase.coldBeach, BiomeGenBase.frozenRiver);
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

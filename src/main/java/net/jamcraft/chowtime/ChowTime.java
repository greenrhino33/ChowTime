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

package net.jamcraft.chowtime;

import cpw.mods.fml.common.FMLCommonHandler;
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
import net.jamcraft.chowtime.core.commands.ChowTimeCommand;
import net.jamcraft.chowtime.core.events.ConnectionHandler;
import net.jamcraft.chowtime.core.events.EntityEventHandler;
import net.jamcraft.chowtime.core.materials.CloudMaterial;
import net.jamcraft.chowtime.core.mobs.SeedMob.EntitySeedMob;
import net.jamcraft.chowtime.core.network.PacketHandler;
import net.jamcraft.chowtime.core.registrars.SeedRegistry;
import net.jamcraft.chowtime.dyn.DynItems;
import net.jamcraft.chowtime.dyn.DynMain;
import net.jamcraft.chowtime.remote.RemoteMain;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.EnumMap;

//import net.jamcraft.chowtime.core.gen.candyLand.BiomeGenCandyLand;

/**
 * Created by James Hollowell on 5/14/2014.
 */
@Mod(modid = ModConstants.MODID, name = ModConstants.NAME, version = "")
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
//        try
//        {
//            Class<?> c = Loader.class;
//            Field rplField = c.getDeclaredField("canonicalModsDir");
//            rplField.setAccessible(true);
//            Object rpList = rplField.get(Loader.instance());
//            if (rpList instanceof File)
//            {
//                ModConstants.DYN_LOC = ((File) rpList).getCanonicalPath() + "/ChowTimeDyn";
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        // FMLInterModComms.sendMessage("Waila", "register",
        // "allout58.mods.prisoncraft.compat.waila.WailaProvider.callbackRegister");

        channels = NetworkRegistry.INSTANCE.newChannel(ModConstants.MODID, new PacketHandler());
        // proxy.registerRenderers();
        logger = event.getModLog();

        ObfHelper.init();
        // logger.error("Running in "+ (ObfHelper.isObf?"obf":"deobf") +
        // " environment");

        Config.init(new Configuration(event.getSuggestedConfigurationFile()));

        CTRegistry.CTBlocks();
        CTRegistry.CTMachines();
        CTRegistry.CTLiquids();
        CTRegistry.CTCrops();
        CTRegistry.CTItems();
        CTRegistry.CTTileEntities();

        RemoteMain.init();
        DynMain.init();

        OreDictionary.registerOre("ingotIron", Items.iron_ingot);


        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        //        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        //        BucketHandler.INSTANCE.buckets.put(CTInits.ChocolateMilk, CTInits.ItemBucketChoco);
        dir = event.getModConfigurationDirectory();

        // MinecraftForge.EVENT_BUS.register(new ConfigToolHighlightHandler());


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

        DynItems.registerRecipes();

        SeedRegistry.AddSeed((ItemSeeds) CTInits.BarleySeeds);
        SeedRegistry.AddSeed((ItemSeeds) CTInits.BlueberrySeeds);
        SeedRegistry.AddSeed((ItemSeeds) CTInits.CornSeeds);
        SeedRegistry.AddSeed((ItemSeeds) CTInits.CranberrySeeds);
        SeedRegistry.AddSeed((ItemSeeds) CTInits.GrapeSeeds);
        SeedRegistry.AddSeed((ItemSeeds) CTInits.RaspberrySeeds);
        SeedRegistry.AddSeed((ItemSeeds) CTInits.StrawberrySeeds);
        SeedRegistry.AddSeed((ItemSeeds) CTInits.TomatoSeeds);

        proxy.registerRenderers();
        EntityRegistry.registerModEntity(EntitySeedMob.class, "SeedMob", 2, this, 40, 3, true);
        EntityRegistry.addSpawn(EntitySeedMob.class, 5, 2, 3, EnumCreatureType.monster, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, BiomeGenBase.plains, BiomeGenBase.beach, BiomeGenBase.coldBeach, BiomeGenBase.frozenRiver);
    }

    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent event)
    {
        // IMCHandler.HandleIMC(event.getMessages());
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new ChowTimeCommand());
    }

    @Mod.EventHandler
    public void serverUnload(FMLServerStoppingEvent event)
    {
    }
}

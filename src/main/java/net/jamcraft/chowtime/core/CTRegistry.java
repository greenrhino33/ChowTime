package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.blocks.*;
import net.jamcraft.chowtime.core.blocks.liquids.CTFluid;
import net.jamcraft.chowtime.core.blocks.machines.Fermenter;
import net.jamcraft.chowtime.core.blocks.machines.IceCreamMaker;
import net.jamcraft.chowtime.core.blocks.machines.Juicer;
import net.jamcraft.chowtime.core.crops.CropBarley;
import net.jamcraft.chowtime.core.crops.CropGrape;
import net.jamcraft.chowtime.core.crops.CropStrawberry;
import net.jamcraft.chowtime.core.items.*;
import net.jamcraft.chowtime.core.lib.CTStrings;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.jamcraft.chowtime.core.tileentities.TEIceCreamMaker;
import net.jamcraft.chowtime.core.tileentities.TEJuicer;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTRegistry
{

    public static void CTBlocks()
    {
        CTInits.CTLeaves = new CTLeaves().setBlockName(CTStrings.BlockLeaves_Basic);
        GameRegistry.registerBlock(CTInits.CTLeaves, CTStrings.BlockLeaves_Basic);
        CTInits.CTLog = new CTLog().setBlockName(CTStrings.BlockLogs_Basic);
        GameRegistry.registerBlock(CTInits.CTLog, CTStrings.BlockLogs_Basic);
        CTInits.CTSapling = new CTSapling().setBlockName(CTStrings.BlockSapling_Basic);
        GameRegistry.registerBlock(CTInits.CTSapling, CTStrings.BlockSapling_Basic);
        CTInits.IceCream = new CTBlock(Material.snow).setBlockName(CTStrings.BlockIceCream_Strawberry);
        GameRegistry.registerBlock(CTInits.IceCream, CTStrings.BlockIceCream_Strawberry);
        CTInits.CottonCandyBLUE = new BlockCottonCandy().setBlockName(CTStrings.BlockCottonCandy_Blue);
        GameRegistry.registerBlock(CTInits.CottonCandyBLUE, CTStrings.BlockCottonCandy_Blue);
    }

    public static void CTMachines()
    {
        CTInits.Juicer = new Juicer();
        GameRegistry.registerBlock(CTInits.Juicer, "Juicer");
        CTInits.Fermenter = new Fermenter();
        GameRegistry.registerBlock(CTInits.Fermenter, "Fermenter");
        CTInits.IceCreamMaker = new IceCreamMaker();
        GameRegistry.registerBlock(CTInits.IceCreamMaker, "IceCreamMaker");
    }

    public static void CTLiquids()
    {
        CTInits.ChocolateMilkFluid = new Fluid("chocolateMilk");
        FluidRegistry.registerFluid(CTInits.ChocolateMilkFluid);
        CTInits.ChocolateMilk = new CTFluid(CTInits.ChocolateMilkFluid, Material.water, "Fluid_ChocolateMilk").setCreativeTab(ChowTime.creativeTab);
        GameRegistry.registerBlock(CTInits.ChocolateMilk, "Chocolate Milk Fluid");

        //        CTInits.FruitPunchFluid = new Fluid("fruitPunch");
        //        FluidRegistry.registerFluid(CTInits.FruitPunchFluid);
        //        CTInits.FruitPunch = new CTFluid(CTInits.ChocolateMilkFluid, Material.water, "fruitPunch").setCreativeTab(ChowTime.creativeTab);
        //        GameRegistry.registerBlock(CTInits.FruitPunch, "Fruit Punch");
    }

    public static void CTCrops()
    {
        CTInits.CropBarley = new CropBarley();
        GameRegistry.registerBlock(CTInits.CropBarley, "barleyCrop").setBlockName("barleyCrop");
        CTInits.BarleySeeds = new SeedBarley();
        GameRegistry.registerItem(CTInits.BarleySeeds, "barleySeed");

        CTInits.CropStrawberry = new CropStrawberry();
        GameRegistry.registerBlock(CTInits.CropStrawberry, "strawberryCrop").setBlockName("strawberryCrop");
        CTInits.StrawberrySeeds = new SeedStrawberry();
        GameRegistry.registerItem(CTInits.StrawberrySeeds, "strawberrySeed");

        CTInits.CropGrape = new CropGrape();
        GameRegistry.registerBlock(CTInits.CropGrape, "grapeCrop").setBlockName("grapeCrop");
        CTInits.GrapeSeeds = new SeedGrape();
        GameRegistry.registerItem(CTInits.GrapeSeeds, "grapeSeed");
    }

    public static void CTItems()
    {
        CTInits.BarleyCrop = new CTItem().setUnlocalizedName(CTStrings.Item_Barley);
        GameRegistry.registerItem(CTInits.BarleyCrop, CTStrings.Item_Barley);
        CTInits.Strawberry = new ItemStrawberry();
        GameRegistry.registerItem(CTInits.Strawberry, "strawberry");
        CTInits.Grape = new ItemGrape();
        GameRegistry.registerItem(CTInits.Grape, "grape");
        CTInits.ItemBucketChoco = new CTItemBucket(CTInits.ChocolateMilk, CTInits.ItemBucketChoco, "bucket_chocolate").setUnlocalizedName(CTStrings.Item_Bucket);
        GameRegistry.registerItem(CTInits.ItemBucketChoco, CTStrings.Item_Bucket);
    }

    public static void CTDrinks()
    {

    }

    public static void CTTileEntities()
    {
        GameRegistry.registerTileEntity(TEFermenter.class, "TEFermenter");
        GameRegistry.registerTileEntity(TEJuicer.class, "TEJuicer");
        GameRegistry.registerTileEntity(TEIceCreamMaker.class, "TEIceCreamMaker");
    }
}

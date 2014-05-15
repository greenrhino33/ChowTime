package net.jamcraft.chowtime.core;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.blocks.BlockCottonCandy;
import net.jamcraft.chowtime.core.blocks.CTBlock;
import net.jamcraft.chowtime.core.blocks.CTLeaves;
import net.jamcraft.chowtime.core.blocks.CTLog;
import net.jamcraft.chowtime.core.blocks.CTSapling;
import net.jamcraft.chowtime.core.blocks.liquids.CTFluid;
import net.jamcraft.chowtime.core.blocks.machines.Fermenter;
import net.jamcraft.chowtime.core.blocks.machines.Juicer;
import net.jamcraft.chowtime.core.crops.CropBarley;
import net.jamcraft.chowtime.core.items.CTItem;
import net.jamcraft.chowtime.core.items.CTItemBucket;
import net.jamcraft.chowtime.core.items.CTItemFood;
import net.jamcraft.chowtime.core.items.SeedBarley;
import net.jamcraft.chowtime.core.lib.CTStrings;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTRegistry {

    public static void CTBlocks(){
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

    public static void CTMachines(){
        CTInits.Juicer = new Juicer();
        GameRegistry.registerBlock(CTInits.Juicer, "Juicer");
        CTInits.Fermenter = new Fermenter();
        GameRegistry.registerBlock(CTInits.Fermenter, "Fermenter");
    }

    public static void CTLiquids() {
        CTInits.ChocolateMilkFluid = new Fluid(CTStrings.Fluid_ChocolateMilk).setUnlocalizedName(CTStrings.Fluid_ChocolateMilk + "_flow");
        FluidRegistry.registerFluid(CTInits.ChocolateMilkFluid);
        CTInits.ChocolateMilk = new CTFluid(CTInits.ChocolateMilkFluid, Material.water, CTStrings.Fluid_ChocolateMilk).setBlockName(CTStrings.Fluid_ChocolateMilk).setCreativeTab(ChowTime.creativeTab);
        GameRegistry.registerBlock(CTInits.ChocolateMilk, CTStrings.Fluid_ChocolateMilk);

    }

    public static void CTCrops() {
        CTInits.CropBarley = new CropBarley().setBlockName(CTStrings.BlockCrop_Barley);
        GameRegistry.registerBlock(CTInits.CropBarley, CTStrings.BlockCrop_Barley);
        CTInits.BarleySeeds = new SeedBarley().setUnlocalizedName(CTStrings.ItemSeed_Barley);
        GameRegistry.registerItem(CTInits.BarleySeeds, CTStrings.ItemSeed_Barley);
    }

    public static void CTItems(){
        CTInits.BarleyCrop = new CTItem().setUnlocalizedName(CTStrings.Item_Barley);
        GameRegistry.registerItem(CTInits.BarleyCrop, CTStrings.Item_Barley);
        CTInits.IceCreamBall = new CTItem().setUnlocalizedName(CTStrings.ItemIceCream_Strawberry);
        GameRegistry.registerItem(CTInits.IceCreamBall, CTStrings.ItemIceCream_Strawberry);
        CTInits.Cone = new CTItem().setUnlocalizedName(CTStrings.Item_Cone);
        GameRegistry.registerItem(CTInits.Cone, CTStrings.Item_Cone);
        CTInits.ItemCottonCandy = new CTItemFood(3, 2.0F, false).setUnlocalizedName(CTStrings.ItemFood_CottonCandy_Blue);
        GameRegistry.registerItem(CTInits.ItemCottonCandy, CTStrings.ItemFood_CottonCandy_Blue);
        CTInits.ItemBucketChoco = new CTItemBucket(CTInits.ChocolateMilk, CTInits.ItemBucketChoco, "bucket_chocolate").setUnlocalizedName(CTStrings.Item_Bucket);
        GameRegistry.registerItem(CTInits.ItemBucketChoco, CTStrings.Item_Bucket);
    }

    public static void CTTileEntities(){
        GameRegistry.registerTileEntity(TEFermenter.class, "TEFermenter");
    }
}

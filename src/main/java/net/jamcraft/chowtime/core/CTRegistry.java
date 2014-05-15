package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.blocks.*;
import net.jamcraft.chowtime.core.crops.CropBarley;
import net.jamcraft.chowtime.core.items.*;
import net.jamcraft.chowtime.core.liquids.CTFluid;
import net.jamcraft.chowtime.core.machines.Fermenter;
import net.jamcraft.chowtime.core.machines.Juicer;
import net.jamcraft.chowtime.core.tileentities.TEJuicer;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTRegistry {

    public static void CTBlocks(){
        CTInits.CTLeaves = new CTLeaves("leavesBasic");
        GameRegistry.registerBlock(CTInits.CTLeaves, "CTLeaves");
        CTInits.CTLog = new CTLog();
        GameRegistry.registerBlock(CTInits.CTLog, "CTLog");
        CTInits.CTSapling = new CTSapling();
        GameRegistry.registerBlock(CTInits.CTSapling, "CTSaplings");
        CTInits.IceCream = new BlockIceCream();
        GameRegistry.registerBlock(CTInits.IceCream, "IceCream");
        CTInits.CottonCandyBLUE = new BlockCottonCandy().setBlockTextureName(ModConstants.MODID + ":cottonCandy");
        GameRegistry.registerBlock(CTInits.CottonCandyBLUE, "cottonCandyBLUE");
    }

    public static void CTMachines(){
        CTInits.Juicer = new Juicer();
        GameRegistry.registerBlock(CTInits.Juicer, "Juicer");
        CTInits.Fermenter = new Fermenter();
        GameRegistry.registerBlock(CTInits.Fermenter, "Fermenter");
    }

    public static void CTLiquids() {
        CTInits.ChocolateMilkFluid = new Fluid("chocolateMilk");
        FluidRegistry.registerFluid(CTInits.ChocolateMilkFluid);
        CTInits.ChocolateMilk = new CTFluid(CTInits.ChocolateMilkFluid, Material.water, "chocolateMilk").setCreativeTab(ChowTime.creativeTab);
        GameRegistry.registerBlock(CTInits.ChocolateMilk, "Chocolate Milk Fluid");

    }

    public static void CTCrops() {
        CTInits.CropBarley = new CropBarley();
        GameRegistry.registerBlock(CTInits.CropBarley, "barleyCrop");
        CTInits.BarleySeeds = new SeedBarley();
        GameRegistry.registerItem(CTInits.BarleySeeds, "barleySeed");
    }

    public static void CTItems(){
        CTInits.BarleyCrop = new ItemBarley();
        GameRegistry.registerItem(CTInits.BarleyCrop, "barley");
        CTInits.IceCreamBall = new ItemIceCream();
        GameRegistry.registerItem(CTInits.IceCreamBall, "iceCreamBall");
        CTInits.Cone = new ItemCone();
        GameRegistry.registerItem(CTInits.Cone, "Cone");
        CTInits.ItemCottonCandy = new ItemCottonCandy();
        GameRegistry.registerItem(CTInits.ItemCottonCandy, "itemCottonCandy");
    }

    public static void CTTileEntities(){
       // GameRegistry.registerTileEntity(TEJuicer.class, "TEJuicer");
    }
}

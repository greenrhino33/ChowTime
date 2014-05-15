package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.blocks.CTLeaves;
import net.jamcraft.chowtime.core.blocks.CTLog;
import net.jamcraft.chowtime.core.liquids.CTFluid;
import net.jamcraft.chowtime.core.machines.Fermenter;
import net.jamcraft.chowtime.core.machines.Juicer;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTRegistry {

    public static void CTBlocks(){
        CTInits.CTLeaves = new CTLeaves();
        GameRegistry.registerBlock(CTInits.CTLeaves, "CTLeaves");
        CTInits.CTLog = new CTLog();
        GameRegistry.registerBlock(CTInits.CTLog, "CTLog");
    }

    public static void CTMachines(){
        CTInits.Juicer = new Juicer();
        GameRegistry.registerBlock(CTInits.Juicer, "Juicer");
        CTInits.Fermenter = new Fermenter();
        GameRegistry.registerBlock(CTInits.Fermenter, "Fermenter");
    }

    public static void CTLiquids() {
        CTInits.ChocolateMilkFluid = new Fluid("ChocolateMilk");
        FluidRegistry.registerFluid(CTInits.ChocolateMilkFluid);
        CTInits.ChocolateMilk = new CTFluid(CTInits.ChocolateMilkFluid, Material.water, "chocolateMilk").setCreativeTab(ChowTime.creativeTab);
        GameRegistry.registerBlock(CTInits.ChocolateMilk, "Chocolate Milk Fluid");

    }
}

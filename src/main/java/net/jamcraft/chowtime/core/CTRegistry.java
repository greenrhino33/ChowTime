package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.core.blocks.CTLeaves;
import net.jamcraft.chowtime.core.blocks.CTLog;
import net.jamcraft.chowtime.core.machines.Fermenter;
import net.jamcraft.chowtime.core.machines.Juicer;

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
}

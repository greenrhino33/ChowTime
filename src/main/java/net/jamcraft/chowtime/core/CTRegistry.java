package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.core.blocks.CTLeaves;
import net.jamcraft.chowtime.core.blocks.CTLog;

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
}

package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.core.blocks.CTLeaves;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTRegistry {

    public static void CTBlocks(){
        CTInits.CTLeaves = new CTLeaves();
        GameRegistry.registerBlock(CTInits.CTLeaves, "CTLeaves");
    }
}

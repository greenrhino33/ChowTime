package net.jamcraft.chowtime.core.client;

import net.jamcraft.chowtime.core.CommonProxy;
import net.jamcraft.chowtime.core.renders.TileEntityFermenterRenderer;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class ClientProxy extends CommonProxy
{
	public void registerRenderers()
    {
		ClientRegistry.bindTileEntitySpecialRenderer(TEFermenter.class, new TileEntityFermenterRenderer());
    }
}

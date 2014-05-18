package net.jamcraft.chowtime.core.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.jamcraft.chowtime.core.CommonProxy;
import net.jamcraft.chowtime.core.mobs.GingerbreadMan.EntityGingerbreadMan;
import net.jamcraft.chowtime.core.mobs.GingerbreadMan.ModelGingerbreadMan;
import net.jamcraft.chowtime.core.mobs.GingerbreadMan.RenderGingerbreadMan;
import net.jamcraft.chowtime.core.mobs.SeedMob.EntitySeedMob;
import net.jamcraft.chowtime.core.mobs.SeedMob.ModelSeedMob;
import net.jamcraft.chowtime.core.mobs.SeedMob.RenderSeedMob;
import net.jamcraft.chowtime.core.renders.TileEntityFermenterRenderer;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class ClientProxy extends CommonProxy
{
    public void registerRenderers()
    {
        super.registerRenderers();
        ClientRegistry.bindTileEntitySpecialRenderer(TEFermenter.class, new TileEntityFermenterRenderer());

        RenderingRegistry.registerEntityRenderingHandler(EntitySeedMob.class, new RenderSeedMob(new ModelSeedMob(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGingerbreadMan.class, new RenderGingerbreadMan(new ModelGingerbreadMan(), 0.5F));
    }
}

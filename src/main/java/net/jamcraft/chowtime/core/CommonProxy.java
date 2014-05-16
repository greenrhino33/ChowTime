package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.jamcraft.chowtime.core.mobs.SeedMob.EntitySeedMob;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class CommonProxy
{
    public void registerRenderers()
    {
        EntityRegistry.registerGlobalEntityID(EntitySeedMob.class, "SeedMob", EntityRegistry.findGlobalUniqueEntityId(), 2, 0);
    }
}

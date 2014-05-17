package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.jamcraft.chowtime.core.client.GuiFermenter;
import net.jamcraft.chowtime.core.client.GuiJuicer;
import net.jamcraft.chowtime.core.container.ContainerFermenter;
import net.jamcraft.chowtime.core.container.ContainerJuicer;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.jamcraft.chowtime.core.tileentities.TEIceCreamMaker;
import net.jamcraft.chowtime.core.tileentities.TEJuicer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.jamcraft.chowtime.core.mobs.SeedMob.EntitySeedMob;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class CommonProxy implements IGuiHandler
{
    public void registerRenderers()
    {
        EntityRegistry.registerGlobalEntityID(EntitySeedMob.class, "SeedMob", EntityRegistry.findGlobalUniqueEntityId(), 2, 0);
    }
    
    @Override public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID== GuiIDS.Fermenter_Gui)
        {
            TEFermenter te=(TEFermenter) world.getTileEntity(x,y,z);
            return new ContainerFermenter(player.inventory,te);
        }
        if(ID==GuiIDS.Juicer_Gui)
        {
            TEJuicer te=(TEJuicer) world.getTileEntity(x,y,z);
            return new ContainerJuicer(player.inventory,te);
        }
        if(ID==GuiIDS.ICMaker_Gui)
        {
            TEIceCreamMaker te=(TEIceCreamMaker) world.getTileEntity(x,y,z);
            return new ContainerICMaker(player.inventory,te);
        }
        return null;
    }

    @Override public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID==GuiIDS.Fermenter_Gui)
        {
            TEFermenter te=(TEFermenter) world.getTileEntity(x,y,z);
            return new GuiFermenter(player.inventory,te);
        }
        if(ID==GuiIDS.Juicer_Gui)
        {
            TEJuicer te=(TEJuicer) world.getTileEntity(x,y,z);
            return new GuiJuicer(player.inventory,te);
        }
        if(ID==GuiIDS.ICMaker_Gui)
        {
            TEIceCreamMaker te=(TEIceCreamMaker) world.getTileEntity(x,y,z);
            return new GuiICMaker(player.inventory,te);
        }
        return null;
    }
}

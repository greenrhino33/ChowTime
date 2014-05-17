package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.jamcraft.chowtime.core.client.GuiFermenter;
import net.jamcraft.chowtime.core.client.GuiJuicer;
import net.jamcraft.chowtime.core.container.ContainterFermenter;
import net.jamcraft.chowtime.core.container.ContainterJuicer;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
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
            return new ContainterFermenter(player.inventory,te);
        }
        if(ID==GuiIDS.Juicer_Gui)
        {
            TEJuicer te=(TEJuicer) world.getTileEntity(x,y,z);
            return new ContainterJuicer(player.inventory,te);
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
        return null;
    }
}

package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.network.IGuiHandler;
import net.jamcraft.chowtime.core.client.GuiFermenter;
import net.jamcraft.chowtime.core.container.ContainterFermenter;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class CommonProxy implements IGuiHandler
{
    public void registerRenderers()
    {
    }

    @Override public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID== GuiIDS.Fermenter_Gui)
        {
            TEFermenter te=(TEFermenter) world.getTileEntity(x,y,z);
            return new ContainterFermenter(player.inventory,te);
        }
        return null;
    }

    @Override public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID=GuiIDS.Fermenter_Gui)
        {
            TEFermenter te=(TEFermenter) world.getTileEntity(x,y,z);
            return new GuiFermenter(player.inventory,te);
        }
        return null;
    }
}

package net.jamcraft.chowtime.core.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

/**
 * Created by James Hollowell on 5/18/2014.
 */
public class ConnectionHandler
{
    @SubscribeEvent
    public void clientConnected(FMLNetworkEvent.ClientConnectedToServerEvent e)
    {
        if(!e.isLocal)
        {

        }
    }
}

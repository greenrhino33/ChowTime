package net.jamcraft.chowtime.core.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.network.SHA1Packet;
import net.jamcraft.chowtime.remote.RemoteMain;

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
            ChowTime.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
            ChowTime.channels.get(Side.CLIENT).writeOutbound(new SHA1Packet(RemoteMain.localHash));
        }
    }

    @SubscribeEvent
    public void connectToServer(FMLNetworkEvent.ServerConnectionFromClientEvent e)
    {
        if (!e.isLocal)
        {
            if(!RemoteMain.isSyncedWithServer) e.setCanceled(true);
        }
    }
}

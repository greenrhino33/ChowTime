package net.jamcraft.chowtime.core.events;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.ChannelFutureListener;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.network.SHA1Packet;
import net.jamcraft.chowtime.remote.RemoteMain;

/**
 * Created by James Hollowell on 5/18/2014.
 */
public class ConnectionHandler
{
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        ChowTime.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        ChowTime.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(event.player);
        ChowTime.channels.get(Side.SERVER).writeAndFlush(new SHA1Packet(RemoteMain.localHash)).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    @SubscribeEvent
    public void connectToServer(FMLNetworkEvent.ServerConnectionFromClientEvent e)
    {
        if (!e.isLocal)
        {
            if(!RemoteMain.isSyncedWithServer)
            {
//                e.setCanceled(true);
            }
        }
    }
}

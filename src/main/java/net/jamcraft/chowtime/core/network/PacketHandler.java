package net.jamcraft.chowtime.core.network;

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by James Hollowell on 5/18/2014.
 */
public class PacketHandler extends FMLIndexedMessageToMessageCodec<IPacket>
{
    public PacketHandler()
    {
        addDiscriminator(0, SHA1Packet.class);

    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, IPacket msg, ByteBuf target)
            throws Exception
    {
        msg.writeBytes(target);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, IPacket msg)
    {
        msg.readBytes(source);
        msg.postProcess();
    }
}

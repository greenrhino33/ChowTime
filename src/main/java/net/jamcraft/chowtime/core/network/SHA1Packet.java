package net.jamcraft.chowtime.core.network;

import io.netty.buffer.ByteBuf;
import net.jamcraft.chowtime.remote.RemoteMain;

/**
 * Created by James Hollowell on 5/18/2014.
 */
public class SHA1Packet implements IPacket
{
    private String sha1;

    public SHA1Packet()
    {}

    public SHA1Packet(String hash)
    {
        sha1=hash;
    }

    @Override public void readBytes(ByteBuf bytes)
    {
        sha1=NetworkUtils.readString(bytes);
    }

    @Override public void writeBytes(ByteBuf bytes)
    {
        NetworkUtils.writeString(bytes,sha1);
    }

    @Override public void postProcess()
    {
        RemoteMain.IsSyncedWithServer(sha1);
    }
}

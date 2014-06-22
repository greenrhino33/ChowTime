/*
 * ChowTime - Dynamically updating food mod for Minecraft
 *     Copyright (C) 2014  Team JamCraft
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

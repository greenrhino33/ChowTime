package net.jamcraft.chowtime.core.network;

import io.netty.buffer.ByteBuf;
import net.jamcraft.chowtime.ChowTime;

public class NetworkUtils
{
    public static void writeString(ByteBuf buff, String string)
    {
        if (string != null)
        {
            int size = string.length();
            buff.writeInt(size);
            for (int i = 0; i < size; i++)
            {
                buff.writeChar(string.toCharArray()[i]);
            }
        }
        else
        {
            ChowTime.logger.error("Trying to write a null string", new Throwable());
        }
    }

    public static String readString(ByteBuf buff)
    {
        int size = buff.readInt();
        String s = "";
        for (int i = 0; i < size; i++)
        {
            s += buff.readChar();
        }
        return s;
    }
}

package net.jamcraft.chowtime.core.client;

/**
 * Created by James Hollowell on 5/22/2014.
 */
public class GuiHelper
{
    public static int getIntFromRGBA(int r, int g, int b, int a)
    {
        return (r & 255) << 24 | (g & 255) << 16 | (b & 255) << 8 | (a & 255);
    }
}

package net.jamcraft.chowtime.core;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class ObfHelper
{
    public static boolean isObf=true;

    public static void init()
    {
        try
        {
            Class.forName("net.minecraft.block.Block");
            isObf=false;
        }
        catch (Exception e)
        {
            isObf=true;
        }
    }
}

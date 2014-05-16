package net.jamcraft.chowtime.core;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class ObfHelper
{
    public static boolean isObf=false;

    public static void init()
    {
        try
        {
            Class.forName("net.minecraft.block.Block");
            isObf=true;
        }
        catch (Exception e)
        {
            isObf=false;
        }
    }
}

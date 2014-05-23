package net.jamcraft.chowtime.core;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class ModConstants
{
    public static final String MODID = "chowtime";
    public static final String NAME = "ChowTime";
    public static String DYN_LOC;

    public static final int Dim_ID_CandyLand = 18;

    static
    {
        try
        {
            DYN_LOC=new File("mods/ChowTimeDyn").getCanonicalPath();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

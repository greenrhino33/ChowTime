package net.jamcraft.chowtime.core;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class Config
{
    //and public static vars here
    public static String remoteLoc;

    public static void init(Configuration conf)
    {
        conf.load();
        conf.get("Dynamic","RemoteLocation","http://jam-craft.github.io/ChowTime/");
        conf.save();
    }
}

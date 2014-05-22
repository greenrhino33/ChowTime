package net.jamcraft.chowtime.core;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class Config
{
    //and public static vars here
    public static String remoteLoc;
    public static boolean forceLocal;
    public static boolean useDev;

    private static Configuration config;

    public static void init(Configuration conf)
    {
        config = conf;
        conf.load();
        remoteLoc = conf.get("Dynamic", "RemoteLocation", "http://jam-craft.github.io/ChowTime/").getString();
        forceLocal = conf.get("Dynamic", "ForceLocal", false).getBoolean(false);
        useDev=conf.get("Dynamic","UseDevVersions",false).getBoolean(false);
        remoteLoc=(useDev?"dev/":"")+remoteLoc;
        conf.save();
    }

    public static void save()
    {
        config.removeCategory(config.getCategory("Dynamic"));
        config.get("Dynamic", "RemoteLocation", remoteLoc);
        config.get("Dynamic", "ForceLocal", forceLocal);
        config.get("Dynamic", "useDev", useDev);
        config.save();
    }
}

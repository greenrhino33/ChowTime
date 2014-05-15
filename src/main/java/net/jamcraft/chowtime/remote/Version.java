package net.jamcraft.chowtime.remote;

import scala.Int;

/**
 * Created by James Hollowell on 5/15/2014.
 */
public class Version
{
    private int Major=0;
    private int Minor=0;
    private int Build=0;

    public int getMajor()
    {
        return Major;
    }

    public int getMinor()
    {
        return Minor;
    }

    public int getBuild()
    {
        return Build;
    }

    public Version(int major, int minor, int build)
    {
        Major = major;
        Minor = minor;
        Build = build;
    }

    @Override public String toString()
    {
        return Major+"."+Minor+"."+Build;
    }

    public void readFromString(String string)
    {
        Major=Integer.parseInt(string.split('.')[0]);
        Minor =Integer.parseInt(string.split('.')[1]);
        Build= Integer.parseInt(string.split('.')[2]);
    }
}

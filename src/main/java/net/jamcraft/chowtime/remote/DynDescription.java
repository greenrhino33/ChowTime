package net.jamcraft.chowtime.remote;

/**
 * Created by James Hollowell on 5/15/2014.
 */
public class DynDescription
{
    public Version version;

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DynDescription)) return false;
        DynDescription other = (DynDescription) o;
        return other.version.equals(version));
    }
}

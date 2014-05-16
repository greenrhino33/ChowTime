package net.jamcraft.chowtime.remote;

/**
 * Created by James Hollowell on 5/15/2014.
 */
public class DynResourceDescription extends DynDescription
{
    public String path;

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DynResourceDescription)) return false;
        DynResourceDescription other = (DynResourceDescription) o;
        return other.version.equals(version) && other.path.equals(path);
    }
}

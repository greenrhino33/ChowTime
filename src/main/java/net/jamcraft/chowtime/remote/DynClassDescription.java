package net.jamcraft.chowtime.remote;

/**
 * Created by James Hollowell on 5/15/2014.
 */
public class DynClassDescription extends DynDescription
{
    public String classname;

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DynClassDescription)) return false;
        DynClassDescription other = (DynClassDescription) o;
        return other.version.equals(version) && other.classname.equals(classname);
    }
}

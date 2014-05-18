package net.jamcraft.chowtime.dyn.common;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public interface IDynItem
{
    /**
     * @return Name to register this item with Forge.
     */
    String getRegistrationName();

    void registerRecipe();
}

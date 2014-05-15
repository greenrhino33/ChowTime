package net.jamcraft.chowtime.dyn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class DynMain
{
    public static void init()
    {
        DynTextures.addDynTP();

        DynItems.registerRecipes();
    }
}

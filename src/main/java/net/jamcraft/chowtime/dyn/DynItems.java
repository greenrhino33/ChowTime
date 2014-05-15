package net.jamcraft.chowtime.dyn;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.item.Item;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class DynItems
{
    public Map<String, Item> items = new HashMap<String, Item>();

    public static void init()
    {
        loadList();
    }

    /**
     * Add
     */

    private static void loadList()
    {
        File dynLoc = new File(ModConstants.DYN_LOC);
        if (!dynLoc.exists())
        {
            //Error?
        }
        try
        {
            ClassLoader loader = new URLClassLoader(new URL[] { dynLoc.toURI().toURL() }, DynItems.class.getClassLoader());

            for (String classname : DynMain.load)
            {
                Class<?> clazz = loader.loadClass(classname);

                if (IDynItem.class.isAssignableFrom(clazz))
                {
                    Object o = null;
                    if (clazz.isInterface()) continue;
                    if (Modifier.isAbstract(clazz.getModifiers()))
                        continue;
                    if (clazz.getConstructor((Class<?>[]) null) != null)
                        o = clazz.newInstance();

                    if (o instanceof IDynItem && o instanceof Item)
                    {
                        String rn = ((IDynItem) o).getRegistrationName();
                        GameRegistry.registerItem(((Item) o), rn);
                        ((IDynItem) o).registerRecipe();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

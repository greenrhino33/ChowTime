package net.jamcraft.chowtime.dyn;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.jamcraft.chowtime.remote.DynClassDescription;
import net.jamcraft.chowtime.remote.DynDescription;
import net.jamcraft.chowtime.remote.RemoteMain;
import net.minecraft.item.Item;

import java.io.File;
import java.io.FileNotFoundException;
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
    public static Map<String, Item> items = new HashMap<String, Item>();

    /**
     * Add all the required classes to the classpath and register them with Forge
     */

    public static void loadList()
    {
        File dynLoc = new File(ModConstants.DYN_LOC);
        if (!dynLoc.exists())
        {
            try
            {
                throw new FileNotFoundException();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            ClassLoader loader = new URLClassLoader(new URL[] { dynLoc.toURI().toURL() }, DynItems.class.getClassLoader());

            for (DynDescription desc : RemoteMain.local.getObjects())
            {
                if (!(desc instanceof DynClassDescription)) continue;
                String classname = ((DynClassDescription) desc).classname;
                ChowTime.logger.error("Loading new item: " + classname);
                //Actually load the class
                Class<?> clazz = loader.loadClass(classname);

                if (IDynItem.class.isAssignableFrom(clazz))
                {
                    //Initialize the obj and do checks to make sure it is usable.
                    Object o = null;
                    if (clazz.isInterface()) continue;
                    if (Modifier.isAbstract(clazz.getModifiers()))
                        continue;
                    if (clazz.getConstructor((Class<?>[]) null) != null)
                        o = clazz.newInstance();

                    //Do the actual registration stuff
                    if (o instanceof IDynItem && o instanceof Item)
                    {
                        //Register with Forge
                        String rn = ((IDynItem) o).getRegistrationName();
                        GameRegistry.registerItem(((Item) o), rn);

                        //Add the item to our list
                        items.put(rn, (Item) o);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void registerRecipes()
    {
        for (int i = 0; i < items.size(); i++)
        {
            String key = (String) items.keySet().toArray()[i];
            Item item = items.get(key);
            ((IDynItem) item).registerRecipe();
        }
    }
}

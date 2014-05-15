package net.jamcraft.chowtime.dyn;

import cpw.mods.fml.common.registry.GameRegistry;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.item.Item;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
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

        for (String classname : DynMain.load)
        {
            try
            {
                String n = classname.replace('.', '/');
                String path = dynLoc.getCanonicalPath() + "/" + n.substring(0, n.lastIndexOf("/"));
                File dir = new File(path);

                if (dir.isDirectory())
                {
                    ClassLoader loader = null;
                    try
                    {
                        loader = new URLClassLoader(new URL[] { dir.toURI().toURL() }, DynItems.class.getClassLoader());
                    }
                    catch (MalformedURLException e)
                    {
                        e.printStackTrace();
                    }

                    for (File f : dir.listFiles())
                    {
                        if (f.isDirectory())
                        {

                        }
                        if (f.getName().endsWith(".class"))
                        {
                            String name = f.getAbsolutePath().substring(dynLoc.getAbsolutePath().length() + 1, f.getAbsolutePath().length() - 6);
                            name = name.replace("\\", ".");
                            Class<?> clazz = loader.loadClass(name);
                            //Object o = null;
                /*
                URL url = cl.toURI().toURL();
                URL[] urls=new URL[]{url};
                ClassLoader classLoader=new URLClassLoader(urls,DynItems.class.getClassLoader());
                Class<?> clazz=classLoader.loadClass(classname);*/
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
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

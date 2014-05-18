package net.jamcraft.chowtime.dyn;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.client.resources.IResourcePack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class DynTextures
{
    public static void addDynTP()
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            //Make sure we have a folder to work with.
            File dirLoad = new File(ModConstants.DYN_LOC);
            if (!dirLoad.exists())
            {
                dirLoad.mkdir();
            }

            //Add a pack.mcmeta if there isn't one.
            File mcmeta = new File(ModConstants.DYN_LOC + "/pack.mcmeta");
            if (!mcmeta.exists())
            {
                try
                {
                    mcmeta.createNewFile();
                    FileWriter fw = new FileWriter(mcmeta);
                    fw.write("{\n" + " \"pack\": {\n" + "   \"description\": \"dummy pack for ChowTime DynResources\",\n" + "   \"pack_format\": 1\n" + "}\n" + "}");
                    fw.close();
                }
                catch (IOException e)
                {
                }
            }

            //HAKS!!! Get the Resource Pack list from FML. Reflection for the WIN!
            try
            {
                Class<?> c = FMLClientHandler.class;
                Field rplField = c.getDeclaredField("resourcePackList");
                rplField.setAccessible(true);
                Object rpList = rplField.get(FMLClientHandler.instance());
                if (rpList instanceof List<?>)
                {
                    IResourcePack rp = new DynFolderResourcePack(dirLoad);
                    ((List) rpList).add(rp);
                }
              //  FMLClientHandler.instance().updateResourcePackList();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

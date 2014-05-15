package net.jamcraft.chowtime.dyn;

import net.minecraft.client.resources.FolderResourcePack;

import java.io.File;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class DynFolderResourcePack extends FolderResourcePack
{
    public DynFolderResourcePack(File par1File)
    {
        super(par1File);
    }

    @Override
    public String getPackName()
    {
        return "DynamicResourcePack:ChowTime";
    }
}

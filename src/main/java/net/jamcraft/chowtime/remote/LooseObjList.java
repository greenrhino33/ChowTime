package net.jamcraft.chowtime.remote;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/15/2014.
 */
public class LooseObjList
{
    protected List<DynClassDescription> descriptions=new ArrayList<DynClassDescription>();

    public List<DynClassDescription> getObjects()
    {
        return descriptions;
    }

    public void writeToJson(File out) throws IOException, JsonIOException
    {
        if(!out.exists())out.createNewFile();
        Gson gson=new Gson();
        gson.toJson(descriptions,descriptions.getClass(),new FileWriter(out));
    }

    public void readFromJson(String jsonText)
    {
        Gson gson=new Gson();
        descriptions=gson.fromJson(jsonText,descriptions.getClass());
    }
}

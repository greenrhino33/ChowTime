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

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof LooseObjList)) return false;

        LooseObjList other=(LooseObjList)o;

        //If they aren't the same size, they obviously aren't the same...
        if(this.getObjects().size()!=other.getObjects().size()) return false;

        for(int i=0;i<descriptions.size();i++)
        {
            if(!descriptions.contains(other.getObjects().get(i))) return false;
        }

        return true;
    }

    public List<DynClassDescription> difference(LooseObjList other)
    {
        List<DynClassDescription> diff=new ArrayList<DynClassDescription>();
        for(DynClassDescription desc:)
    }
}

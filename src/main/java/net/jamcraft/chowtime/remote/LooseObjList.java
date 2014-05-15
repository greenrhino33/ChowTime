package net.jamcraft.chowtime.remote;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.*;
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

    public void writeToFile(File out) throws IOException, JsonIOException
    {
        if(!out.exists())out.createNewFile();
        String
        FileWriter fw=new FileWriter(out);
        fw.write(s);
        fw.close();
    }

    public void readFromFile(File in)
    {
        try
        {
            FileReader fr = new FileReader(in);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready())

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
        for(DynClassDescription desc: descriptions)
        {
            if(!other.getObjects().contains(desc)) diff.add(desc);
        }
        return diff;
    }

    public void add(DynClassDescription desc)
    {
        descriptions.add(desc);
    }
}

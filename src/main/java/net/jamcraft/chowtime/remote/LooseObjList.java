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
    protected List<DynDescription> descriptions = new ArrayList<DynDescription>();

    public List<DynDescription> getObjects()
    {
        return descriptions;
    }

    public void writeToFile(File out) throws IOException, JsonIOException
    {
        /* FILE DESCRIPTION:
        *********************************
        classes
        {
        net.jamcraft.chowtime. 0.0.1
        ...
        }
        resources
        {
        //based in DYN_LOC/assets/chowtime
        textures/items/Temp.png 0.0.1
        lang/en_US.lang 0.0.1
        }
        ***********************************
         */

        

        if(!out.exists())out.createNewFile();
        FileWriter fw=new FileWriter(out);
        BufferedWriter bw=new BufferedWriter(fw);

        bw.write("classes\n{\n");

        for (DynDescription desc : descriptions)
        {

        }

        String s = desc.classname + " " + desc.version.toString();
        bw.write(s);
        bw.newLine();

        bw.close();
    }

    public void readFromFile(File in)
    {
        try
        {
            FileReader fr = new FileReader(in);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready())
            {
                String line=br.readLine();
                DynClassDescription desc=new DynClassDescription();
                desc.classname=line.split(" ")[0];
                desc.version=new Version(0,0,0);
                desc.version.readFromString(line.split(" ")[1]);
                descriptions.add(desc);
            }
            br.close();
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

    public List<DynDescription> difference(LooseObjList other)
    {
        List<DynDescription> diff = new ArrayList<DynDescription>();
        for (DynDescription desc : descriptions)
        {
            if(!other.getObjects().contains(desc)) diff.add(desc);
        }
        return diff;
    }

    public void add(DynDescription desc)
    {
        descriptions.add(desc);
    }
}

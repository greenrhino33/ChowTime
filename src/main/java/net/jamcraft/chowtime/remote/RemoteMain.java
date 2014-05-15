package net.jamcraft.chowtime.remote;

import com.google.gson.JsonIOException;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.Config;
import net.jamcraft.chowtime.core.ModConstants;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class RemoteMain
{
    private static LooseObjList local = new LooseObjList();
    private static LooseObjList remote = new LooseObjList();

    public static void init()
    {
//        DynClassDescription desc=new DynClassDescription();
//        desc.classname="net.jamcraft.chowtime.dyn.items.Temp";
//        desc.version=new Version(0,0,1);
//        local.add(desc);
//
//        File f = new File(ModConstants.DYN_LOC + "/local.json");
//        try
//        {
//            local.writeToFile(f);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        LoadLocal();
        for(DynClassDescription desc: local.getObjects())
        {
            System.out.println("Local Desc: v:"+desc.version.toString()+" cn:"+desc.classname);
        }
        LoadRemote();
        for(DynClassDescription desc: remote.getObjects())
        {
            System.out.println("Remote Desc: v:"+desc.version.toString()+" cn:"+desc.classname);
        }
    }

    public static boolean LoadLocal()
    {
        File f = new File(ModConstants.DYN_LOC + "/local.json");
        local.readFromFile(f);
        return true;
    }

    public static boolean LoadRemote()
    {
        try
        {
            URL url = new URL(Config.remoteLoc + "/dyn/current.json");
            URLConnection con = url.openConnection();
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader br=new BufferedReader(isr);
            File dyn=new File(ModConstants.DYN_LOC+"/remote.json");
            if(!dyn.exists())dyn.createNewFile();
            FileWriter fw=new FileWriter(dyn);
            while(br.ready())
            {
                fw.write(br.readLine());
            }

            remote.readFromFile(dyn);
            return true;
        }
        catch (IOException e)
        {
            ChowTime.logger.error("Error reading remote JSON file; falling back to local only");
        }
        catch (JsonIOException je)
        {
            ChowTime.logger.error("Error parsing remote JSON file; falling back to local only");
        }

        return false;
    }
}

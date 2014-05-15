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
//            local.writeToJson(f);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        LoadLocal();
        for(DynClassDescription desc: local.getObjects())
        {
            ChowTime.logger.debug("Desc: v:"+desc.version.toString()+" cn:"+desc.classname);
        }
        //LoadRemote();
    }

    public static boolean LoadLocal()
    {
        try
        {
            File f = new File(ModConstants.DYN_LOC + "/local.json");
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String json = br.readLine();
            local.readFromJson(json);
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

    public static boolean LoadRemote()
    {
        try
        {
            URL url = new URL(Config.remoteLoc + "/dyn/current.json");
            URLConnection con = url.openConnection();
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String json = br.readLine();
            remote.readFromJson(json);
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

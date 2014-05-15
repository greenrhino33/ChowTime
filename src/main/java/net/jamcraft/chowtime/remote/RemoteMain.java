package net.jamcraft.chowtime.remote;

import com.google.gson.JsonIOException;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.Config;
import net.jamcraft.chowtime.core.ModConstants;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by James Hollowell on 5/14/2014.
 */
public class RemoteMain
{
    public static LooseObjList local = new LooseObjList();
    private static LooseObjList remote = new LooseObjList();

    public static void init()
    {
        LoadLocal();
        LoadRemote();
        if(!local.equals(remote))
        {
            //Download the classes that need to be updated
            List<DynClassDescription> list = remote.difference(local);
            for(DynClassDescription desc:list)
            {
                DownloadClass(desc);
            }

            //Update local file
            try
            {
                remote.writeToFile(new File(ModConstants.DYN_LOC + "/local.json"));
            }
            catch (IOException ioe)
            {
                ChowTime.logger.error("Could not update the local file from the remote location");
            }
            //Reload local
            LoadLocal();
        }
    }

    public static boolean LoadLocal()
    {
        local.getObjects().clear();
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
                fw.write("\n");
            }
            fw.close();

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

    public static void DownloadClass(DynClassDescription desc)
    {
        String newPath = "/" + desc.classname.replace('.', '/')+".class";

        try
        {
            final int blk_size = 1024;
            URL url = new URL(Config.remoteLoc + "dyn/current" + newPath);
            URLConnection con = url.openConnection();
            InputStream reader = url.openStream();
            File f=new File(ModConstants.DYN_LOC + newPath);
            if(!f.exists())
            {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            FileOutputStream writer = new FileOutputStream(ModConstants.DYN_LOC + newPath);
            int total = con.getContentLength();
            int size_dl = 0;
            byte[] buffer = new byte[blk_size];
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) > 0)
            {
                size_dl += bytesRead;
                writer.write(buffer, 0, bytesRead);
                buffer = new byte[blk_size];
            }
            writer.close();
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

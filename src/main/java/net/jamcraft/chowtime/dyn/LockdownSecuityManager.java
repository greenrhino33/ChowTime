/*
 * ChowTime - Dynamically updating food mod for Minecraft
 *     Copyright (C) 2014  Team JamCraft
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jamcraft.chowtime.dyn;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;
import java.util.logging.LoggingPermission;

/**
 * Created by James Hollowell on 5/22/2014.
 */
public class LockdownSecuityManager extends SecurityManager
{
    public static LockdownSecuityManager instance = new LockdownSecuityManager();

    public void checkPermission(Permission perm)
    {
        if (perm != null)
        {
            //Allows the loggger through, allows us to reset the security manager to null
            if (perm.getName() != "setSecurityManager" && perm.getName() != "suppressAccessChecks" && perm.getName() != "getClassLoader" && (perm instanceof LoggingPermission && perm.getName() != "control"))
            {
                super.checkPermission(perm);
            }
        }
        else
        {
            super.checkPermission(perm);
        }
    }

    @Override
    public void checkRead(String file)
    {
        throw new SecurityException("checkread-Lockdown");
    }

    @Override
    public void checkRead(FileDescriptor fd)
    {
        throw new SecurityException("checkread-Lockdown");
    }

    @Override
    public void checkRead(String file, Object context)
    {
        throw new SecurityException("checkread-Lockdown");
    }

    @Override
    public void checkCreateClassLoader()
    {
        throw new SecurityException("checkCreateClassLoader-Lockdown");
    }

    @Override
    public void checkAccess(Thread t)
    {
        throw new SecurityException("checkAccess-Lockdown");
    }

    @Override
    public void checkAccess(ThreadGroup g)
    {
        throw new SecurityException("checkAccess-Lockdown");
    }

    @Override
    public void checkExit(int status)
    {
        throw new SecurityException("checkExit-Lockdown-" + status);
    }

    @Override
    public void checkExec(String cmd)
    {
        throw new SecurityException("checkExec-Lockdown-" + cmd);
    }

    @Override
    public void checkLink(String lib)
    {
        throw new SecurityException("checkLink-Lockdown-" + lib);
    }

    @Override
    public void checkWrite(FileDescriptor fd)
    {
        throw new SecurityException("checkWrite-Lockdown");
    }

    @Override
    public void checkWrite(String file)
    {
        throw new SecurityException("checkWrite-Lockdown");
    }

    @Override
    public void checkDelete(String file)
    {
        throw new SecurityException("checkDelete-Lockdown");
    }

    @Override
    public void checkConnect(String host, int port)
    {
        throw new SecurityException("checkConnect-Lockdown-" + host + ":" + port);
    }

    @Override
    public void checkConnect(String host, int port, Object context)
    {
        checkConnect(host, port);
    }

    @Override
    public void checkListen(int port)
    {
        throw new SecurityException("checkListen-Lockdown-" + port);
    }

    @Override
    public void checkAccept(String host, int port)
    {
        throw new SecurityException("checkAccept-Lockdown-" + host + ":" + port);
    }

    @Override
    public void checkMulticast(InetAddress maddr)
    {
        throw new SecurityException("checkMulticast-Lockdown");
    }

    @Override
    public void checkMulticast(InetAddress maddr, byte ttl)
    {
        throw new SecurityException("checkMulticast-Lockdown");
    }

    @Override
    public void checkPropertiesAccess()
    {
        throw new SecurityException("checkPropertiesAccess-Lockdown");
    }

    @Override
    public void checkPropertyAccess(String key)
    {
        throw new SecurityException("checkPropertyAccess-Lockdown-" + key);
    }

    @Override
    public boolean checkTopLevelWindow(Object window)
    {
        throw new SecurityException("checkTopLevelWindow-Lockdown");
    }

    @Override
    public void checkPrintJobAccess()
    {
        throw new SecurityException("checkPrintJobAccess-Lockdown");
    }

    @Override
    public void checkSystemClipboardAccess()
    {
        throw new SecurityException("checkSystemClipboardAccess-Lockdown");
    }

    @Override
    public void checkAwtEventQueueAccess()
    {
        throw new SecurityException("checkAwtEventQueueAccess-Lockdown");
    }

    @Override
    public void checkSetFactory()
    {
        throw new SecurityException("checkSetFactory-Lockdown");
    }
}

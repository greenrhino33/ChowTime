package net.jamcraft.chowtime.core.commands;

import net.jamcraft.chowtime.ChowTime;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentTranslation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by James Hollowell on 5/24/2014.
 */
public class ChowTimeCommand implements ICommand
{
    private List aliases;

    public ChowTimeCommand()
    {
        this.aliases = new ArrayList();
        this.aliases.add("chowtime");
    }

    @Override public String getCommandName()
    {
        return "chowtime";
    }

    @Override public String getCommandUsage(ICommandSender var1)
    {
        return "/chowtime getXP\n/chowtime setXP <xp>";
    }

    @Override public List getCommandAliases()
    {
        return aliases;
    }

    @Override public void processCommand(ICommandSender commandSender, String[] astring)
    {
        if (astring.length == 1 && astring[0].equals("getXP"))
        {
            commandSender.addChatMessage(new ChatComponentTranslation("chat.getXP", ChowTime.harvestXP));
        }
        else if (astring.length == 2 && astring[0].equals("setXP"))
        {
            int xp = Integer.parseInt(astring[1]);
            ChowTime.harvestXP = xp;
            commandSender.addChatMessage(new ChatComponentTranslation("chat.setXP", xp));
        }
    }

    @Override public boolean canCommandSenderUseCommand(ICommandSender var1)
    {
        return true;
    }

    @Override public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
    {
        final List<String> MATCHES = new LinkedList<String>();
        final String ARG_LC = astring[astring.length - 1].toLowerCase();
        if (astring.length == 1)
        {
            if ("getXP".toLowerCase().startsWith(ARG_LC)) MATCHES.add("getXP");
            if ("setXP".toLowerCase().startsWith(ARG_LC)) MATCHES.add("setXP");
        }
        return MATCHES.isEmpty() ? null : MATCHES;
    }

    @Override public boolean isUsernameIndex(String[] var1, int var2)
    {
        return false;
    }

    @Override public int compareTo(Object o)
    {
        return 0;
    }
}

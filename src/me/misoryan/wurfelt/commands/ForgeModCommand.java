package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.WurfeltBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;

public class ForgeModCommand extends Command implements Listener
{
    public ForgeModCommand(final String name) {
        super(name);
    }

    public void execute(final CommandSender commandSender, final String[] strings) {
        if (!commandSender.hasPermission("Wurfelt.Helper")) {
            commandSender.sendMessage(ChatColor.RED + "Permission denied.");
            return;
        }
        if (strings.length != 1) {
            commandSender.sendMessage(ChatColor.RED + "Correct Usage: /modlist <Player>");
            return;
        }
        if (WurfeltBungee.ins.getProxy().getPlayer(strings[0]) == null) {
            commandSender.sendMessage(ChatColor.RED + "This player is offline.");
            return;
        }
        if (WurfeltBungee.ins.getProxy().getPlayer(strings[0]).isForgeUser()) {
            commandSender.sendMessage(ChatColor.YELLOW + "Player " + ChatColor.GOLD + WurfeltBungee.ins.getProxy().getPlayer(strings[0]) + " " + ChatColor.YELLOW + "is using forge:");
            commandSender.sendMessage(ChatColor.GOLD + "" + WurfeltBungee.ins.getProxy().getPlayer(strings[0]).getModList());
            return;
        }
        commandSender.sendMessage(ChatColor.YELLOW + "This player is not using forge.");
    }
}

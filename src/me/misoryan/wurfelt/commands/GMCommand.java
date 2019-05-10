package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GMCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("gm")) {
            Bukkit.dispatchCommand(commandSender,"gamemode " + Lib.getCurrentArgsFormat(strings,0));
            return true;
        }
        return true;
    }
}

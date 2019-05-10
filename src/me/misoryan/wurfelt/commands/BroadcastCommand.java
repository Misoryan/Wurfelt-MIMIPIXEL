package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("bc") || command.getName().equalsIgnoreCase("broadcast")) {
            if (!commandSender.hasPermission("Wurfelt.Broadcast") && !commandSender.hasPermission("Wurfelt.Admin")) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.permission-denied")));
                return true;
            }
            String text = Lib.getCurrentText(Lib.getCurrentArgsFormat(strings,0));
            String prefix = Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Broadcast.prefix"));
            if (text.startsWith(Lib.getCurrentText("&u"))) {
                prefix = "";
            }
            Bukkit.broadcastMessage(prefix + text);
        }
        return true;
    }
}

package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.security.auth.login.Configuration;
import java.io.IOException;

public class WurfeltCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("wurfelt")) {
            if (strings.length == 0) {
                commandSender.sendMessage(Lib.getCurrentText("&fWurfelt&f created by &7Misoryan"));
                commandSender.sendMessage(Lib.getCurrentText("&fType &7/Wurfelt Help &ffor Help."));
                return true;
            }
            if (strings[0].equalsIgnoreCase("reload")) {
                Wurfelt.ins.reloadConfig();
                Wurfelt.ins.register();
                commandSender.sendMessage(Lib.getCurrentText("&7Configuration is now &f&lRELOADED &7."));
                return true;
            }
            if (strings[0].equalsIgnoreCase("help")) {
                commandSender.sendMessage(Lib.getCurrentText("&7This command is now &f&lMAKING &7."));
                return true;
            }

            if (strings[0].equalsIgnoreCase("execute")) {
                if (!commandSender.hasPermission("Wurfelt.Execute") && !commandSender.hasPermission("Wurfelt.Admin")) {
                    commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.permission-denied")));
                    return true;
                }
                if (strings[1] != null) {
                    try {
                        Lib.execute(Lib.getCurrentArgsFormat(strings,1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.current-usage") + "/wurfelt execute <ID>"));
                }
            }

            commandSender.sendMessage(Lib.getCurrentText("Unknown arguments. Use &7/Wurfelt Help &ffor help."));
        }
        return true;
    }
}

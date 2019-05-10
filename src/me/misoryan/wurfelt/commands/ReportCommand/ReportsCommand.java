package me.misoryan.wurfelt.commands.ReportCommand;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Iterator;

public class ReportsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("reports")) {
            if (!commandSender.hasPermission("Wurfelt.Report") && !commandSender.hasPermission("Wurfelt.Admin")) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.sendErrorMessage("pex")));
                return true;
            }
            commandSender.sendMessage(Lib.getCurrentText("&7列举所有的举报:"));
            Iterator i = ReportCommand.ReportData.entrySet().iterator();
            while (i.hasNext()) {
                Object obj = i.next();
                String key = obj.toString();
                String data = ReportCommand.ReportData.get(key);
                commandSender.sendMessage(Lib.getCurrentText(key + "/" + data));
            }
        }
        return true;
    }
}

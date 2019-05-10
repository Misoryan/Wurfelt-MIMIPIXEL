package me.misoryan.wurfelt.commands.ReportCommand;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import me.misoryan.wurfelt.utils.ReportUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements CommandExecutor {

    public static Map<Long, String> ReportData;

    public ReportCommand() {
        ReportData = new HashMap<Long, String>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("wdr") || command.getName().equalsIgnoreCase("report")) {
            if (strings.length < 2) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.sendErrorMessage("usage") + "/report <ID> <Reason>"));
                return true;
            }
            if (ReportUtils.checkPlayerReportExists(strings[0]) != null) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("report.already-reported")));
                return true;
            }
            String player;
            if (Bukkit.getPlayerExact(strings[0]) != null) {
                player = Bukkit.getPlayerExact(strings[0]).getName();
            } else {
                player = strings[0];
            }
            String reason = Lib.getCurrentArgsFormat(strings,1);
            ReportUtils.createNewReport(player,reason,commandSender.getName());
            commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("report.report-success").replace("%player%",strings[0])));
            return true;
        }
        return true;
    }
}

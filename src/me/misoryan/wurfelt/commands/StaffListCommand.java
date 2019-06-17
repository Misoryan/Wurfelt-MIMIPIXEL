package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.WurfeltBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffListCommand extends Command {
    public StaffListCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("Wurfelt.Helper")) {
            commandSender.sendMessage("§cPermission Denied.");
            return;
        }
        commandSender.sendMessage("§a在线的管理员:");
        for (ProxiedPlayer p : WurfeltBungee.ins.getProxy().getPlayers()) {
            if (p.hasPermission("Wurfelt.Helper")) {
                commandSender.sendMessage(ChatCommand.getStaffRank(p.getName()) + p.getName() + " §7(" +  p.getServer().getInfo() + ")");
            }
        }
    }
}

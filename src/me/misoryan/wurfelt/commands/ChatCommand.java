package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.WurfeltBungee;
import me.misoryan.wurfelt.libs.Lib;
import net.md_5.bungee.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.HashMap;
import java.util.Map;

public class ChatCommand extends Command {

    public static Map<String, Boolean> ChatMode;

    public ChatCommand(String name) {
        super(name);
        ChatMode = new HashMap<String, Boolean>();
    }
    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("Wurfelt.Helper")) {
            commandSender.sendMessage("§cPermission Denied.");
            return;
        }
        if (strings.length == 0) {
            commandSender.sendMessage("§cCurrent Usage: §4/chat<a/s>");
            return;
        }
        if (strings[0].equalsIgnoreCase("a")) {
            ChatMode.put(commandSender.getName(),false);
            commandSender.sendMessage("§a你现在正在§6所有§a频道中");
            return;
        }
        if (strings[0].equalsIgnoreCase("s")) {
            ChatMode.put(commandSender.getName(),true);
            commandSender.sendMessage("§a你现在正在§6管理§a频道中");
            return;
        }
        commandSender.sendMessage("§cCurrent Usage: §4/chat <a/s>");
        return;
    }

    public static void sendStaffMessage(String player,String message) {
        String rank = getStaffRank(player);
        for (ProxiedPlayer p : WurfeltBungee.ins.getProxy().getPlayers()) {
            if (!getStaffRank(p.getName()).equalsIgnoreCase("§7")) {
                p.sendMessage("§3管理 > " + rank + player + ": §f" + message);
            }
        }
    }

    public static String getStaffRank(String player) {
        if (player.equalsIgnoreCase("Misoryan")) {
            return "§c[§fDEV§c] ";
        }
        if (WurfeltBungee.ins.getProxy().getPlayer(player).hasPermission("Wurfelt.Admin")) {
            return "§c[ADMIN] ";
        }
        if (WurfeltBungee.ins.getProxy().getPlayer(player).hasPermission("Wurfelt.Helper")) {
            return "§2[HELPER] ";
        }
       return "§7";
    }
}

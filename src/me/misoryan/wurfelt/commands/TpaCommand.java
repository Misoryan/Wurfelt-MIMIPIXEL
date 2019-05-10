package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TpaCommand implements CommandExecutor {

    public static Map<Player, String> TeleportReceiver;
    public static Map<Player, Long> TeleportCommandDelay;

    public TpaCommand() {
        TeleportReceiver = new HashMap<Player, String>();
        TeleportCommandDelay = new HashMap<Player, Long>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("tpa")) {
            if (strings.length < 1) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.current-usage") + "/tpa <player>"));
                return true;
            }
            if (Bukkit.getPlayer(strings[0]) == null) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.player-offline")));
                return true;
            }
            if (commandSender.getName().equalsIgnoreCase(strings[0])) {
                commandSender.sendMessage(Lib.getCurrentText("&b你必须指定一个在线的玩家."));
                return true;
            }
            if (TeleportCommandDelay.get(Bukkit.getPlayer(commandSender.getName())) != null) {
                if (System.currentTimeMillis() - TeleportCommandDelay.get(Bukkit.getPlayer(commandSender.getName())) < 60000) {
                    commandSender.sendMessage(Lib.getCurrentText("&b你已经发起了一个请求.."));
                }
            }
            TeleportReceiver.put(Bukkit.getPlayer(strings[0]),commandSender.getName());
            TeleportCommandDelay.put(Bukkit.getPlayer(commandSender.getName()),System.currentTimeMillis());
            Bukkit.getPlayer(strings[0]).sendMessage(Lib.getCurrentText("&3" + commandSender.getName() + " &b想要传送到你这里."));
            Bukkit.getPlayer(strings[0]).sendMessage(Lib.getCurrentText("&b使用 &3/tpaccept &b来接受请求."));
            Bukkit.getPlayer(strings[0]).sendMessage(Lib.getCurrentText("&b使用 &3/tpdeny &b来拒绝请求."));
            commandSender.sendMessage(Lib.getCurrentText("&b成功向 &3" + strings[0] + " &b发送传送请求."));
        }
        return true;
    }
}

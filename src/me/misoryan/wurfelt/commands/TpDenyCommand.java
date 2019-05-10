package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TpDenyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("tpdeny") && command.getName().equalsIgnoreCase("tpno")) {
            if (TpaCommand.TeleportReceiver.get(Bukkit.getPlayer(commandSender.getName())) == null) {
                commandSender.sendMessage("&c没有待处理的传送请求...");
                return true;
            }
            TpaCommand.TeleportCommandDelay.put(Bukkit.getPlayer(TpaCommand.TeleportReceiver.get(Bukkit.getPlayer(commandSender.getName()))),null);
            Bukkit.getPlayer(TpaCommand.TeleportReceiver.get(Bukkit.getPlayer(commandSender.getName()))).sendMessage(Lib.getCurrentText("&c你向 &4" + commandSender.getName() + " &c发起的请求已被拒绝."));
            TpaCommand.TeleportReceiver.put(Bukkit.getPlayer(commandSender.getName()),null);
        }
        return true;
    }
}

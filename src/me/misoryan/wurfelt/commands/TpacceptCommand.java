package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getEntity;

public class TpacceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("tpaccept") || command.getName().equalsIgnoreCase("tpyes")) {
        if (TpaCommand.TeleportReceiver.get(Bukkit.getPlayer(commandSender.getName())) == null) {
            commandSender.sendMessage(Lib.getCurrentText("&c没有待处理的传送请求..."));
            return true;
        }
            Bukkit.getPlayer(TpaCommand.TeleportReceiver.get(Bukkit.getPlayer(commandSender.getName()))).teleport(getEntity(Bukkit.getPlayer(commandSender.getName()).getUniqueId()));
            TpaCommand.TeleportCommandDelay.put(Bukkit.getPlayer(TpaCommand.TeleportReceiver.get(Bukkit.getPlayer(commandSender.getName()))),null);
            TpaCommand.TeleportReceiver.put(Bukkit.getPlayer(commandSender.getName()),null);
        }
        return true;
    }
}

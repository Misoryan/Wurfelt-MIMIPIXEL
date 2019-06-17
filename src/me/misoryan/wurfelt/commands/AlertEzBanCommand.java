package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.WurfeltBungee;
import me.misoryan.wurfelt.libs.Lib;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class AlertEzBanCommand extends Command {
    public AlertEzBanCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("Wurfelt.Helper")) {
            commandSender.sendMessage("§cPermission Denied.");
            return;
        }
        if (strings.length == 0) {
            commandSender.sendMessage("§cCurrent Usage: §4/ezban <Message>");
            return;
        }
        WurfeltBungee.ins.getProxy().getPlayer(commandSender.getName()).chat("/ban " + Lib.getCurrentArgsFormat(strings,0) + " 12h -s 不安全的账号，请进行保护并申诉。");
    }
}

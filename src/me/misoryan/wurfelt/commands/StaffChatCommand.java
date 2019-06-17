package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.libs.Lib;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.protocol.packet.Chat;

public class StaffChatCommand extends Command {

    public StaffChatCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("Wurfelt.Helper")) {
            commandSender.sendMessage("§cPermission Denied.");
            return;
        }
        if (strings.length == 0) {
            commandSender.sendMessage("§cCurrent Usage: §4/sc <Message>");
            return;
        }
        ChatCommand.sendStaffMessage(commandSender.getName(),Lib.getCurrentArgsFormat(strings,0));
    }
}

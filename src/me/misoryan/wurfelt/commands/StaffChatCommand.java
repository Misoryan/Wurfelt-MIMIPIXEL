package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.libs.Lib;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {

    public StaffChatCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission("Wurfelt.Helper")) {
            commandSender.sendMessage(Lib.getCurrentText("&cPermission Denied."));
            return;
        }
        if (strings.length == 0) {
            commandSender.sendMessage(Lib.getCurrentText("&cCurrent Usage: &4/sc <Message>"));
            return;
        }
    }
}

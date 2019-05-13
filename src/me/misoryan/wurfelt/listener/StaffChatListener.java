package me.misoryan.wurfelt.listener;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.WurfeltBungee;
import me.misoryan.wurfelt.commands.ChatCommand;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class StaffChatListener implements Listener {

    public StaffChatListener(String onChat) {
    }

    @EventHandler
    public void onChat(ChatEvent e) {
        ChatCommand.ChatMode.putIfAbsent(e.getSender().toString(),false);
        if (!ChatCommand.ChatMode.get(e.getSender().toString())) {
            return;
        }
        if (e.getMessage().startsWith("/")) {
            return;
        }
        e.setCancelled(true);
        ChatCommand.sendStaffMessage(e.getSender().toString(),e.getMessage());
    }
}

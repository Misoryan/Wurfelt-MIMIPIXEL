package me.misoryan.wurfelt.listener;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.WurfeltBungee;
import me.misoryan.wurfelt.commands.ChatCommand;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class StaffChatListener implements Listener {

    public StaffChatListener(String onChat) {
    }

    public static Boolean checkEventHandledDouble(String player) {
        ChatCommand.EventTimings.putIfAbsent(player, 0L);
        if (System.currentTimeMillis() - ChatCommand.EventTimings.get(player) > 10L) {
            ChatCommand.EventTimings.put(player, System.currentTimeMillis());
            return false;
        }
        else {
            return true;
            }
    }

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        if (e.getPlayer().hasPermission("Wurfelt.Helper")) {
            if (!checkEventHandledDouble(e.getPlayer().toString())) {
                for (ProxiedPlayer p : WurfeltBungee.ins.getProxy().getPlayers()) {
                    if (p.hasPermission("Wurfelt.Helper")) {
                        p.sendMessage("§a加入 > " + ChatCommand.getStaffRank(e.getPlayer().getName()) + e.getPlayer().getName());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent e) {
        if (e.getPlayer().hasPermission("Wurfelt.Helper")) {
            if (!checkEventHandledDouble(e.getPlayer().toString())) {
                for (ProxiedPlayer p : WurfeltBungee.ins.getProxy().getPlayers()) {
                    if (p.hasPermission("Wurfelt.Helper")) {
                        p.sendMessage("§a退出 > " + ChatCommand.getStaffRank(e.getPlayer().getName()) + e.getPlayer().getName());
                    }
                }
            }
        }
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
        //System.out.println("Event Handled (StaffChatEvent) by " + e.getSender().toString() + " on " + System.currentTimeMillis() + " ms.");
    }
}

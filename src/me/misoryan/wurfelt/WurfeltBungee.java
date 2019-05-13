package me.misoryan.wurfelt;

import me.misoryan.wurfelt.commands.AllChatCommand;
import me.misoryan.wurfelt.commands.ChatCommand;
import me.misoryan.wurfelt.commands.StaffChatCommand;
import me.misoryan.wurfelt.listener.StaffChatListener;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.*;

public class WurfeltBungee extends Plugin implements Listener {
    public static WurfeltBungee ins;
    public static Configuration conf;

    public void onEnable() {
        WurfeltBungee.ins = this;
        this.getProxy().getPluginManager().registerCommand(this,new AllChatCommand("ac"));
        this.getProxy().getPluginManager().registerCommand(this,new StaffChatCommand("sc"));
        this.getProxy().getPluginManager().registerCommand(this,new ChatCommand("chat"));
        this.getProxy().getPluginManager().registerListener(this,new StaffChatListener("onChat"));
    }
}

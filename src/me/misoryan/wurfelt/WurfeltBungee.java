package me.misoryan.wurfelt;

import me.misoryan.wurfelt.commands.*;
import me.misoryan.wurfelt.listener.StaffChatListener;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.*;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class WurfeltBungee extends Plugin implements Listener {
    public static WurfeltBungee ins;
    public static Configuration conf;

    public void onEnable() {
        WurfeltBungee.ins = this;
        this.getProxy().getPluginManager().registerCommand(this,new AllChatCommand("ac"));
        this.getProxy().getPluginManager().registerCommand(this,new StaffChatCommand("sc"));
        this.getProxy().getPluginManager().registerCommand(this,new ChatCommand("chat"));
        this.getProxy().getPluginManager().registerCommand(this,new StaffListCommand("slist"));
        this.getProxy().getPluginManager().registerListener(this,new StaffChatListener("onChat"));
        this.getProxy().getPluginManager().registerListener(this,new StaffChatListener("onJoin"));
    }
}

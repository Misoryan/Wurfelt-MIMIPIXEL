package me.misoryan.wurfelt;

import me.misoryan.wurfelt.commands.AllChatCommand;
import me.misoryan.wurfelt.commands.ChatCommand;
import me.misoryan.wurfelt.commands.StaffChatCommand;
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
        try {
            WurfeltBungee.conf = ConfigurationProvider.getProvider((Class) YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.getProxy().getPluginManager().registerCommand(this,new AllChatCommand("ac"));
        this.getProxy().getPluginManager().registerCommand(this,new StaffChatCommand("sc"));
        this.getProxy().getPluginManager().registerCommand(this,new ChatCommand("chat"));
        this.getProxy().getPluginManager().registerListener(this,new StaffChatListener("onChat"));
    }
}

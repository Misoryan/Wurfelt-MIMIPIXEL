package me.misoryan.wurfelt.listener;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import me.misoryan.wurfelt.commands.VanishCommand;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VanishListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (VanishCommand.VanishData.get(e.getPlayer()) != null) {
            e.setJoinMessage("");
            for (Player player: Bukkit.getOnlinePlayers()) {
                e.getPlayer().hidePlayer(player);
            }
            ActionBarAPI.sendActionBar(e.getPlayer(), Lib.getCurrentText("&f你目前处于&c隐身&f状态中"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if (VanishCommand.VanishData.get(e.getPlayer()) != null) {
            e.setQuitMessage("");
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER) {
            if (VanishCommand.VanishData.get(Bukkit.getPlayer(e.getEntity().getName())) != null) {
                e.setCancelled(true);
            }
        }
    }
}

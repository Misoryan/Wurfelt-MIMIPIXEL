package me.misoryan.wurfelt.listener;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.commands.FreezeCommand;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class FreezeListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(final EntityDamageByEntityEvent e) {
        if (FreezeCommand.freezeing.get(e.getEntity()) != null) {
            e.setCancelled(true);
        }
        if (FreezeCommand.freezeing.get(e.getDamager()) != null) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(final InventoryClickEvent e) {
        if (FreezeCommand.freezeing.get(e.getWhoClicked().getName()) != null) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
        if (FreezeCommand.freezeing.get(e.getPlayer().getName()) != null) {
            if (Bukkit.getPlayerExact(e.getPlayer().getName()) == null) {
                return;
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (Bukkit.getPlayer(e.getPlayer().getName()) == null) {
                        return;
                    }
                    final Inventory inventory = Bukkit.createInventory(null, 9, Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.title")));
                    final ItemStack itemStack = new ItemStack(Material.YELLOW_FLOWER);
                    final ItemMeta itemMeta = itemStack.getItemMeta();
                    int i = 0;
                    for (final String l : Wurfelt.ins.getConfig().getStringList("freeze.lore")) {
                        ++i;
                    }
                    final ArrayList<String> lore = new ArrayList<String>();
                    i = 0;
                    for (String j : Wurfelt.ins.getConfig().getStringList("freeze.lore")) {
                        j = Lib.getCurrentText(j);
                        j = j.replace("[OPERATOR]", FreezeCommand.freezeing.get(e.getPlayer().getName()));
                        lore.add(j);
                        ++i;
                    }
                    itemMeta.setLore(lore);
                    itemMeta.setDisplayName(Wurfelt.ins.getConfig().getString("freeze.title").replaceAll("&", "§"));
                    itemStack.setItemMeta(itemMeta);
                    inventory.setItem(4, itemStack);
                    e.getPlayer().openInventory(inventory);
                }
            }.runTaskLater(Wurfelt.ins,3L);
        }
    }

    @EventHandler
    public void onDisconnect(final PlayerQuitEvent e) {
        if (FreezeCommand.freezeing.get(e.getPlayer().getName()) != null) {
            for (String x : Wurfelt.ins.getConfig().getStringList("freeze.disconnect.message")) {
                if (Wurfelt.ins.getConfig().getBoolean("freeze.disconnect.showtoall")) {
                    Bukkit.broadcastMessage(Lib.getCurrentText(x.replace("[PLAYER]",e.getPlayer().getName())));
                }
                else {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("Wurfelt.SS")) {
                            p.sendMessage(Lib.getCurrentText(x.replace("[PLAYER]",e.getPlayer().getName())));
                        }
                    }
                }
            }
            for (String p : Wurfelt.ins.getConfig().getStringList("freeze.disconnect.command")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),p.replace("[PLAYER]",e.getPlayer().getName()));
            }
            FreezeCommand.freezeing.remove(e.getPlayer().getName());
        }
    }
}

package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FreezeCommand implements CommandExecutor {

    public static Map<String, String> freezeing;

    public FreezeCommand() {
        freezeing = new HashMap<String, String>();
    }
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("freeze")) {
            if (!sender.hasPermission("Wurfelt.SS")) {
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.permission-denied")));
                return true;
            }
            if (args.length != 1) {
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.current-usage") + "/freeze <ID>"));
                return true;
            }
            if (Bukkit.getPlayerExact(args[0]) == null) {
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.player-offline")));
                return true;
            }
            else if (freezeing.get(args[0]) == null) {
                freezeing.put(Bukkit.getPlayer(args[0]).getName(), sender.getName());
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
                    j = j.replace("[OPERATOR]", freezeing.get(args[0]));
                    lore.add(j);
                    ++i;
                }
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.title")));
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(4, itemStack);
                Bukkit.getPlayerExact(args[0]).openInventory(inventory);
                Bukkit.getPlayerExact(args[0]).sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.freeze-message").replace("[PLAYER]",sender.getName())));
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.freeze-other-message").replace("[PLAYER]",args[0])));
            }
            else {
                freezeing.remove(Bukkit.getPlayer(args[0]).getName());
                Bukkit.getPlayerExact(args[0]).closeInventory();
                Bukkit.getPlayerExact(args[0]).sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.unfreeze-message").replace("[PLAYER]",sender.getName())));
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.unfreeze-other-message").replace("[PLAYER]",args[0])));
            }
        }
        return true;
    }
}

package me.misoryan.wurfelt.commands;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class VanishCommand implements CommandExecutor {
    public static Map<Player, Boolean> VanishData;

    public VanishCommand() {
        VanishData = new HashMap<Player, Boolean>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("v") || command.getName().equalsIgnoreCase("vanish")) {
            Player sender = Bukkit.getPlayer(commandSender.getName());
            if (!commandSender.hasPermission("Wurfelt.Vanish") && !commandSender.hasPermission("Wurfelt.Admin")) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.permission-denied")));
                return true;
            }
            VanishData.putIfAbsent(sender,false);
            if (!VanishData.get(sender)) {
                VanishData.put(sender,true);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.hidePlayer(Wurfelt.ins,sender);
                }
                ActionBarAPI.sendActionBar(Objects.requireNonNull(Bukkit.getPlayer(commandSender.getName())),Lib.getCurrentText("&f你目前处于&c隐身&f状态中"));
                commandSender.sendMessage(Lib.getCurrentText("&a你现在进入了隐身模式,其他玩家将无法看到你."));
            }
            else {
                VanishData.put(sender, false);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.showPlayer(Wurfelt.ins,sender);
                    sender.showPlayer(Wurfelt.ins,p);
                }
                ActionBarAPI.sendActionBar(Objects.requireNonNull(Bukkit.getPlayer(commandSender.getName())),Lib.getCurrentText("&f "));
                commandSender.sendMessage(Lib.getCurrentText("&c你现在关闭了隐身模式,其他玩家将可以看到你."));
            }
        }
        return true;
    }
}

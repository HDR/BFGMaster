package org.bitfixgaming.bfgmaster.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tprequestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length >= 1){
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target != null) {
                    if (target == player){
                        player.sendMessage(ChatColor.RED + "You can't teleport to yourself you freak!");
                        return true;
                    } else {
                        target.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GOLD + " is requesting to teleport to you, use /tpaccept to accept.");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Could not find a player by the name " + ChatColor.GREEN + args[0]);
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.RED + "Please specify the player you want to teleport to.");
                return true;
            }
        }
        return false;
    }
}


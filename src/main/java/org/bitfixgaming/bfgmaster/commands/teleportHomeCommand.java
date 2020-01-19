package org.bitfixgaming.bfgmaster.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bitfixgaming.bfgmaster.Main.HomeData;
import static org.bitfixgaming.bfgmaster.Main.SaveData;

public class teleportHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            JsonElement getHomes = HomeData.get(player.getName());
            try {
                if (!getHomes.toString().isEmpty()) {
                    JsonObject homeData = getHomes.getAsJsonObject();
                    World W = Bukkit.getWorld(homeData.get("World").toString().replaceAll("\"", ""));
                    double X = homeData.get("X").getAsDouble();
                    double Y = homeData.get("Y").getAsDouble();
                    double Z = homeData.get("Z").getAsDouble();
                    player.teleport(new Location(W,X,Y,Z));
                    player.sendMessage(ChatColor.GOLD + "Teleporting you to " + ChatColor.GREEN + homeData.get("Home").toString());
                    return true;
                }
            } catch(NullPointerException ignore){
                player.sendMessage(ChatColor.RED + "You have no homes, Please stop being homeless and try again later.");
                return true;
            }
        }
        return false;
    }
}


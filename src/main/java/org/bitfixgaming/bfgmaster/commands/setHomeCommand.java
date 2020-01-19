package org.bitfixgaming.bfgmaster.commands;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bitfixgaming.bfgmaster.Main.HomeData;
import static org.bitfixgaming.bfgmaster.Main.SaveData;

public class setHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length >= 1){
                World W = player.getLocation().getWorld();
                double X = player.getLocation().getBlockX();
                double Y = player.getLocation().getBlockY();
                double Z = player.getLocation().getBlockZ();
                player.teleport(new Location(W, X, Y, Z));
                String Homename = args[0];
                JsonObject tmpData = new JsonObject();
                tmpData.addProperty("Home", Homename);
                tmpData.addProperty("World" , W.getName());
                tmpData.addProperty("X", X);
                tmpData.addProperty("Y", Y);
                tmpData.addProperty("Z", Z);
                HomeData.add(player.getName(), tmpData);
                SaveData();
                player.sendMessage(ChatColor.GOLD + "Saving " + ChatColor.GREEN + "" + Homename + ChatColor.GOLD + " located at X:" + ChatColor.GREEN + X + ChatColor.GOLD + " Y:" + ChatColor.GREEN + Y + ChatColor.GOLD +  " Z:" + ChatColor.GREEN + Z);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Please specify a name for your home");
            }
        }
        return false;
    }
}
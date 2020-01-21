package org.bitfixgaming.bfgmaster.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bitfixgaming.bfgmaster.Main.HomeData;
import static org.bitfixgaming.bfgmaster.Utils.SaveData;

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
                JsonObject tmpData = new JsonObject();
                tmpData.addProperty("Home", args[0]);
                tmpData.addProperty("World" , W.getName());
                tmpData.addProperty("X", X);
                tmpData.addProperty("Y", Y);
                tmpData.addProperty("Z", Z);
                HomeData.add(player.getName(), tmpData);
                SaveData("homes.json", HomeData.toString());
                player.sendMessage(ChatColor.GOLD + "Saving " + ChatColor.GREEN + "" + args[0] + ChatColor.GOLD + " located at X:" + ChatColor.GREEN + X + ChatColor.GOLD + " Y:" + ChatColor.GREEN + Y + ChatColor.GOLD +  " Z:" + ChatColor.GREEN + Z);
                return true;
            } else {
                JsonElement getHomes = HomeData.get(player.getName());
                try { if(!getHomes.toString().isEmpty()){ player.sendMessage(ChatColor.RED + "A Home already exists, please delete it with /delhome first."); }
                } catch(NullPointerException ignore){
                    World W = player.getLocation().getWorld();
                    double X = player.getLocation().getBlockX();
                    double Y = player.getLocation().getBlockY();
                    double Z = player.getLocation().getBlockZ();
                    JsonObject tmpData = new JsonObject();
                    tmpData.addProperty("Home", "home");
                    tmpData.addProperty("World", W.getName());
                    tmpData.addProperty("X", X);
                    tmpData.addProperty("Y", Y);
                    tmpData.addProperty("Z", Z);
                    HomeData.add(player.getName(), tmpData);
                    SaveData("homes.json", HomeData.toString());
                    player.sendMessage(ChatColor.GOLD + "Saving " + ChatColor.GREEN + "" + "home" + ChatColor.GOLD + " located at X:" + ChatColor.GREEN + X + ChatColor.GOLD + " Y:" + ChatColor.GREEN + Y + ChatColor.GOLD + " Z:" + ChatColor.GREEN + Z);
                    return true;
                }
            }
        }
        return false;
    }
}

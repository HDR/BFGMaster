package org.bitfixgaming.bfgmaster.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bitfixgaming.bfgmaster.Main.HomeData;
import static org.bitfixgaming.bfgmaster.Main.SaveData;

public class deleteHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            JsonElement getHomes = HomeData.get(player.getName());
            try {
                if (!getHomes.toString().isEmpty()) {
                    JsonObject homeData = getHomes.getAsJsonObject();
                    //player.sendMessage("Home Name: " + homeData.get("Home").toString() + "World: " + homeData.get("World").toString() + "X:" + homeData.get("X").toString() + "Y:" + homeData.get("Y").toString() + "Z:" + homeData.get("Z").toString());
                    player.sendMessage(ChatColor.GREEN + homeData.get("Home").toString() + ChatColor.GOLD + " has been deleted.");
                    HomeData.remove(player.getName());
                    SaveData();
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


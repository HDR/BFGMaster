package org.bitfixgaming.bfgmaster;

import com.google.gson.JsonObject;
import org.bitfixgaming.bfgmaster.commands.deleteHomeCommand;
import org.bitfixgaming.bfgmaster.commands.setHomeCommand;
import org.bitfixgaming.bfgmaster.commands.teleportHomeCommand;
import org.bitfixgaming.bfgmaster.commands.tprequestCommand;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bitfixgaming.bfgmaster.Utils.readJson;
import static org.bitfixgaming.bfgmaster.Utils.createJson;

public class Main extends JavaPlugin {

    public static JsonObject HomeData;

    @Override
    public void onEnable(){
        this.getCommand("sethome").setExecutor(new setHomeCommand());
        this.getCommand("delhome").setExecutor(new deleteHomeCommand());
        this.getCommand("home").setExecutor(new teleportHomeCommand());
        this.getCommand("tpa").setExecutor((new tprequestCommand()));
        createJson("plugins/config/BFG/", "homes.json");
        readJson("plugins/config/BFG/homes.json");
    }
}

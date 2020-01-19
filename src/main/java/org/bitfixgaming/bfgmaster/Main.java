package org.bitfixgaming.bfgmaster;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bitfixgaming.bfgmaster.commands.setHomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main extends JavaPlugin {

    public static JsonObject HomeData;

    @Override
    public void onEnable(){
        this.getCommand("sethome").setExecutor(new setHomeCommand());
        createJson();
        readJson();
    }

    public static void SaveData() {
        String jstring = HomeData.toString();
        try {
            Utils.create("plugins/BFG", "homes.json", jstring);
        } catch (IOException ignore) {}
    }

    private static void createJson() {
        String CreateString = "{}";
        File file = new File("plugins/BFG/homes.json");
        if (!file.exists()) {
            try {
                Utils.create("plugins/BFG", "homes.json", CreateString);
            } catch (IOException ignore) {}
        }
        if (file.length() == 0) {
            try {
                Utils.create("plugins/BFG", "homes.json", CreateString);
            } catch (IOException ignore) {}
        }
    }

    private static void readJson() {
        JsonParser parser = new JsonParser();
        try {
            Object obj = parser.parse(new FileReader("plugins/BFG/homes.json"));
            HomeData = (JsonObject) obj;
        } catch (IOException ignore) {}
    }
}

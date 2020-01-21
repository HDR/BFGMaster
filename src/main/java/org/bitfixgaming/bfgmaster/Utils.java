package org.bitfixgaming.bfgmaster;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.bitfixgaming.bfgmaster.Main.HomeData;

public class Utils {

    static void create(String path, String name, String text) throws IOException {
        File dir = new File(path);
        dir.mkdirs();
        FileWriter file = new FileWriter(new File(dir, name));
        file.write(text);
        file.flush();
        file.close();
    }

    public static void SaveData(String file, String data) {
        try {
            Utils.create("plugins/config/BFG/", file, data);
        } catch (IOException ignore) {}
    }

    public static void readJson(String filepath) {
        JsonParser parser = new JsonParser();
        try {
            Object obj = parser.parse(new FileReader(filepath));
            HomeData = (JsonObject) obj;
        } catch (IOException ignore) {}
    }

    public static void createJson(String path, String filename) {
        String CreateString = "{}";
        File file = new File(path + filename);
        if (!file.exists()) { try { Utils.create(path, filename, CreateString);}catch(IOException ignore){}}
        if (file.length() == 0) { try { Utils.create(path, filename, CreateString);}catch(IOException ignore){}}
    }
}
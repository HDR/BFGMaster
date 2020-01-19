package org.bitfixgaming.bfgmaster;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Utils {
    static void create(String path, String name, String text) throws IOException {
        File dir = new File(path);
        dir.mkdirs();
        FileWriter file = new FileWriter(new File(dir, name));
        file.write(text);
        file.flush();
        file.close();
    }
}
package eu.telecomnancy.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileWriter {
    public static void createDir() {
        File dir = new File("resources");
        File dir_2 = new File("resources/decks");

        if (!dir.exists()) {
            dir.mkdir();
            dir_2.mkdir();
        }
    }

    public void write(String json, String name) throws IOException {
        createDir();

        File file = new File("resources/decks/" + name + ".json");
        OutputStream stream = new FileOutputStream(file);

        stream.write(json.getBytes());
        stream.close();
    }
}

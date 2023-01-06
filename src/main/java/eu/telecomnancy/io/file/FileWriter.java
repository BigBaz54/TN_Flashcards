package eu.telecomnancy.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import eu.telecomnancy.model.Media;

public class FileWriter {
    public FileWriter() {
        FileWriter.createDir();
    }

    public static void createDir() {
        File dir_1 = new File("resources");
        File dir_2 = new File("resources/decks");
        File dir_3 = new File("resources/exports");
        File dir_4 = new File("resources/images");
        File dir_5 = new File("resources/sounds");
        File dir_6 = new File("resources/videos");

        if (!dir_1.exists()) {
            dir_1.mkdir();
            dir_2.mkdir();
            dir_3.mkdir();
            dir_4.mkdir();
            dir_5.mkdir();
            dir_6.mkdir();
        }
    }

    public void writeJson(String json, String name) throws IOException {
        File file = new File("resources/decks/" + name);
        OutputStream stream = new FileOutputStream(file);

        stream.write(json.getBytes());
        stream.close();
    }

    public void writeMedia(Media media) throws IOException {
        String path = "resources/";
        String folder = "";
        switch (media.getType()) {
            case IMG:
                folder = "images";
                break;
            case AUDIO:
                folder = "sounds";
                break;
            case VIDEO:
                folder = "videos";
                break;
        }

        path += folder + "/" + media.getFile().getName();

        FileWriter.writeFile(media.getFile(), path);
    }

    public static void writeFile(File file, String path) {
        File newFile = new File(path);
        if (newFile.exists()) {
            return;
        }
        try (FileInputStream is = new FileInputStream(file);
                FileOutputStream os = new FileOutputStream(newFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

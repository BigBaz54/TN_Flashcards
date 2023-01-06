package eu.telecomnancy.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import eu.telecomnancy.model.Media;

public class FileWriter {
    public FileWriter() {
        FileWriter.createDir();
    }

    public static void createDir() {
        ArrayList<File> dirs = new ArrayList<File>();
        dirs.add(new File("resources"));
        dirs.add(new File("resources/decks"));
        dirs.add(new File("resources/exports"));
        dirs.add(new File("resources/images"));
        dirs.add(new File("resources/sounds"));
        dirs.add(new File("resources/temp"));
        dirs.add(new File("resources/videos"));

        for (File file : dirs) {
            if (!file.exists())
                file.mkdir();
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

package eu.telecomnancy.model;

import java.io.File;

public class Media {
    private String fileName;
    private MediaType type;

    public Media(String fileName, MediaType type) {
        this.fileName = fileName;
        this.type = type;
    }

    public Media() {
    }

    public File getFile() {
        String path = "resources/";
        switch (type) {
            case IMG:
                path += "images/";
                break;
            case AUDIO:
                path += "sounds/";
                break;
            case VIDEO:
                path += "videos/";
                break;
        }
        return new File(path + fileName);
    }

    public MediaType getType() {
        return type;
    }

    public void setFile(String fileName) {
        this.fileName = fileName;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getName() {
        return this.fileName;
    }
}

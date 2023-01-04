package eu.telecomnancy.model;

import java.io.File;

public class Media {
    private File file;
    private MediaType type;

    public Media(File file, MediaType type) {
        this.file = file;
        this.type = type;
    }

    public Media() {
    }

    public File getFile() {
        return file;
    }

    public MediaType getType() {
        return type;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setType(MediaType type) {
        this.type = type;
    }
}

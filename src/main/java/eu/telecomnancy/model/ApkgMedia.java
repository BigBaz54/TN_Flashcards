package eu.telecomnancy.model;

import java.util.HashMap;

public class ApkgMedia {
    private HashMap<Integer, String> media;

    public ApkgMedia() {
        this.media = new HashMap<Integer, String>();
    }

    public HashMap<Integer, String> getMedia() {
        return media;
    }

    public void setMedia(HashMap<Integer, String> media) {
        this.media = media;
    }

    public void addMedia(Integer id, String path) {
        this.media.put(id, path);
    }

    public String getPath(Integer id) {
        return this.media.get(id);
    }
}

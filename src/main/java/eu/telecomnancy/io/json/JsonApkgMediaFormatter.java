package eu.telecomnancy.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.telecomnancy.io.adapter.ApkgMediaAdapter;
import eu.telecomnancy.model.ApkgMedia;

public class JsonApkgMediaFormatter extends JsonFormatter<ApkgMedia> {

    public JsonApkgMediaFormatter(ApkgMedia model) {
        super(model);
        // not needed
    }

    @Override
    protected void compactModel() {
        // not needed
    }

    @Override
    public String toJson() {
        // not needed
        return null;
    }

    @Override
    public void fromJson(String json, ApkgMedia model) {
        GsonBuilder builder = new GsonBuilder();
        builder = builder.registerTypeAdapter(ApkgMedia.class, new ApkgMediaAdapter());
        Gson gson = builder.create();

        model.setMedia(gson.fromJson(json, ApkgMedia.class).getMedia());
    }

}

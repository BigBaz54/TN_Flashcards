package eu.telecomnancy.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.telecomnancy.io.adapter.ApkgDecksAdapter;
import eu.telecomnancy.model.ApkgDeckListModel;

public class JsonApkgFormatter extends JsonFormatter<ApkgDeckListModel> {

    public JsonApkgFormatter(ApkgDeckListModel model) {
        super(model);
    }

    @Override
    protected void compactModel() {
        // Not needed
    }

    @Override
    public String toJson() {
        // Not needed
        return null;
    }

    @Override
    public void fromJson(String json, ApkgDeckListModel model) {
        GsonBuilder builder = new GsonBuilder();
        builder = builder.registerTypeAdapter(ApkgDeckListModel.class, new ApkgDecksAdapter());
        Gson gson = builder.create();

        model.setDecks(gson.fromJson(json, ApkgDeckListModel.class).getDecks());
    }

}

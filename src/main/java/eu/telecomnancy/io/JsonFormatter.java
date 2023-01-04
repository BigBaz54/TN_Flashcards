package eu.telecomnancy.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.compact.CDeckModel;

public class JsonFormatter {
    private DeckModel deckModel;
    private CDeckModel compactDeckModel;

    public JsonFormatter(DeckModel deckModel) {
        this.deckModel = deckModel;
        compactDeckModel = null;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
        compactDeckModel = null;
    }

    private void compactDeckModel() {
        this.compactDeckModel = new CDeckModel().from(deckModel);
    }

    public String toJson() {
        if (compactDeckModel == null) {
            compactDeckModel();
        }

        GsonBuilder builder = new GsonBuilder();
        builder = builder.registerTypeAdapter(CDeckModel.class, new CDeckModelAdapter());
        Gson gson = builder.create();

        return gson.toJson(compactDeckModel);
    }

    public DeckModel deckFromJson(String json) {
        GsonBuilder builder = new GsonBuilder();
        builder = builder.registerTypeAdapter(CDeckModel.class, new CDeckModelAdapter());
        Gson gson = builder.create();

        CDeckModel cDeckModel = gson.fromJson(json, CDeckModel.class);

        return cDeckModel.to();
    }
}

package eu.telecomnancy.io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.telecomnancy.io.adapter.CDeckModelAdapter;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.compact.CDeckModel;

public class JsonFormatterDeck extends JsonFormatter<DeckModel> {
    public JsonFormatterDeck() {
        super(null);
    }

    public JsonFormatterDeck(DeckModel deckModel) {
        super(deckModel);
    }

    @Override
    protected void compactModel() {
        this.compact = new CDeckModel().from(model);
    }

    @Override
    public String toJson() {
        if (model == null) {
            throw new IllegalStateException("No model to serialize");
        }

        if (compact == null) {
            compactModel();
        }

        GsonBuilder builder = new GsonBuilder();
        if (pretty) {
            builder = builder.setPrettyPrinting();
        }
        builder = builder.registerTypeAdapter(CDeckModel.class, new CDeckModelAdapter());
        Gson gson = builder.create();

        return gson.toJson(compact);
    }

    @Override
    public void fromJson(String json, DeckModel model) {
        GsonBuilder builder = new GsonBuilder();
        builder = builder.registerTypeAdapter(CDeckModel.class, new CDeckModelAdapter());
        Gson gson = builder.create();

        CDeckModel cDeckModel = gson.fromJson(json, CDeckModel.class);
        cDeckModel.to(model);
    }
}

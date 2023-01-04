package eu.telecomnancy.io;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.model.CDeckModel;
import eu.telecomnancy.model.CardModel;

public class CDeckModelAdapter extends TypeAdapter<CDeckModel> {

    @Override
    public void write(JsonWriter out, CDeckModel value) throws IOException {
        out.beginObject();
        out.name("cards");
        out.beginArray();
        for (CardModel card : value.getCards()) {
            out.beginObject();
            out.name("question");
            out.value(card.getQuestion());
            out.name("answer");
            out.value(card.getAnswer());
            out.name("probability");
            out.value(card.getProbability());
            out.endObject();
        }
        out.endArray();
        out.name("name");
        out.value(value.getName());
        out.name("description");
        out.value(value.getDescription());
        out.endObject();
    }

    @Override
    public CDeckModel read(JsonReader in) throws IOException {
        // TODO: Auto-generated method stub
        return null;
    }

}

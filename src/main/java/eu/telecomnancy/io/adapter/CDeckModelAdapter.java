package eu.telecomnancy.io.adapter;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.compact.CDeckModel;

public class CDeckModelAdapter extends TypeAdapter<CDeckModel> {

    @Override
    public void write(JsonWriter out, CDeckModel value) throws IOException {
        // TODO: add tags on cards and deck
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
        // TODO: add tags on cards and deck
        CDeckModel deck = new CDeckModel();
        in.beginObject();
        String fieldName = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = in.nextName();
            }

            if ("cards".equals(fieldName)) {
                ArrayList<CardModel> cards = new ArrayList<>();
                in.beginArray();
                while (in.hasNext()) {
                    in.beginObject();
                    String question = null;
                    String answer = null;
                    Float probability = null;
                    while (in.hasNext()) {
                        JsonToken cardToken = in.peek();
                        if (cardToken.equals(JsonToken.NAME)) {
                            fieldName = in.nextName();
                        }
                        if ("question".equals(fieldName)) {
                            question = in.nextString();
                        }
                        if ("answer".equals(fieldName)) {
                            answer = in.nextString();
                        }
                        if ("probability".equals(fieldName)) {
                            probability = Float.parseFloat(in.nextString());
                        }
                    }
                    in.endObject();
                    CardModel model = new CardModel(question, answer);
                    model.setProbability(probability);
                    cards.add(model);
                }
                in.endArray();
                deck.setCards(cards);
            }

            if ("name".equals(fieldName)) {
                deck.setName(in.nextString());
            }

            if ("description".equals(fieldName)) {
                deck.setDescription(in.nextString());
            }
        }
        in.endObject();

        return deck;
    }

}

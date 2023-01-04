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
                    cards.add(readCard(in));
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

    private CardModel readCard(JsonReader in) throws IOException {
        CardModel card = new CardModel();
        in.beginObject();
        String fieldName = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = in.nextName();
            }

            if ("question".equals(fieldName)) {
                card.setQuestion(in.nextString());
            }

            if ("answer".equals(fieldName)) {
                card.setAnswer(in.nextString());
            }

            if ("probability".equals(fieldName)) {
                card.setProbability(Float.parseFloat(in.nextString()));
            }
        }
        in.endObject();

        return card;
    }
}

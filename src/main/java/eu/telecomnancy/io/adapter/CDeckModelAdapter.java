package eu.telecomnancy.io.adapter;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.DeckTag;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.compact.CDeckModel;

public class CDeckModelAdapter extends TypeAdapter<CDeckModel> {
    private CardAdapter cardAdapter = new CardAdapter();
    private StatDeckAdapter statDeckAdapter = new StatDeckAdapter();

    @Override
    public void write(JsonWriter out, CDeckModel value) throws IOException {
        out.beginObject();
        out.name("cards");
        out.beginArray();
        for (CardModel card : value.getCards()) {
            cardAdapter.write(out, card);
        }
        out.endArray();
        out.name("tags");
        out.beginArray();
        for (DeckTag tag : value.getTags()) {
            out.value(tag.getName());
        }
        out.endArray();
        out.name("statDeck");
        statDeckAdapter.write(out, value.getStatDeck());
        out.name("name");
        out.value(value.getName());
        out.name("description");
        out.value(value.getDescription());
        out.endObject();
    }

    @Override
    public CDeckModel read(JsonReader in) throws IOException {
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
                    cards.add(cardAdapter.read(in));
                }
                in.endArray();
                deck.setCards(cards);
            }
            if ("tags".equals(fieldName)) {
                ArrayList<DeckTag> tags = new ArrayList<>();
                in.beginArray();
                while (in.hasNext()) {
                    tags.add(new DeckTag(in.nextString()));
                }
                in.endArray();
                deck.setTags(tags);
            }
            if ("statDeck".equals(fieldName)) {
                deck.setStatDeck(statDeckAdapter.read(in));
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

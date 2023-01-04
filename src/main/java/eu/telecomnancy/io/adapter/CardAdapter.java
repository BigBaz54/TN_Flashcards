package eu.telecomnancy.io.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.CardTag;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.Media;

public class CardAdapter extends TypeAdapter<CardModel> {
    private MediaAdapter mediaAdapter = new MediaAdapter();

    @Override
    public void write(JsonWriter out, CardModel value) throws IOException {
        out.beginObject();
        out.name("question");
        out.value(value.getQuestion());
        out.name("answer");
        out.value(value.getAnswer());
        out.name("probability");
        out.value(value.getProbability());
        out.name("tags");
        out.beginArray();
        for (CardTag tag : value.getTags()) {
            out.value(tag.getName());
        }
        out.endArray();
        Media media = value.getMedia();
        if (media != null) {
            out.name("media");
            mediaAdapter.write(out, media);
        }
        out.endObject();
    }

    @Override
    public CardModel read(JsonReader in) throws IOException {
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
            if ("tags".equals(fieldName)) {
                in.beginArray();
                while (in.hasNext()) {
                    card.addTag(in.nextString());
                }
                in.endArray();
            }
            if ("media".equals(fieldName)) {
                card.setMedia(mediaAdapter.read(in));
            }
        }
        in.endObject();

        return card;
    }

}

package eu.telecomnancy.io.adapter;

import java.io.IOException;
import java.util.Date;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.model.StatCard;
import eu.telecomnancy.model.StatDeck;

public class StatDeckAdapter extends TypeAdapter<StatDeck> {
    private StatCardAdapter statCardAdapter = new StatCardAdapter();

    @Override
    public void write(JsonWriter out, StatDeck value) throws IOException {
        // TODO: Add StatCards
        out.beginObject();
        out.name("nbTimesOpened");
        out.value(value.getNbTimesOpened());
        out.name("nbTimesCorrect");
        out.value(value.getNbTimesCorrect());
        out.name("nbTimesWrong");
        out.value(value.getNbTimesWrong());
        out.name("nbCardsSeen");
        out.value(value.getNbCardsSeen());
        out.name("timesSpent");
        out.value(value.getTimesSpent());
        out.name("lastOpened");
        out.value(value.getLastOpened().getTime());
        out.name("creationDate");
        out.value(value.getCreationDate().getTime());
        out.endObject();
    }

    @Override
    public StatDeck read(JsonReader in) throws IOException {
        StatDeck statDeck = new StatDeck();
        in.beginObject();
        String fieldName = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = in.nextName();
            }
            if ("nbTimesOpened".equals(fieldName)) {
                statDeck.setNbTimesOpened(in.nextInt());
            }
            if ("nbTimesCorrect".equals(fieldName)) {
                statDeck.setNbTimesCorrect(in.nextInt());
            }
            if ("nbTimesWrong".equals(fieldName)) {
                statDeck.setNbTimesWrong(in.nextInt());
            }
            if ("nbCardsSeen".equals(fieldName)) {
                statDeck.setNbCardsSeen(in.nextInt());
            }
            if ("timesSpent".equals(fieldName)) {
                statDeck.setTimesSpent(in.nextLong());
            }
            if ("lastOpened".equals(fieldName)) {
                statDeck.setLastOpened(new Date(in.nextLong()));
            }
            if ("creationDate".equals(fieldName)) {
                statDeck.setCreationDate(new Date(in.nextLong()));
            }
        }

        in.endObject();
        return statDeck;
    }

}

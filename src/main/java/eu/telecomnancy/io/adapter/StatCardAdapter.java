package eu.telecomnancy.io.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.model.StatCard;

public class StatCardAdapter extends TypeAdapter<StatCard> {

    @Override
    public void write(JsonWriter out, StatCard value) throws IOException {
        out.beginObject();
        out.name("nbTimesSeen");
        out.value(value.getNbTimesSeen());
        out.name("nbTimesCorrect");
        out.value(value.getNbTimesCorrect());
        out.name("nbTimesWrong");
        out.value(value.getNbTimesWrong());
        out.name("timesSpent");
        out.beginArray();
        for (Long time : value.getTimesSpent()) {
            out.value(time);
        }
        out.endArray();
        out.endObject();
    }

    @Override
    public StatCard read(JsonReader in) throws IOException {
        StatCard statCard = new StatCard();
        in.beginObject();
        String fieldName = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = in.nextName();
            }
            if ("nbTimesSeen".equals(fieldName)) {
                statCard.setNbTimesSeen(in.nextInt());
            }
            if ("nbTimesCorrect".equals(fieldName)) {
                statCard.setNbTimesCorrect(in.nextInt());
            }
            if ("nbTimesWrong".equals(fieldName)) {
                statCard.setNbTimesWrong(in.nextInt());
            }
            if ("timesSpent".equals(fieldName)) {
                in.beginArray();
                while (in.hasNext()) {
                    statCard.addTimeSpent(in.nextLong());
                }
                in.endArray();
            }
        }

        in.endObject();
        return statCard;
    }

}

package eu.telecomnancy.io.adapter;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import eu.telecomnancy.model.ApkgDeckListModel;

public class ApkgDecksAdapter extends TypeAdapter<ApkgDeckListModel> {
    ApkgAdapter adapter = new ApkgAdapter();

    @Override
    public void write(JsonWriter out, ApkgDeckListModel value) throws IOException {
        // not needed
    }

    @Override
    public ApkgDeckListModel read(JsonReader in) throws IOException {
        ApkgDeckListModel decks = new ApkgDeckListModel();

        in.beginObject();

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME))
                in.nextName();

            decks.add(adapter.read(in));
        }

        in.endObject();
        return decks;
    }

}

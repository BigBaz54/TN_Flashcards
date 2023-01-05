package eu.telecomnancy.io.adapter;

import java.io.File;
import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import eu.telecomnancy.model.Media;
import eu.telecomnancy.model.MediaType;

public class MediaAdapter extends TypeAdapter<Media> {

    @Override
    public void write(JsonWriter out, Media value) throws IOException {
        out.beginObject();
        out.name("file");
        out.value(value.getFile().getName());
        out.name("type");
        out.value(value.getType().toString());
        out.endObject();
    }

    @Override
    public Media read(JsonReader in) throws IOException {
        Media media = new Media();
        in.beginObject();
        String fieldName = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = in.nextName();
            }
            if ("file".equals(fieldName)) {
                media.setFile(in.nextString());
            }
            if ("type".equals(fieldName)) {
                media.setType(MediaType.fromString(in.nextString()));
            }
        }
        in.endObject();

        return media;
    }

}

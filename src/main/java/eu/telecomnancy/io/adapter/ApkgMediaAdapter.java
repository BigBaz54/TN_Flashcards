package eu.telecomnancy.io.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.model.ApkgMedia;

public class ApkgMediaAdapter extends TypeAdapter<ApkgMedia> {

    @Override
    public void write(JsonWriter out, ApkgMedia value) throws IOException {
        // not needed
    }

    @Override
    public ApkgMedia read(JsonReader in) throws IOException {
        String fieldName = null;
        ApkgMedia media = new ApkgMedia();

        in.beginObject();

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME))
                fieldName = in.nextName();

            media.addMedia(Integer.parseInt(fieldName), in.nextString());
        }

        in.endObject();
        return media;
    }

}

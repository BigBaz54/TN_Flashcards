package eu.telecomnancy.io.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import eu.telecomnancy.model.compact.ApkgDeckModel;

public class ApkgAdapter extends TypeAdapter<ApkgDeckModel> {

    @Override
    public void write(JsonWriter out, ApkgDeckModel value) throws IOException {
        // Not needed
    }

    @Override
    public ApkgDeckModel read(JsonReader in) throws IOException {
        String fieldName = null;
        ApkgDeckModel model = new ApkgDeckModel();

        in.beginObject();

        while (in.hasNext()) {
            JsonToken token = in.peek();

            if (token.equals(JsonToken.NAME))
                fieldName = in.nextName();

            if ("id".equals(fieldName))
                model.setId(in.nextLong());
            else if ("name".equals(fieldName))
                model.setName(in.nextString());
            else if ("desc".equals(fieldName))
                model.setDescription(in.nextString());
            else
                in.skipValue();
        }
        in.endObject();
        return model;
    }

}

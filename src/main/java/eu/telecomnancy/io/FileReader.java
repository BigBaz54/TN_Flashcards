package eu.telecomnancy.io;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import eu.telecomnancy.io.json.JsonFormatter;

public class FileReader<T> {
    private JsonFormatter<T> jsonFormatter;

    public FileReader(JsonFormatter<T> jsonFormatter) {
        this.jsonFormatter = jsonFormatter;
    }

    public JsonFormatter<T> getJsonFormatter() {
        return jsonFormatter;
    }

    private String getContent(String name) throws IOException {
        File file = new File("resources/decks/" + name);
        Scanner scanner = new Scanner(file);

        String content = "";
        while (scanner.hasNextLine()) {
            content += scanner.nextLine();
        }

        scanner.close();
        return content;
    }

    public T read(String name) throws IOException {
        return jsonFormatter.fromJson(getContent(name));
    }
}

package eu.telecomnancy;

import java.io.File;
import java.io.IOException;

import eu.telecomnancy.io.FileImporter;
import eu.telecomnancy.io.FileReader;
import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;

public class Test {
    public static void main(String[] args) throws IOException {
        String name = "test/test_import_1";
        FileImporter importer = new FileImporter();
        importer.imports(new File("resources/exports/" + name + ".zip"));
        DeckModel deckModel = new FileReader<DeckModel>(new JsonFormatterDeck()).read(name + ".json");
    }
}

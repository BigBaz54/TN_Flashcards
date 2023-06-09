package eu.telecomnancy.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import eu.telecomnancy.io.file.FileImporter;
import eu.telecomnancy.io.file.FileReader;
import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;

public class FileImporterTest {
    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void testImport1() throws IOException {
        String name = "test/test_import_1";
        FileImporter importer = new FileImporter();
        importer.importFromFile(new File("resources/exports/" + name + ".tlf"));
        DeckModel deckModel = new DeckModel();
        new FileReader<DeckModel>(new JsonFormatterDeck()).read(name + ".json", deckModel);
        assertEquals(deckModel.getName(), "test import 1");
    }
}

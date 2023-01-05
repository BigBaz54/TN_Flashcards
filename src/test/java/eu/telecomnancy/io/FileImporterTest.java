package eu.telecomnancy.io;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

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
        importer.imports(new File("resources/exports/" + name + ".zip"));
        DeckModel deckModel = new FileReader<DeckModel>(new JsonFormatterDeck()).read(name + ".json");
        assertEquals(deckModel.getName(), "test import 1");
    }
}

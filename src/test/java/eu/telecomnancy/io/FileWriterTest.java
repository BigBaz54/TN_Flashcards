package eu.telecomnancy.io;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;

import java.io.File;
import java.io.IOException;

public class FileWriterTest {
    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void test_write_1() {
        String json = "{\"cards\":[],\"name\":\"test 2\",\"description\":\"This is a test\"}";
        String name = "test_write_1";
        FileWriter writer = new FileWriter();
        try {
            writer.write(json, name);
            File file = new File("resources/decks/" + name + ".json");
            assertEquals(true, file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_write_2() {
        DeckModel deckModel = new DeckModel("test write 3", "This is a test");
        deckModel.addCard("Hello", "Bonjour");
        JsonFormatterDeck jsonFormatter = new JsonFormatterDeck(deckModel);
        jsonFormatter.setPretty(true);
        String json = jsonFormatter.toJson();

        String name = "test_write_2";
        FileWriter writer = new FileWriter();
        try {
            writer.write(json, name);
            File file = new File("resources/decks/" + name + ".json");
            assertEquals(true, file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

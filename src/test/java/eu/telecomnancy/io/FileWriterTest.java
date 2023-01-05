package eu.telecomnancy.io;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.Media;
import eu.telecomnancy.model.MediaType;

import java.io.File;
import java.io.IOException;

public class FileWriterTest {
    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void testWrite1() {
        String json = "{\"cards\":[],\"name\":\"test 2\",\"description\":\"This is a test\"}";
        String name = "test/test_write_1.json";
        FileWriter writer = new FileWriter();
        try {
            writer.writeJson(json, name);
            File file = new File("resources/decks/" + name);
            assertEquals(true, file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite2() {
        DeckModel deckModel = new DeckModel("test write 2", "This is a test");
        deckModel.addCard("Hello", "Bonjour");
        JsonFormatterDeck jsonFormatter = new JsonFormatterDeck(deckModel);
        jsonFormatter.setPretty(true);
        String json = jsonFormatter.toJson();

        String name = "test/test_write_2.json";
        FileWriter writer = new FileWriter();
        try {
            writer.writeJson(json, name);
            File file = new File("resources/decks/" + name);
            assertEquals(true, file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite3() {
        DeckModel deckModel = new DeckModel("test write 3", "This is a test");
        deckModel.addCard("Hello", "Bonjour");
        deckModel.getCard(0).setMedia(new Media("test", MediaType.IMG));
        JsonFormatterDeck jsonFormatter = new JsonFormatterDeck(deckModel);
        jsonFormatter.setPretty(true);
        String json = jsonFormatter.toJson();

        String name = "test/test_write_3.json";
        FileWriter writer = new FileWriter();
        try {
            writer.writeJson(json, name);
            File file = new File("resources/decks/" + name);
            assertEquals(true, file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite4() {
        DeckModel deckModel = new DeckModel("test write 4", "This is a test");
        deckModel.addCard("Hello", "Bonjour");
        deckModel.getCard(0).setMedia(new Media("test", MediaType.IMG));
        deckModel.addTag("test");
        deckModel.addTag("test2");
        JsonFormatterDeck jsonFormatter = new JsonFormatterDeck(deckModel);
        jsonFormatter.setPretty(true);
        String json = jsonFormatter.toJson();

        String name = "test/test_write_4.json";
        FileWriter writer = new FileWriter();
        try {
            writer.writeJson(json, name);
            File file = new File("resources/decks/" + name);
            assertEquals(true, file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite5() {
        DeckModel deckModel = new DeckModel("test write 5", "This is a test");
        deckModel.addCard("Hello", "Bonjour");
        deckModel.getCard(0).setMedia(new Media("test", MediaType.IMG));
        deckModel.addTag("test");
        deckModel.addTag("test2");
        deckModel.getCard(0).addTag("test");
        deckModel.getCard(0).addTag("test2");
        JsonFormatterDeck jsonFormatter = new JsonFormatterDeck(deckModel);
        jsonFormatter.setPretty(true);
        String json = jsonFormatter.toJson();

        String name = "test/test_write_5.json";
        FileWriter writer = new FileWriter();
        try {
            writer.writeJson(json, name);
            File file = new File("resources/decks/" + name);
            assertEquals(true, file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

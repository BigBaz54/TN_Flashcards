package eu.telecomnancy.io;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.compact.CDeckModel;

public class JsonFormatterTest {
    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void test_format_1() {
        DeckModel deckModel = new DeckModel("temp", "null");
        JsonFormatter jsonFormatter = new JsonFormatter(deckModel);
        String json = jsonFormatter.toJson();
        String expect = "{\"cards\":[],\"name\":\"temp\",\"description\":\"null\"}";
        assertEquals(expect, json);
    }

    @Test
    public void test_format_2() {
        DeckModel deckModel = new DeckModel("test 2", "This is a test");
        JsonFormatter jsonFormatter = new JsonFormatter(deckModel);
        String json = jsonFormatter.toJson();
        String expect = "{\"cards\":[],\"name\":\"test 2\",\"description\":\"This is a test\"}";
        assertEquals(expect, json);
    }

    @Test
    public void test_format_3() {
        DeckModel deckModel = new DeckModel("test 3", "This is a test");
        deckModel.addCard("Hello", "Bonjour");
        JsonFormatter jsonFormatter = new JsonFormatter(deckModel);
        String json = jsonFormatter.toJson();
        String expect = "{\"cards\":[{\"question\":\"Hello\",\"answer\":\"Bonjour\",\"probability\":1.0}],\"name\":\"test 3\",\"description\":\"This is a test\"}";
        assertEquals(expect, json);
    }

    @Test
    public void test_read_1() {
        String json = "{\n  \"cards\": [],\n  \"name\": \"test 2\",\n  \"description\": \"This is a test\"\n}";
        DeckModel deckModel = new JsonFormatter(null).deckFromJson(json);

        assertEquals("test 2", deckModel.getName());
        assertEquals("This is a test", deckModel.getDescription());
        assertEquals(0, deckModel.getCards().size());
    }

    @Test
    public void test_read_2() {
        String json = "{\"cards\":[{\"question\":\"Hello\",\"answer\":\"Bonjour\",\"probability\":1.0}],\"name\":\"test 2\",\"description\":\"This is a test\"}";
        DeckModel deckModel = new JsonFormatter(null).deckFromJson(json);

        assertEquals("test 2", deckModel.getName());
        assertEquals("This is a test", deckModel.getDescription());
        assertEquals(1, deckModel.getCards().size());
        assertEquals("Hello", deckModel.getCards().get(0).getQuestion());
        assertEquals("Bonjour", deckModel.getCards().get(0).getAnswer());
    }
}
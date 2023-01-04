package eu.telecomnancy.io;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import eu.telecomnancy.model.DeckModel;

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
        String expect = "{\n  \"cards\": [],\n  \"name\": \"temp\",\n  \"description\": \"null\"\n}";
        assertEquals(expect, json);
    }

    @Test
    public void test_format_2() {
        DeckModel deckModel = new DeckModel("test 2", "This is a test");
        JsonFormatter jsonFormatter = new JsonFormatter(deckModel);
        String json = jsonFormatter.toJson();
        String expect = "{\n  \"cards\": [],\n  \"name\": \"test 2\",\n  \"description\": \"This is a test\"\n}";
        assertEquals(expect, json);
    }
}

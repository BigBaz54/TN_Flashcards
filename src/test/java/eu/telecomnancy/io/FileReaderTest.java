package eu.telecomnancy.io;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;

import java.io.IOException;

public class FileReaderTest {
    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void test_read_1() {
        String name = "test_read_1";
        FileReader<DeckModel> fileReader = new FileReader<DeckModel>(new JsonFormatterDeck(null));
        try {
            DeckModel deckModel = fileReader.read(name);
            assertEquals("test", deckModel.getName());
            assertEquals("This is a test", deckModel.getDescription());
            assertEquals(1, deckModel.getCards().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_read_2() {
        String name = "test_read_2";
        FileReader<DeckModel> fileReader = new FileReader<DeckModel>(new JsonFormatterDeck(null));
        try {
            DeckModel deckModel = fileReader.read(name);
            assertEquals("test 2", deckModel.getName());
            assertEquals("This is a test 2", deckModel.getDescription());
            assertEquals(1, deckModel.getCards().size());
            assertEquals("Hola", deckModel.getCard(0).getAnswer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

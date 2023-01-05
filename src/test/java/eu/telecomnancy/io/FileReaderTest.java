package eu.telecomnancy.io;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import eu.telecomnancy.io.file.FileReader;
import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;

import java.io.IOException;

public class FileReaderTest {
    @Test
    public void test() {
        assertEquals(1, 1);
    }

    @Test
    public void testRead1() {
        String name = "test/test_read_1.json";
        FileReader<DeckModel> fileReader = new FileReader<DeckModel>(new JsonFormatterDeck(null));
        try {
            DeckModel deckModel = new DeckModel();
            fileReader.read(name, deckModel);
            assertEquals("test read 1", deckModel.getName());
            assertEquals("This is a test", deckModel.getDescription());
            assertEquals(1, deckModel.getCards().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead2() {
        String name = "test/test_read_2.json";
        FileReader<DeckModel> fileReader = new FileReader<DeckModel>(new JsonFormatterDeck(null));
        try {
            DeckModel deckModel = new DeckModel();
            fileReader.read(name, deckModel);
            assertEquals("test read 2", deckModel.getName());
            assertEquals("This is a test 2", deckModel.getDescription());
            assertEquals(1, deckModel.getCards().size());
            assertEquals("Hola", deckModel.getCard(0).getAnswer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead3() {
        String name = "test/test_read_3.json";
        FileReader<DeckModel> fileReader = new FileReader<DeckModel>(new JsonFormatterDeck(null));
        try {
            DeckModel deckModel = new DeckModel();
            fileReader.read(name, deckModel);
            assertEquals("test read 3", deckModel.getName());
            assertEquals("This is a test 3", deckModel.getDescription());
            assertEquals(1, deckModel.getCards().size());
            assertEquals("Bonjour", deckModel.getCard(0).getAnswer());
            assertEquals("test", deckModel.getTags().get(0).getName());
            assertEquals("test2", deckModel.getTags().get(1).getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead4() {
        String name = "test/test_read_4.json";
        FileReader<DeckModel> fileReader = new FileReader<DeckModel>(new JsonFormatterDeck(null));
        try {
            DeckModel deckModel = new DeckModel();
            fileReader.read(name, deckModel);
            assertEquals("test read 4", deckModel.getName());
            assertEquals("This is a test 4", deckModel.getDescription());
            assertEquals(1, deckModel.getCards().size());
            assertEquals("Bonjour", deckModel.getCard(0).getAnswer());
            assertEquals("test", deckModel.getTags().get(0).getName());
            assertEquals("test2", deckModel.getTags().get(1).getName());
            assertEquals("test", deckModel.getCard(0).getTags().get(0).getName());
            assertEquals("test2", deckModel.getCard(0).getTags().get(1).getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Deprecated
    public void testRead5() {
        String name = "test/test_read_5.json";
        FileReader<DeckModel> fileReader = new FileReader<DeckModel>(new JsonFormatterDeck(null));
        try {
            DeckModel deckModel = new DeckModel();
            fileReader.read(name, deckModel);
            assertEquals("test read 5", deckModel.getName());
            assertEquals("This is a test 5", deckModel.getDescription());
            assertEquals(1, deckModel.getCards().size());
            assertEquals("Bonjour", deckModel.getCard(0).getAnswer());
            assertEquals("test", deckModel.getTags().get(0).getName());
            assertEquals("test2", deckModel.getTags().get(1).getName());
            assertEquals("test", deckModel.getCard(0).getTags().get(0).getName());
            assertEquals("test2", deckModel.getCard(0).getTags().get(1).getName());
            assertEquals(0, deckModel.getStatDeck().getNbTimesCorrect());
            assertEquals(0, deckModel.getStatDeck().getNbTimesWrong());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

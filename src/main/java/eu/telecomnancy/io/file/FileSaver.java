package eu.telecomnancy.io.file;

import java.io.IOException;

import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;

public class FileSaver {
    private FileWriter fileWriter;
    private JsonFormatterDeck jsonFormatterDeck;

    public FileSaver(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
        this.jsonFormatterDeck = new JsonFormatterDeck();
    }

    public void saveDeck(DeckModel deckModel) throws IOException {
        jsonFormatterDeck.setModel(deckModel);
        jsonFormatterDeck.setPretty(true);
        fileWriter.writeJson(jsonFormatterDeck.toJson(), deckModel.getName() + ".json");
    }
}

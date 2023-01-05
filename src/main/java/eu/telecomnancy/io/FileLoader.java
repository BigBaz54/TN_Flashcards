package eu.telecomnancy.io;

import java.io.File;

import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.model.DeckModel;

public class FileLoader {
    private DeckListController deckListController;
    private FileReader<DeckModel> fileReader;

    public FileLoader(FileReader<DeckModel> fileReader) {
        this.fileReader = fileReader;
    }

    public void setDeckListController(DeckListController deckListController) {
        this.deckListController = deckListController;
    }

    public void loadDecks() {
        if (deckListController == null) {
            throw new RuntimeException("DeckListController is not set");
        }

        File dir = new File("resources/decks");

        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                try {
                    deckListController.importDeck(fileReader.read(file.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

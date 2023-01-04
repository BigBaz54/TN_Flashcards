package eu.telecomnancy.io;

import java.io.File;

import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.model.DeckModel;

public class FileLoader {
    private DeckListController deckListController;
    private FileReader<DeckModel> fileReader;

    public FileLoader(DeckListController deckListController, FileReader<DeckModel> fileReader) {
        this.deckListController = deckListController;
        this.fileReader = fileReader;
    }

    public void loadDecks() {
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

package eu.telecomnancy.controller;

import java.io.File;

import eu.telecomnancy.io.file.FileController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.model.DeckModel;
import javafx.stage.FileChooser;

public class DeckListController {
    private FileController fileController;
    private DeckListModel deckListModel;

    public DeckListController(DeckListModel deckListModel, FileController fileController) {
        this.deckListModel = deckListModel;
        this.fileController = fileController;
        fileController.setDeckListController(this);
    }

    public DeckListModel getDeckListModel() {
        return deckListModel;
    }

    public void createDeck(String name, String description) {
        deckListModel.createDeck(name, description);
    }

    public DeckModel createEmptyDeck() {
        DeckModel deck = new DeckModel();
        return deck;
    }

    public void removeDeck(int i) {
        deckListModel.removeDeck(i);
    }

    public void exportDeck(int i) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ZIP files (*.zip)", "*.zip");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName(deckListModel.getDecks().get(i).getName() + ".zip");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            fileController.exportDeck(deckListModel.getDecks().get(i), file);
        } else {
            fileController.exportDeck(deckListModel.getDecks().get(i),
                    new File("resources/exports/" + deckListModel.getDecks().get(i).getName() + ".zip"));
        }
    }

    public void addDeck(DeckModel deck) {
        deckListModel.addDeck(deck);
    }

    public void importDeck() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ZIP files (*.zip)", "*.zip");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            fileController.importFromFile(file);
        }
    }
}

package eu.telecomnancy.controller;

import eu.telecomnancy.io.FileController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.model.DeckModel;

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
        fileController.saveDeck(deckListModel.getDecks().get(i));
    }

    public void importDeck(DeckModel deck) {
        deckListModel.addDeck(deck);
    }

}

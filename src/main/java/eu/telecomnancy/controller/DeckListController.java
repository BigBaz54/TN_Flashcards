package eu.telecomnancy.controller;

import eu.telecomnancy.model.DeckListModel;

public class DeckListController {
    
    private DeckListModel deckListModel;

    public DeckListController(DeckListModel deckListModel) {
        this.deckListModel = deckListModel;
    }

    public void createDeck(String name, String description) {
        deckListModel.addDeck(name, description);
    }

    public void removeDeck(int i) {
        deckListModel.removeDeck(i);
    }

    public void exportDeck(int i) {
        // TODO:
    }

    public void importDeck() {
        // TODO:
    }
}

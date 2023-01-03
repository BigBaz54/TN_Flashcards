package eu.telecomnancy.controller;

import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.view.DeckView;

public class DeckController {
    private DeckModel deckModel;
    private DeckView deckView;

    public DeckController(DeckModel deckModel, DeckView deckView) {
        this.deckModel = deckModel;
        this.deckView = deckView;
    }

    public void setName(String name) {
        deckModel.setName(name);
    }

    public void setDescription(String description) {
        deckModel.setDescription(description);
    }

    public void addCard(String question, String answer) {
        deckModel.addCard(question, answer);
    }

    public void removeCard(int i) {
        deckModel.removeCard(i);
    }
}

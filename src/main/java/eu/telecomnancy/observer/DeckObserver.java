package eu.telecomnancy.observer;

import eu.telecomnancy.model.DeckModel;

public abstract class DeckObserver implements Observer{

    protected DeckModel deckModel;

    protected DeckObserver(DeckModel deckModel) {
        this.deckModel = deckModel;
        deckModel.addObserver(this);
    }
}

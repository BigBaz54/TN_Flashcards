package eu.telecomnancy.observer;

import eu.telecomnancy.model.DeckListModel;

public abstract class DeckListObserver implements Observer{
    protected DeckListModel deckListModel;

    protected DeckListObserver(DeckListModel deckListModel) {
        this.deckListModel = deckListModel;
        deckListModel.addObserver(this);
    }
    
}

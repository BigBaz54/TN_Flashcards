package eu.telecomnancy.observer;

import eu.telecomnancy.model.DeckListModel;

public abstract class DeckListObserver implements Observer{

    protected DeckListModel deckList;

    public abstract void react();

    public DeckListObserver(DeckListModel deckList) {
        this.deckList = deckList;
        deckList.addObserver(this);
    }
    
}

package eu.telecomnancy.observer;

import eu.telecomnancy.model.DeckList;

public abstract class DeckListObserver implements Observer{

    protected DeckList deckList;

    public abstract void react();

    public DeckListObserver(DeckList deckList) {
        deckList.addObserver(this);
    }
    
}

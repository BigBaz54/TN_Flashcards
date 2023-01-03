package eu.telecomnancy.observer;

import eu.telecomnancy.model.Deck;

public abstract class DeckObserver implements Observer{

    protected Deck deck;

    public DeckObserver(Deck deck) {
        deck.addObserver(this);
    }


    @Override
    public abstract void react();
    
}

package eu.telecomnancy.observer;

import eu.telecomnancy.model.DeckModel;

public abstract class DeckObserver implements Observer{

    protected DeckModel deck;

    public DeckObserver(DeckModel deck) {
        this.deck = deck;
        deck.addObserver(this);
    }


    @Override
    public abstract void react();
    
}

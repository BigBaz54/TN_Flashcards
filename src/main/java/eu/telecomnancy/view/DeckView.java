package eu.telecomnancy.view;

import eu.telecomnancy.model.Deck;
import eu.telecomnancy.observer.DeckObserver;

public class DeckView extends DeckObserver{


    public DeckView(Deck deck) {
        super(deck);
    }

    @Override
    public void react() {
        
    }



    
}

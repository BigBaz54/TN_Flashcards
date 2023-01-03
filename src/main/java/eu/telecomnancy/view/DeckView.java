package eu.telecomnancy.view;

import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;

public class DeckView extends DeckObserver{


    public DeckView(DeckModel deck) {
        super(deck);
    }

    @Override
    public void react() {
        
    }



    
}

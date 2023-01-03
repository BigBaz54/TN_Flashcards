package eu.telecomnancy.observer;

import eu.telecomnancy.controller.CardController;
import eu.telecomnancy.model.CardModel;

public abstract class CardObserver implements Observer {
    private CardController cardController;
    private CardModel cardModel;
}

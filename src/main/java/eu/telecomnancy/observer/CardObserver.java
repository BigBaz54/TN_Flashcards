package eu.telecomnancy.observer;

import eu.telecomnancy.model.CardModel;

public abstract class CardObserver implements Observer {
    private CardModel cardModel;

    protected CardObserver(CardModel cardModel) {
        this.cardModel = cardModel;
        cardModel.addObserver(this);
    }
}

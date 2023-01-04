package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;

public abstract class Learning {
    protected DeckController deckController;
    public abstract boolean isFinished();

    public void nextCard(boolean goodAnswer) {
        if (goodAnswer) {
            deckController.answeredRight();
        } else {
            deckController.answeredWrong();
        }
        deckController.handleAnswer(goodAnswer);
        deckController.nextCard();
    }
}

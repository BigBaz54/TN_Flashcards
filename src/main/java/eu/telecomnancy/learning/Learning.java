package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;

public abstract class Learning {
    protected DeckController deckController;
    protected DrawCardStrategy drawCardStrategy;
    public abstract boolean isFinished();

    public void nextCard(boolean goodAnswer) {
        if (goodAnswer) {
            deckController.answeredRight();
        } else {
            deckController.answeredWrong();
        }
        deckController.handleAnswer(goodAnswer, drawCardStrategy);
        deckController.nextCard(drawCardStrategy);
    }
}

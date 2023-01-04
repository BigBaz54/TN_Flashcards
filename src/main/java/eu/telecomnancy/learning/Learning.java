package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;

public abstract class Learning {
    protected DeckController deckController;
    protected DrawCardStrategy drawCardStrategy;
    protected long beginLastCard;
    public abstract boolean isFinished();

    public void nextCard(boolean goodAnswer) {
        long timeSpent = System.currentTimeMillis() - beginLastCard;
        deckController.updateStatCard(goodAnswer, timeSpent);
        deckController.handleAnswer(goodAnswer, drawCardStrategy);
        deckController.nextCard(drawCardStrategy);
        beginLastCard = System.currentTimeMillis();
    }
}

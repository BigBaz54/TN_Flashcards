package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;

public class LearningTimeLimit extends Learning {
    private int timeMax;
    private int timePlayed;

    public LearningTimeLimit(DeckController deckController, DrawCardStrategy drawCardStrategy, int timeMax) {
        this.deckController = deckController;
        this.drawCardStrategy = drawCardStrategy;
        this.timeMax = timeMax;
        this.timePlayed = 0;
        this.beginLastCard = System.currentTimeMillis();

        this.deckController.updateStatDeck();
    }

    public boolean isFinished() {
        return timePlayed >= timeMax;
    }

    public void updateConcreteLearning(boolean goodAnswer) {
        timePlayed += System.currentTimeMillis() - beginLastCard;
    }
}

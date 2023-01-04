package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;

public class LearningXTimes extends Learning {
    private int nbTimesMax;
    private int nbTimesPlayed;

    public LearningXTimes(DeckController deckController, DrawCardStrategy drawCardStrategy, int nbTimesMax) {
        this.deckController = deckController;
        this.drawCardStrategy = drawCardStrategy;
        this.nbTimesMax = nbTimesMax;
        this.nbTimesPlayed = 0;
    }

    public boolean isFinished() {
        return nbTimesPlayed >= nbTimesMax;
    }
}

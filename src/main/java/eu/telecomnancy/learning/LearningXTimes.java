package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;

public class LearningXTimes extends Learning {
    private int nbTimesMax;
    private int nbTimesPlayed;

    public LearningXTimes(DeckController deckController, int nbTimesMax) {
        this.deckController = deckController;
        this.nbTimesMax = nbTimesMax;
        this.nbTimesPlayed = 0;
    }

    public boolean isFinished() {
        return nbTimesPlayed >= nbTimesMax;
    }
}

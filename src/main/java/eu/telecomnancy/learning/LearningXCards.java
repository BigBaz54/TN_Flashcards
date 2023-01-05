package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;

public class LearningXCards extends Learning {
    private int nbTimesMax;
    private int nbTimesPlayed;

    public LearningXCards(DeckController deckController, DrawCardStrategy drawCardStrategy, int nbTimesMax) {
        this.deckController = deckController;
        this.drawCardStrategy = drawCardStrategy;
        this.nbTimesMax = nbTimesMax;
        this.nbTimesPlayed = 0;
        this.beginLastCard = System.currentTimeMillis();

        this.deckController.updateStatDeck();
    }

    public boolean isFinished() {
        return nbTimesPlayed >= nbTimesMax;
    }
}

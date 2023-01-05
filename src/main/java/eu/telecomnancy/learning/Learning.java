package eu.telecomnancy.learning;

import java.util.ArrayList;

import eu.telecomnancy.CardTag;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.model.StatLearning;

public abstract class Learning {
    protected DeckController deckController;
    protected DrawCardStrategy drawCardStrategy;
    protected Long beginLastCard;
    protected StatLearning statLearning = new StatLearning();
    public abstract boolean isFinished();
    public abstract void updateConcreteLearning();

    public void nextCard(boolean goodAnswer) {
        Long timeSpent = System.currentTimeMillis() - beginLastCard;
        updateConcreteLearning();
        updateStatLearning(goodAnswer, timeSpent);
        deckController.updateStatCard(goodAnswer, timeSpent);
        deckController.handleAnswer(goodAnswer, drawCardStrategy);
        deckController.nextCard(drawCardStrategy);
        beginLastCard = System.currentTimeMillis();
    }

    protected void updateStatLearning(boolean goodAnswer, Long timeSpent) {
        ArrayList<CardTag> tags = deckController.getDeckModel().getCard(deckController.getDeckModel().getActiveCard()).getTags();
        for (CardTag tag : tags) {
            statLearning.incrementNbPlayedByTag(tag.getName());
            if (goodAnswer) {
                statLearning.incrementNbCorrectByTag(tag.getName());
            }
        }
        statLearning.incrementNbPlayed();
        if (goodAnswer) {
            statLearning.incrementNbCorrect();
        }
        statLearning.incrementTimePlayed(timeSpent);
        statLearning.addTimeCard(timeSpent);
    }

    public StatLearning getStatLearning() {
        return statLearning;
    }
}

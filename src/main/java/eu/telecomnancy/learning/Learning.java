package eu.telecomnancy.learning;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.model.StatLearning;

public abstract class Learning {
    protected DeckController deckController;
    protected DrawCardStrategy drawCardStrategy;
    protected long beginLastCard;
    protected StatLearning statLearning = new StatLearning();
    public abstract boolean isFinished();
    public abstract void updateConcreteLearning();

    public void nextCard(boolean goodAnswer) {
        long timeSpent = System.currentTimeMillis() - beginLastCard;
        updateConcreteLearning();
        updateStatLearning(goodAnswer, timeSpent);
        deckController.updateStatCard(goodAnswer, timeSpent);
        deckController.handleAnswer(goodAnswer, drawCardStrategy);
        deckController.nextCard(drawCardStrategy);
        beginLastCard = System.currentTimeMillis();
    }

    protected void updateStatLearning(boolean goodAnswer, long timeSpent) {
        statLearning.incrementNbPlayed();
        if (goodAnswer) {
            statLearning.incrementNbCorrect();
        }
        statLearning.incrementTimePlayed(timeSpent);
    }
}

package eu.telecomnancy.drawCardStrategy;

import java.util.ArrayList;

import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.learning.LearningSession;
import eu.telecomnancy.model.DeckModel;

public class DrawCardStrategyRandom implements DrawCardStrategy {
    public void handleAnswer(boolean answer, DeckModel deckModel) {
        // Nothing to do
    }

    public int nextCard(DeckModel deckModel, Learning learning) {
        if (learning instanceof LearningSession) {
            ArrayList<Integer> toLearn = ((LearningSession) learning).getToLearn();
            return (int) toLearn.get((int) (Math.random()*(toLearn.size())));
        }
        return (int) (Math.random() * deckModel.getCards().size());
    }
}

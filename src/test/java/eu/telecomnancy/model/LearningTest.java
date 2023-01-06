package eu.telecomnancy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyWeighted;
import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.learning.LearningXCards;

public class LearningTest {
    @Test
    public void test_proba() {
        DeckModel deckModel = new DeckModel();
        CardModel card1 = new CardModel("question1", "answer1");
        card1.addTag("tag1");
        CardModel card2 = new CardModel("question2", "answer2");
        card2.addTag("tag1");
        CardModel card3 = new CardModel("question3", "answer3");
        card3.addTag("tag2");
        deckModel.addCard(card1);
        deckModel.addCard(card2);
        deckModel.addCard(card3);
        DeckController deckController = new DeckController(deckModel);
        Learning learning = new LearningXCards(deckController, new DrawCardStrategyWeighted(), 10);
        StatLearning statLearning = learning.getStatLearning();

        assertEquals(1, card1.getProbability(), 0.001);
        assertEquals(1, card2.getProbability(), 0.001);
        assertEquals(1, card3.getProbability(), 0.001);

        learning.nextCard(false);
        assertEquals(1, card1.getProbability(), 0.001);

        card1.setProbability(1);
        deckModel.setActiveCard(0);
        learning.nextCard(true);
        assertEquals(0.5, card1.getProbability(), 0.001);
    }

    @Test
    public void test_stats() {
        DeckModel deckModel = new DeckModel();
        CardModel card1 = new CardModel("question1", "answer1");
        card1.addTag("tag1");
        CardModel card2 = new CardModel("question2", "answer2");
        card2.addTag("tag2");
        CardModel card3 = new CardModel("question3", "answer3");
        card3.addTag("tag1");
        card3.addTag("tag2");
        deckModel.addCard(card1);
        deckModel.addCard(card2);
        deckModel.addCard(card3);
        DeckController deckController = new DeckController(deckModel);
        Learning learning = new LearningXCards(deckController, new DrawCardStrategyWeighted(), 10);
        StatLearning statLearning = learning.getStatLearning();

        assertEquals(0, statLearning.getNbPlayed());
        assertEquals(0, statLearning.getNbCorrect());
        assertEquals(0, statLearning.getTimePlayed(), 0);
        assertEquals(0, statLearning.getTimeCards().size());
        assertEquals(0, statLearning.getNbPlayedByTag().size());
        assertEquals(0, deckModel.getActiveCard());

        learning.nextCard(true);
        assertEquals(1, statLearning.getNbPlayed());
        assertEquals(1, statLearning.getNbCorrect());
        assertEquals(1, statLearning.getNbCorrectByTag().get("tag1"), 0.01);

        deckModel.setActiveCard(1);
        learning.nextCard(false);
        assertEquals(2, statLearning.getNbPlayed());
        assertEquals(1, statLearning.getNbCorrect());
        assertEquals(1, statLearning.getNbCorrectByTag().get("tag1"), 0.01);
        assertEquals(0, statLearning.getNbCorrectByTag().get("tag2"), 0.01);

        deckModel.setActiveCard(2);
        learning.nextCard(true);
        assertEquals(3, statLearning.getNbPlayed());
        assertEquals(2, statLearning.getNbCorrect());
        assertEquals(2, statLearning.getNbCorrectByTag().get("tag1"), 0.01);
        assertEquals(1, statLearning.getNbCorrectByTag().get("tag2"), 0.01);
    }

    @Test
    public void test_finished() throws InterruptedException {
        DeckModel deckModel = new DeckModel();
        CardModel card1 = new CardModel("question1", "answer1");
        card1.addTag("tag1");
        CardModel card2 = new CardModel("question2", "answer2");
        card2.addTag("tag1");
        CardModel card3 = new CardModel("question3", "answer3");
        card3.addTag("tag2");
        deckModel.addCard(card1);
        deckModel.addCard(card2);
        deckModel.addCard(card3);
        DeckController deckController = new DeckController(deckModel);
        Learning learning = new LearningXCards(deckController, new DrawCardStrategyWeighted(), 10);
        StatLearning statLearning = learning.getStatLearning();

        assertEquals(false, learning.isFinished());
        learning.nextCard(false);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(false, learning.isFinished());
        learning.nextCard(true);
        assertEquals(true, learning.isFinished());
    }
}

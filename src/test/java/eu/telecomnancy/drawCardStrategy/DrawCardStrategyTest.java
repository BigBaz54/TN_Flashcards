package eu.telecomnancy.drawCardStrategy;

import org.junit.Test;

import eu.telecomnancy.model.DeckModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DrawCardStrategyTest {
    @Test
    // checking that the nextCard methods work
    public void test1() {
        DeckModel deck = new DeckModel("name", "description");
        deck.addCard("question1", "answer1");
        deck.addCard("question2", "answer2");
        deck.addCard("question3", "answer3");

        DrawCardStrategyRandom random = new DrawCardStrategyRandom();
        int drawn = random.nextCard(deck);
        assertTrue(drawn >= 0 && drawn < deck.getCards().size());

        DrawCardStrategyWeighted weighted = new DrawCardStrategyWeighted();
        drawn = weighted.nextCard(deck);
        assertTrue(drawn >= 0 && drawn < deck.getCards().size());

        // DrawCardStrategyTime time = new DrawCardStrategyTime();
        // drawn = time.nextCard(deck);
        // assertTrue(drawn >= 0 && drawn < deck.getCards().size());
    }

    @Test
    // checking that the handleAnswer methods work
    public void test2() {
        DeckModel deck = new DeckModel("name", "description");
        deck.addCard("question1", "answer1");
        float savedProba;

        savedProba = deck.getCard(0).getProbability();
        DrawCardStrategyRandom random = new DrawCardStrategyRandom();
        random.handleAnswer(false, deck);
        assertTrue(savedProba == deck.getCard(0).getProbability());
        assertTrue(savedProba >= 0 && savedProba <= 1);

        savedProba = deck.getCard(0).getProbability();
        random.handleAnswer(true, deck);
        assertTrue(savedProba == deck.getCard(0).getProbability());
        assertTrue(savedProba >= 0 && savedProba <= 1);


        savedProba = deck.getCard(0).getProbability();
        DrawCardStrategyWeighted weighted = new DrawCardStrategyWeighted();
        weighted.handleAnswer(false, deck);
        assertTrue(savedProba <= deck.getCard(0).getProbability());
        assertTrue(savedProba >= 0 && savedProba <= 1);

        savedProba = deck.getCard(0).getProbability();
        weighted.handleAnswer(true, deck);
        assertTrue(savedProba >= deck.getCard(0).getProbability());
        assertTrue(savedProba >= 0 && savedProba <= 1);
    }
}
package eu.telecomnancy.model;

import org.junit.Test;

import eu.telecomnancy.DeckTag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeckModelTest {
    @Test
    // checking that the constructor works
    public void test1() {
        DeckModel deck = new DeckModel("name", "description");
        assertEquals("name", deck.getName());
        assertEquals("description", deck.getDescription());
        assertEquals(0, deck.getCards().size());
        assertEquals(0, deck.getTags().size());
        assertEquals(0, deck.getStatDeck().getNbTimesCorrect());
        assertEquals(0, deck.getStatDeck().getNbTimesWrong());
    }

    @Test
    // checking that adding and removing cards works
    public void test2() {
        DeckModel deck = new DeckModel("name", "description");
        deck.addCard("question1", "answer1");
        deck.addCard("question2", "answer2");
        deck.addCard("question3", "answer3");
        assertEquals(3, deck.getCards().size());
        deck.removeCard(2);
        assertEquals(2, deck.getCards().size());
    }

    @Test
    // adding and removing tags + checking that the list of used tags is updated and independent from the card's one
    public void test3() {
        DeckModel deck = new DeckModel("name", "description");
        deck.addCard("question1", "answer1");
        deck.getCard(0).addTag("cardTag1");
        
        deck.addTag("deckTag1");
        deck.addTag("deckTag2");
        deck.addTag("deckTag3");
        assertEquals(3, deck.getTags().size());
        deck.removeTag("deckTag2");
        assertEquals(2, deck.getTags().size());
        assertEquals(3, DeckTag.getListUsed().size());
        assertTrue(!DeckTag.getListUsed().contains("cardTag1"));
    }
}

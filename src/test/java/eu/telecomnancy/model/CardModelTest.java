package eu.telecomnancy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import eu.telecomnancy.CardTag;

public class CardModelTest {
    @Test
    // checking that the constructor works
    public void test1() {
        CardModel card = new CardModel("question1", "answer1");
        assertEquals("question1", card.getQuestion());
        assertEquals("answer1", card.getAnswer());
        assertEquals(1F, card.getProbability(), 0.01);
        assertEquals(0, card.getTags().size());
        assertEquals(0, card.getStatCard().getNbTimesCorrect());
        assertEquals(0, card.getStatCard().getNbTimesWrong());
    }

    @Test
    // checking that the number of times correct and wrong is updated
    public void test2() {
        CardModel card = new CardModel("question1", "answer1");
        card.getStatCard().setNbTimesCorrect(1);
        card.getStatCard().setNbTimesWrong(1);
        card.getStatCard().setNbTimesCorrect(2);
        assertEquals(2, card.getStatCard().getNbTimesCorrect());
        assertEquals(1, card.getStatCard().getNbTimesWrong());
    }

    @Test
    // adding and removing tags + checking that the list of used tags is updated
    public void test3() {
        CardModel card = new CardModel("question1", "answer1");
        card.addTag("tag1");
        card.addTag("tag2");
        card.addTag("tag3");
        assertEquals(3, card.getTags().size());
        card.removeTag("tag2");
        assertEquals(2, card.getTags().size());
        assertEquals(3, CardTag.getListUsed().size());
    }
}

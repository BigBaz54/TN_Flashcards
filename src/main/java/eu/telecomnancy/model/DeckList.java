package eu.telecomnancy.model;

import java.util.ArrayList;
import eu.telecomnancy.observer.*;

public class DeckList extends Observed{
    private static final DeckList INSTANCE = new DeckList();
    private final ArrayList<Deck> decks;

    public DeckList() {
        decks = new ArrayList<>();
    }

    public static DeckList getInstance() {
        return INSTANCE;
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    public void removeDeck(Deck deck) {
        decks.remove(deck);
    }

    public Deck[] getDecks() {
        return decks.toArray(new Deck[0]);
    }
}

package eu.telecomnancy.model;

import java.util.ArrayList;
import eu.telecomnancy.observer.*;

public class DeckListModel extends Observed{
    private static final DeckListModel INSTANCE = new DeckListModel();
    private final ArrayList<DeckModel> decks;

    public DeckListModel() {
        decks = new ArrayList<>();
    }

    public static DeckListModel getInstance() {
        return INSTANCE;
    }

    public void addDeck(DeckModel deck) {
        decks.add(deck);
        notifyObservers();
    }

    public void removeDeck(DeckModel deck) {
        decks.remove(deck);
        notifyObservers();
    }

    public DeckModel[] getDecks() {
        return decks.toArray(new DeckModel[0]);
    }
}

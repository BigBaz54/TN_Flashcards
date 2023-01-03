package eu.telecomnancy.model;

import java.util.ArrayList;

public class DeckListModel extends Observed {
    private final ArrayList<DeckModel> decks;

    public DeckListModel() {
        decks = new ArrayList<>();
    }

    public DeckListModel(ArrayList<DeckModel> decks) {
        this.decks = decks;
    }

    public void addDeck(DeckModel deck) {
        decks.add(deck);
        notifyObservers();
    }

    public void removeDeck(DeckModel deck) {
        decks.remove(deck);
        notifyObservers();
    }
}

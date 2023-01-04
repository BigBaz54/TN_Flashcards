package eu.telecomnancy.model;

import java.util.ArrayList;

public class DeckListModel extends Observed {
    private final ArrayList<DeckModel> decks;
    private StatDeckList statDeck;

    public StatDeckList getStatDeck() {
        return statDeck;
    }

    public DeckListModel() {
        decks = new ArrayList<>();
        statDeck = new StatDeckList();

    }

    public DeckListModel(ArrayList<DeckModel> decks) {
        this.decks = decks;

    }

    public void createDeck(String name, String description) {
        decks.add(new DeckModel(name, description));
        notifyObservers();
    }

    public void addDeck(DeckModel deck) {
        decks.add(deck);
        notifyObservers();
    }

    public void removeDeck(int i) {
        decks.remove(i);
        notifyObservers();
    }

    public ArrayList<DeckModel> getDecks() {
        return decks;
    }
}

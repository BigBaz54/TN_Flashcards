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

    public void addDeck(String name, String description) {
        decks.add(new DeckModel(name, description));
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

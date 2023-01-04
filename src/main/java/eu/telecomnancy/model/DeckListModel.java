package eu.telecomnancy.model;

import java.util.ArrayList;

public class DeckListModel extends Observed {
    private final ArrayList<DeckModel> decks;
    private StatDeckList statDeck;
    private Mode mode;

    public StatDeckList getStatDeck() {
        return statDeck;
    }

    public DeckListModel() {
        decks = new ArrayList<>();
        statDeck = new StatDeckList();
        statDeck = new StatDeckList();
        mode = Mode.VIEW;
    }

    public DeckListModel(ArrayList<DeckModel> decks) {
        this.decks = decks;
        mode = Mode.VIEW;
    }

    public void addDeck(String name, String description) {
        decks.add(new DeckModel(name, description, mode));
        statDeck.addDeck(new StatDeck(name));
        notifyObservers();
    }

    public void removeDeck(int i) {
        decks.remove(i);
        notifyObservers();
    }

    public ArrayList<DeckModel> getDecks() {
        return decks;
    }

    public Mode getMode(){
        return this.mode;
    }

    public void setMode(Mode mode){
        this.mode = mode;
        for (DeckModel deck : decks) {
            deck.setMode(mode);
        }
        notifyObservers();
    }
}

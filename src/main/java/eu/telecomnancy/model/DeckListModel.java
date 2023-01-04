package eu.telecomnancy.model;

import java.util.ArrayList;

public class DeckListModel extends Observed {
    private final ArrayList<DeckModel> decks;
    private StatDeck statDeck;
    private Mode mode;

    public DeckListModel() {
        decks = new ArrayList<>();
        statDeck = new StatDeck();
        mode = Mode.VIEW;
    }

    public DeckListModel(ArrayList<DeckModel> decks) {
        this.decks = decks;
        mode = Mode.VIEW;
    }

    public void addDeck(String name, String description) {
        decks.add(new DeckModel(name, description, mode));
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

package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyWeighted;

public class DeckListModel extends Observed {
    private final ArrayList<DeckModel> decks;
    private StatDeckList statDeck;
    private DrawCardStrategy drawCardStrategy;
    
    public StatDeckList getStatDeck() {
        return statDeck;
    }

    public DeckListModel() {
        decks = new ArrayList<>();
        statDeck = new StatDeckList();
        drawCardStrategy = new DrawCardStrategyWeighted();
    }

    public DeckListModel(ArrayList<DeckModel> decks) {
        this.decks = decks;
        statDeck = new StatDeckList();
        drawCardStrategy = new DrawCardStrategyWeighted();
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

    public DrawCardStrategy getDrawCardStrategy() {
        return drawCardStrategy;
    }

    public void setDrawCardStrategy(DrawCardStrategy drawCardStrategy) {
        this.drawCardStrategy = drawCardStrategy;
        notifyObservers();
    }
}

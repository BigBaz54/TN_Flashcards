package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyWeighted;

public class DeckListModel extends Observed {
    private final ArrayList<DeckModel> decks;
    private StatDeckList statDeck;
    private DrawCardStrategy drawCardStrategy;
    private BuildCardStrategy buildCardStrategy;

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
        buildCardStrategy = new BuildCardStrategyClassic();
    }

    public void createDeck(String name, String description) {
        DeckModel deck = new DeckModel(name, description);
        decks.add(deck);
        statDeck.addDeck(deck.getStatDeck());
        notifyObservers();
    }

    public void addDeck(DeckModel deck) {
        decks.add(deck);
        statDeck.addDeck(deck.getStatDeck());
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
        for (DeckModel deck : decks) {
            deck.setDrawCardStrategy(drawCardStrategy);
        }
        notifyObservers();
    }

    public BuildCardStrategy getBuildCardStrategy() {
        return buildCardStrategy;
    }

    public void setBuildCardStrategy(BuildCardStrategy buildCardStrategy) {
        this.buildCardStrategy = buildCardStrategy;
        for (DeckModel deck : decks) {
            deck.setBuildCardStrategy(buildCardStrategy);
        }
        notifyObservers();
    }
}

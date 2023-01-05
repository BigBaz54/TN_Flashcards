package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyWeighted;

public class DeckListModel extends Observed {
    private final ArrayList<DeckModel> decks;
    private DrawCardStrategy drawCardStrategy;
    private BuildCardStrategy buildCardStrategy;

    public DeckListModel() {
        decks = new ArrayList<>();
        buildCardStrategy = new BuildCardStrategyClassic();
        drawCardStrategy = new DrawCardStrategyWeighted();
    }

    public DeckListModel(ArrayList<DeckModel> decks) {
        this.decks = decks;
        drawCardStrategy = new DrawCardStrategyWeighted();
        buildCardStrategy = new BuildCardStrategyClassic();
    }

    public void createDeck(String name, String description) {
        DeckModel deck = new DeckModel(name, description);
        decks.add(deck);
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

    public BuildCardStrategy getBuildCardStrategy() {
        return buildCardStrategy;
    }

    public void setBuildCardStrategy(BuildCardStrategy buildCardStrategy) {
        this.buildCardStrategy = buildCardStrategy;
        notifyObservers();
    }
}

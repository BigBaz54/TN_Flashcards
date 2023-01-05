package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.DeckTag;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyRandom;

public class DeckModel extends Observed {
    private ArrayList<CardModel> cards;
    private ArrayList<DeckTag> tags;
    private String name;
    private String description;
    private int activeCard;
    private StatDeck statDeck;
    private BuildCardStrategy buildCardStrategy;
    private DrawCardStrategy drawCardStrategy;

    public DeckModel(ArrayList<CardModel> cards, ArrayList<DeckTag> tags, String name, String description,
            StatDeck statDeck) {
        this.cards = cards;
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.activeCard = 0;
        this.statDeck = statDeck;
        this.buildCardStrategy = new BuildCardStrategyClassic();
        this.drawCardStrategy = new DrawCardStrategyRandom();
    }

    public DeckModel(String name, String description) {
        cards = new ArrayList<>();
        tags = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.activeCard = 0;
        this.statDeck = new StatDeck(name);
        this.buildCardStrategy = new BuildCardStrategyClassic();
        this.drawCardStrategy = new DrawCardStrategyRandom();
    }

    public void addCard(CardModel card) {
        cards.add(card);
        statDeck.addCard(card.getStatCard());
        notifyObservers();
    }

    public void setActiveCard(int i) {
        this.activeCard = i;
        notifyObservers();
    }

    public int getActiveCard() {
        return activeCard;
    }

    public void addCard(String question, String answer) {
        cards.add(new CardModel(question, answer));
        notifyObservers();
    }

    public CardModel getCard(int i) {
        return cards.get(i);
    }

    public void removeCard(int i) {
        cards.remove(i);
        notifyObservers();
    }

    public ArrayList<CardModel> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CardModel> cards) {
        this.cards = cards;
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public ArrayList<DeckTag> getTags() {
        return this.tags;
    }

    public void addTag(String tag) {
        this.tags.add(new DeckTag(tag));
    }

    public void removeTag(String tag) {
        for (DeckTag t : tags) {
            if (t.getName().equals(tag)) {
                tags.remove(t);
                break;
            }
        }
    }

    public StatDeck getStatDeck() {
        return this.statDeck;
    }

    public void setBuildCardStrategy(BuildCardStrategy buildCardStrategy) {
        this.buildCardStrategy = buildCardStrategy;
        notifyObservers();
    }
    public BuildCardStrategy getBuildCardStrategy() {
        return buildCardStrategy;
    }
    public void setDrawCardStrategy(DrawCardStrategy drawCardStrategy) {
        this.drawCardStrategy = drawCardStrategy;
        notifyObservers();
    }
    public DrawCardStrategy getDrawCardStrategy() {
        return drawCardStrategy;
    }
}

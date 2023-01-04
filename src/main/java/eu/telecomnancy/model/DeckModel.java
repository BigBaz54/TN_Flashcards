package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.DeckTag;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyWeighted;

public class DeckModel extends Observed {
    private ArrayList<CardModel> cards;
    private ArrayList<DeckTag> tags;
    private String name;
    private String description;
    private int activeCard;
    private DrawCardStrategy drawCardStrategy;
    private BuildCardStrategy buildCardStrategy;
    private StatDeck statDeck;
    private Mode mode;

    public DeckModel(ArrayList<CardModel> cards, ArrayList<DeckTag> tags, String name, String description) {
        this.cards = cards;
        this.tags = tags;
        drawCardStrategy = new DrawCardStrategyWeighted();
        buildCardStrategy = new BuildCardStrategyClassic();
        this.name = name;
        this.description = description;
        this.activeCard = 0;
        this.statDeck = new StatDeck();
        this.mode = Mode.VIEW;
    }

    public DeckModel(String name, String description, Mode mode) {
        cards = new ArrayList<>();
        tags = new ArrayList<>();
        drawCardStrategy = new DrawCardStrategyWeighted();
        buildCardStrategy = new BuildCardStrategyClassic();
        this.name = name;
        this.description = description;
        this.activeCard = 0;
        this.statDeck = new StatDeck();
        this.mode = mode;
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

    public void setDrawCardStrategy(DrawCardStrategy strategy) {
        this.drawCardStrategy = strategy;
        notifyObservers();
    }

    public void setBuildCardStrategy(BuildCardStrategy strategy) {
        this.buildCardStrategy = strategy;
        notifyObservers();
    }

    public ArrayList<DeckTag> getTags() {
        return this.tags;
    }

    public void setMode(Mode mode){
        this.mode = mode;
    }

    public Mode getMode(){
        return this.mode;
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
}

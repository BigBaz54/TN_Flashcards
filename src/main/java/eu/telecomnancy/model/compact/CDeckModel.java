package eu.telecomnancy.model.compact;

import java.util.ArrayList;

import eu.telecomnancy.DeckTag;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.StatDeck;

public class CDeckModel implements Compact<DeckModel> {
    private ArrayList<CardModel> cards;
    private ArrayList<DeckTag> tags;
    private String name;
    private String description;
    private StatDeck statDeck;

    public CDeckModel() {
        cards = new ArrayList<>();
        tags = new ArrayList<>();
        name = "";
        description = "";
        statDeck = new StatDeck();
    }

    public CDeckModel(ArrayList<CardModel> cards, ArrayList<DeckTag> tags, String name, String description,
            StatDeck statDeck) {
        this.cards = cards;
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.statDeck = statDeck;
    }

    @Override
    public CDeckModel from(DeckModel t) {
        return new CDeckModel(t.getCards(), t.getTags(), t.getName(), t.getDescription(), t.getStatDeck());
    }

    @Override
    public DeckModel to() {
        return new DeckModel(cards, tags, name, description, statDeck);
    }

    public ArrayList<CardModel> getCards() {
        return cards;
    }

    public ArrayList<DeckTag> getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public StatDeck getStatDeck() {
        return statDeck;
    }

    public void setCards(ArrayList<CardModel> cards) {
        this.cards = cards;
    }

    public void setTags(ArrayList<DeckTag> tags) {
        this.tags = tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatDeck(StatDeck statDeck) {
        this.statDeck = statDeck;
    }
}

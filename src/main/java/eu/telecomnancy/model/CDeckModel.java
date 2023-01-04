package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.DeckTag;

public class CDeckModel {
    private ArrayList<CardModel> cards;
    private ArrayList<DeckTag> tags;
    private String name;
    private String description;

    public CDeckModel(ArrayList<CardModel> cards, ArrayList<DeckTag> tags, String name, String description) {
        this.cards = cards;
        this.tags = tags;
        this.name = name;
        this.description = description;
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
}

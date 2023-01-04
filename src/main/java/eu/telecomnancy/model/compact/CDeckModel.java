package eu.telecomnancy.model.compact;

import java.util.ArrayList;

import eu.telecomnancy.DeckTag;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;

public class CDeckModel implements Compact<DeckModel> {
    private ArrayList<CardModel> cards;
    private ArrayList<DeckTag> tags;
    private String name;
    private String description;

    public CDeckModel() {
        cards = new ArrayList<>();
        tags = new ArrayList<>();
        name = "";
        description = "";
    }

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

    @Override
    public DeckModel transform() {
        return new DeckModel(cards, tags, name, description);
    }
}

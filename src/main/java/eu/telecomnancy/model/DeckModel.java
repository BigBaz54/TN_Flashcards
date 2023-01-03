package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.Tag;

public class DeckModel extends Observed {
    private ArrayList<CardModel> cards;
    private ArrayList<Tag> tags;
    private String name;
    private String description;
    private int activeCard;

    public DeckModel(String name, String description) {
        cards = new ArrayList<CardModel>();
        tags = new ArrayList<Tag>();
        this.name = name;
        this.description = description;
    }

    public void addCard(CardModel cardModel) {
        cards.add(cardModel);
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

}

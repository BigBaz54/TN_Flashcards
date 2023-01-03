package eu.telecomnancy.model;

import java.util.ArrayList;
import java.util.HashMap;

import eu.telecomnancy.observer.Observed;

public class Deck extends Observed {
    private HashMap<String, Card> cards;
    private ArrayList<Tag> tags;
    private String name;
    private String description;

    public Deck(String name, String description) {
        cards = new HashMap<String, Card>();
        tags = new ArrayList<Tag>();
        this.name = name;
        this.description = description;
    }

    public void addCard(Card card) {
        cards.put(card.getQuestion(), card);
        notifyObservers();
    }

    public Card getCard(String question) {
        return cards.get(question);
    }

    public void removeCard(String question) {
        cards.remove(question);
        notifyObservers();
    }

    public HashMap<String, Card> getCards() {
        return cards;
    }

    public void setCards(HashMap<String, Card> cards) {
        this.cards = cards;
        notifyObservers();
    }

    public boolean containsCard(String question) {
        return cards.containsKey(question);
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

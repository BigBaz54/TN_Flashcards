package eu.telecomnancy.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Deck {
    private HashMap<String, Card> cards;
    private ArrayList<Tag> tags;

    public Deck() {
        cards = new HashMap<String, Card>();
    }

    public void addCard(Card card) {
        cards.put(card.getQuestion(), card);
    }

    public Card getCard(String question) {
        return cards.get(question);
    }

    public void removeCard(String question) {
        cards.remove(question);
    }

    public HashMap<String, Card> getCards() {
        return cards;
    }

    public void setCards(HashMap<String, Card> cards) {
        this.cards = cards;
    }

    public boolean containsCard(String question) {
        return cards.containsKey(question);
    }

}

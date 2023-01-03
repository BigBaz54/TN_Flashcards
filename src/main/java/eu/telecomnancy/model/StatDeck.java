package eu.telecomnancy.model;

import java.util.ArrayList;
import java.util.Timer;

public class StatDeck {
    private int nbCards;
    private int nbTimesOpened;
    private int nbTimesCorrect;
    private int nbTimesWrong;
    private int nbCardsSeen;
    private Long timesSpent;
    private ArrayList<StatCard> cards;

    public StatDeck() {
        this.nbCards = 0;
        this.nbTimesOpened = 0;
        this.nbTimesCorrect = 0;
        this.nbTimesWrong = 0;
        this.nbCardsSeen = 0;
        this.timesSpent = 0L;
        this.cards = new ArrayList<>();
    }

    public void addCard(StatCard card) {
        this.cards.add(card);
        this.nbCards++;
        this.nbTimesCorrect += card.getNbTimesCorrect();
        this.nbTimesWrong += card.getNbTimesWrong();
        this.nbCardsSeen += card.getNbTimesSeen();
        this.timesSpent +=card.getTimesSpentTotal();
    }

    public void removeCard(StatCard card) {
        this.cards.remove(card);
        this.nbCards--;
        this.nbTimesCorrect -= card.getNbTimesCorrect();
        this.nbTimesWrong -= card.getNbTimesWrong();
        this.nbCardsSeen -= card.getNbTimesSeen();
        this.timesSpent -= card.getTimesSpentTotal();
    }

    public int getNbCards() {
        return nbCards;
    }

    public void setNbCards(int nbCards) {
        this.nbCards = nbCards;
    }

    public int getNbTimesOpened() {
        return nbTimesOpened;
    }

    public void setNbTimesOpened(int nbTimesOpened) {
        this.nbTimesOpened = nbTimesOpened;
    }

    public int getNbTimesCorrect() {
        return nbTimesCorrect;
    }

    public void setNbTimesCorrect(int nbTimesCorrect) {
        this.nbTimesCorrect = nbTimesCorrect;
    }

    public int getNbTimesWrong() {
        return nbTimesWrong;
    }

    public void setNbTimesWrong(int nbTimesWrong) {
        this.nbTimesWrong = nbTimesWrong;
    }

    public int getNbCardsSeen() {
        return nbCardsSeen;
    }

    public void setNbCardsSeen(int nbCardsSeen) {
        this.nbCardsSeen = nbCardsSeen;
    }

    public Long getTimesSpent() {
        return timesSpent;
    }

    public void setTimesSpent(Long timesSpent) {
        this.timesSpent = timesSpent;
    }

    public ArrayList<StatCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<StatCard> cards) {
        this.cards = cards;
    }
}
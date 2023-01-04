package eu.telecomnancy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.time.LocalDate;

public class StatDeck {
    private int nbCards;
    private int nbTimesOpened;
    private int nbTimesCorrect;
    private int nbTimesWrong;
    private int nbCardsSeen;
    private Long timesSpent;
    private String name;
    private ArrayList<StatCard> cards;
    private Date lastOpened;
    private Date creationDate;

    public StatDeck(String name) {
        this.nbCards = 0;
        this.nbTimesOpened = 0;
        this.nbTimesCorrect = 0;
        this.nbTimesWrong = 0;
        this.nbCardsSeen = 0;
        this.timesSpent = 0L;
        this.cards = new ArrayList<>();
        this.lastOpened = new Date();
        this.creationDate = new Date();
        this.name = name;
    }
    public StatDeck() {
        this.nbCards = 0;
        this.nbTimesOpened = 0;
        this.nbTimesCorrect = 0;
        this.nbTimesWrong = 0;
        this.nbCardsSeen = 0;
        this.timesSpent = 0L;
        this.cards = new ArrayList<>();
        this.lastOpened = new Date();
        this.creationDate = new Date();
    }

    public void addCard(StatCard card) {
        this.cards.add(card);
        this.nbCards++;
        this.nbTimesCorrect += card.getNbTimesCorrect();
        this.nbTimesWrong += card.getNbTimesWrong();
        this.nbCardsSeen += card.getNbTimesSeen();
        this.timesSpent += card.getTimesSpentTotal();
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

    public void setLastOpened(Date lastOpened) {
        this.lastOpened = lastOpened;
    }

    public Date getLastOpened() {
        return lastOpened;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}

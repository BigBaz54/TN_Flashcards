package eu.telecomnancy.model;

import java.util.ArrayList;

public class StatLearning {
    private int nbPlayed;
    private int nbCorrect;
    private Long timePlayed;
    private ArrayList<Long> timeCards;

    public StatLearning() {
        this.nbPlayed = 0;
        this.nbCorrect = 0;
        this.timePlayed = 0L;
        this.timeCards = new ArrayList<Long>();
    }

    public int getNbPlayed() {
        return nbPlayed;
    }

    public void incrementNbPlayed() {
        this.nbPlayed++;
    }

    public int getNbCorrect() {
        return nbCorrect;
    }

    public void incrementNbCorrect() {
        this.nbCorrect++;
    }

    public Long getTimePlayed() {
        return timePlayed;
    }

    public void incrementTimePlayed(Long timePlayed) {
        this.timePlayed += timePlayed;
    }

    public ArrayList<Long> getTimeCards() {
        return timeCards;
    }

    public void addTimeCard(Long timeCard) {
        this.timeCards.add(timeCard);
    }
}

package eu.telecomnancy.model;

import java.util.ArrayList;
import java.util.HashMap;

public class StatLearning {
    private int nbPlayed;
    private int nbCorrect;
    private Long timePlayed;
    private ArrayList<Long> timeCards;
    private HashMap nbPlayedByTag;
    private HashMap nbCorrectByTag;

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

    public HashMap getNbPlayedByTag() {
        return nbPlayedByTag;
    }

    public void incrementNbPlayedByTag(String tag) {
        if (nbPlayedByTag.containsKey(tag)) {
            nbPlayedByTag.put(tag, (int) nbPlayedByTag.get(tag) + 1);
        } else {
            nbPlayedByTag.put(tag, 1);
            nbCorrectByTag.put(tag, 0);
        }
    }

    public HashMap getNbCorrectByTag() {
        return nbCorrectByTag;
    }

    public void incrementNbCorrectByTag(String tag) {
        if (nbCorrectByTag.containsKey(tag)) {
            nbCorrectByTag.put(tag, (int) nbCorrectByTag.get(tag) + 1);
        } else {
            nbCorrectByTag.put(tag, 1);
            nbPlayedByTag.put(tag, 1);
        }
    }
}

package eu.telecomnancy.model;

import java.util.ArrayList;
import java.util.HashMap;

public class StatLearning {
    private int nbPlayed;
    private int nbCorrect;
    private Long timePlayed;
    private ArrayList<Long> timeCards;
    private HashMap<String, Integer> nbPlayedByTag;
    private HashMap<String, Integer> nbCorrectByTag;
    private HashMap<String, Long> timePlayedByTag;

    public StatLearning() {
        this.nbPlayed = 0;
        this.nbCorrect = 0;
        this.timePlayed = 0L;
        this.timeCards = new ArrayList<Long>();
        this.nbPlayedByTag = new HashMap<String, Integer>();
        this.nbCorrectByTag = new HashMap<String, Integer>();
        this.timePlayedByTag = new HashMap<String, Long>();
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

    public HashMap<String, Integer> getNbPlayedByTag() {
        return nbPlayedByTag;
    }

    public void incrementNbPlayedByTag(String tag) {
        if (nbPlayedByTag.containsKey(tag)) {
            nbPlayedByTag.put(tag, (int) nbPlayedByTag.get(tag) + 1);
        } else {
            nbPlayedByTag.put(tag, 1);
            nbCorrectByTag.put(tag, 0);
            timePlayedByTag.put(tag, 0L);
        }
    }

    public HashMap<String, Integer> getNbCorrectByTag() {
        return nbCorrectByTag;
    }

    public void incrementNbCorrectByTag(String tag) {
        if (nbCorrectByTag.containsKey(tag)) {
            nbCorrectByTag.put(tag, (int) nbCorrectByTag.get(tag) + 1);
        } else {
            nbCorrectByTag.put(tag, 1);
        }
    }

    public HashMap<String, Long> getTimePlayedByTag() {
        return timePlayedByTag;
    }

    public void incrementTimePlayedByTag(String tag, Long timePlayed) {
        if (timePlayedByTag.containsKey(tag)) {
            timePlayedByTag.put(tag, (Long) timePlayedByTag.get(tag) + timePlayed);
        } else {
            timePlayedByTag.put(tag, timePlayed);
        }
    }
}

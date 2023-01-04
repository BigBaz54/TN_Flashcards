package eu.telecomnancy.model;

import java.util.ArrayList;
import java.util.Timer;

public class StatDeckList {
    private int nbDecks;
    private int nbCards;
    private int nbTimesCorrect;
    private int nbTimesWrong;
    private int nbCardsSeen;
    private ArrayList<Float> pourcentageTimesSpent;
    private ArrayList<String> decksName;
    private ArrayList<StatDeck> decks;

    public ArrayList<String> getDecksName() {
        return decksName;
    }

    public StatDeckList() {
        this.nbDecks = 0;
        this.nbCards = 0;
        this.nbTimesCorrect = 0;
        this.nbTimesWrong = 0;
        this.nbCardsSeen = 0;
        this.pourcentageTimesSpent = new ArrayList<Float>();
        this.decks = new ArrayList<>();
    }

    public void addDeck(StatDeck deck) {
        this.decks.add(deck);
        this.nbDecks++;
        this.nbCards += deck.getNbCards();
        this.nbTimesCorrect += deck.getNbTimesCorrect();
        this.nbTimesWrong += deck.getNbTimesWrong();
        this.nbCardsSeen += deck.getNbCardsSeen();
        setPourcentageTimesSpent(decks);
    }

    public void removeDeck(StatDeck deck) {
        this.decks.remove(deck);
        this.nbDecks--;
        this.nbCards -= deck.getNbCards();
        this.nbTimesCorrect -= deck.getNbTimesCorrect();
        this.nbTimesWrong -= deck.getNbTimesWrong();
        this.nbCardsSeen -= deck.getNbCardsSeen();
        setPourcentageTimesSpent(decks);
    }



    public int getNbDecks() {
        return nbDecks;
    }

    public void setNbDecks(int nbDecks) {
        this.nbDecks = nbDecks;
    }

    public int getNbCards() {
        return nbCards;
    }

    public void setNbCards(int nbCards) {
        this.nbCards = nbCards;
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

    public ArrayList<Float> getPourcentageTimesSpent() {
        return pourcentageTimesSpent;
    }

    public void setPourcentageTimesSpent(ArrayList<StatDeck> decks) {
        ArrayList<Long> timeEachDeck = new ArrayList<>();
        for (StatDeck deck : decks) {
            timeEachDeck.add(deck.getTimesSpent());
        }
        //get the name of the decks
        float totalTime = 0;
        for (Long time : timeEachDeck) {
            totalTime += time;
        }
        for (Long time : timeEachDeck) {
            this.pourcentageTimesSpent.add(time/totalTime);
        }
    }

    public ArrayList<StatDeck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<StatDeck> decks) {
        this.decks = decks;
    }


}

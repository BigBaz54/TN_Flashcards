package eu.telecomnancy.model;

public class StatLearning {
    private int nbPlayed;
    private int nbCorrect;
    private long timePlayed;

    public StatLearning() {
        this.nbPlayed = 0;
        this.nbCorrect = 0;
        this.timePlayed = 0L;
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

    public long getTimePlayed() {
        return timePlayed;
    }

    public void incrementTimePlayed(long timePlayed) {
        this.timePlayed += timePlayed;
    }
}

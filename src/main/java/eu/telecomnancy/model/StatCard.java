package eu.telecomnancy.model;

import java.util.ArrayList;

public class StatCard {
    private int nbTimesSeen;
    private int nbTimesCorrect;
    private int nbTimesWrong;
    private ArrayList<Long> timesSpent;

    /*
     * How to record time
     * Long startTime = System.currentTimeMillis();
     * Do something
     * Long endTime = System.currentTimeMillis();
     * Long duration = (endTime - startTime);
     */

    public StatCard() {
        this.nbTimesSeen = 0;
        this.nbTimesCorrect = 0;
        this.nbTimesWrong = 0;
        this.timesSpent = new ArrayList<>();
    }

    public int getNbTimesSeen() {
        return nbTimesSeen;
    }

    public void setNbTimesSeen(int nbTimesSeen) {
        this.nbTimesSeen = nbTimesSeen;
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

    public ArrayList<Long> getTimesSpent() {
        return timesSpent;
    }

    public Long getTimesSpentLast() {
        return timesSpent.get(timesSpent.size() - 1);
    }

    public Long getTimesSpentTotal() {
        Long total = 0L;
        for (Long time : timesSpent) {
            total += time;
        }
        return total;
    }

    public Long getTimesSpentAverage() {
        Long total = 0L;
        for (Long time : timesSpent) {
            total += time;
        }
        return total / timesSpent.size();
    }

    public void setTimesSpent(ArrayList<Long> times) {
        this.timesSpent = times;
    }

    public void addTimeSpent(Long time) {
        this.timesSpent.add(time);
    }
}

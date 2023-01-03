package eu.telecomnancy.model;

import eu.telecomnancy.observer.*;

public class StageModel extends Observed {
    private int activeScene;

    public void setActiveScene(int activeScene) {
        this.activeScene = activeScene;
        notifyObservers();
    }
}

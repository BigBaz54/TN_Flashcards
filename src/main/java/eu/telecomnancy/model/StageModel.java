package eu.telecomnancy.model;

import javafx.stage.Stage;

public class StageModel extends Observed {
    private int activeScene;

    public StageModel() {
        this.activeScene = 0;

    }

    public void setActiveScene(int activeScene) {
        this.activeScene = activeScene;
        System.out.println("setActiveScene");
        notifyObservers();
    }
    public int getActiveScene() {
        return activeScene;
    }
}

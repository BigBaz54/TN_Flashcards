package eu.telecomnancy.model;

public class StageModel extends Observed {
    private int activeScene;

    public StageModel() {
        this.activeScene = 0;

    }

    public void setActiveScene(int activeScene) {
        this.activeScene = activeScene;
        notifyObservers();
    }

    public int getActiveScene() {
        return activeScene;
    }
}

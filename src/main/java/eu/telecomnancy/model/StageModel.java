package eu.telecomnancy.model;

import eu.telecomnancy.observer.*;
import javafx.stage.Stage;

public class StageModel extends Observed {
    private int activeScene;
    private Stage primaryStage;

    public StageModel(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.activeScene = 0;
    }

    public void setActiveScene(int activeScene) {
        this.activeScene = activeScene;
        notifyObservers();
    }
    public int getActiveScene() {
        return activeScene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}

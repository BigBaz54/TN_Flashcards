package eu.telecomnancy.controller;

import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.view.DeckView;
import eu.telecomnancy.view.GlobalView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageController implements Controller {

    private StageModel stage;

    public StageController(StageModel stage) {
        this.stage = stage;
    }
    public void setActiveScene(int scene) {
        stage.setActiveScene(scene);
    }
    public void setGlobalView() {
        stage.setActiveScene(0);

    }
    public void setDeckView() {
        stage.setActiveScene(1);
    }

}

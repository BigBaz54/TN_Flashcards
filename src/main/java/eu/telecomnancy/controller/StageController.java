package eu.telecomnancy.controller;

import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.view.DeckView;
import eu.telecomnancy.view.GlobalView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageController {

    private StageModel stageModel;

    public StageController(StageModel stageModel) {
        this.stageModel = stageModel;
    }
    public void setActiveScene(int scene) {
        stageModel.setActiveScene(scene);
    }
    public void setGlobalView() {
        stageModel.setActiveScene(0);

    }
    public void setDeckView() {
        stageModel.setActiveScene(1);
    }

}

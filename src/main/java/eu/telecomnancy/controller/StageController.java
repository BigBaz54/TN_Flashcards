package eu.telecomnancy.controller;

import eu.telecomnancy.view.DeckView;
import eu.telecomnancy.view.GlobalView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageController implements Controller {
    private Stage stage;
    public StageController(Stage stage) {
        this.stage = stage;
    }
    public void setActiveScene(Scene scene) {
        stage.setScene(scene);
    }
    public void setGlobalView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GlobalView.fxml"));
        loader.setControllerFactory(ic -> new GlobalView());

    }
    public void setDeckView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DeckView.fxml"));
        loader.setControllerFactory(ic -> new DeckView());
    }

}

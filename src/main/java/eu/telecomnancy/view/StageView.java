package eu.telecomnancy.view;

import java.io.IOException;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.observer.StageObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageView extends StageObserver{
    private Stage primaryStage;
    private Scene globalView;
    private Scene deckView;
    private StageController stageController;

    public StageView(StageModel stageModel, StageController stageController, Scene globalView, Scene deckView) {
        super(stageModel);
        this.stageController = stageController;
        this.globalView = globalView;
        this.deckView = deckView;
    }
    
    public void react() {
        switch(stageModel.getActiveScene()) {
            case 0:
                primaryStage.setScene(globalView);
                break;
            case 1:
                primaryStage.setScene(deckView);
                break;
        }
    }
    
}

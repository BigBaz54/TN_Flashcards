package eu.telecomnancy.view;

import java.io.IOException;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.observer.StageObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageView extends StageObserver{
    private Stage primaryStage;
    private StageController stageController;
    private Scene globalView;
    private Scene deckView;
    private Scene statView;

    public StageView(Stage primaryStage, StageModel stageModel, StageController stageController, Scene globalView, Scene stsView) {
        super(stageModel);
        this.primaryStage = primaryStage;
        this.stageController = stageController;
        this.globalView = globalView;
        this.stageController.setStageView(this);
        this.statView = stsView;
    }
    
    public void react() {
        switch(stageModel.getActiveScene()) {
            case 0:
                primaryStage.setScene(globalView);
                break;
            case 1:
                primaryStage.setScene(deckView);
                break;
            case 2:
                primaryStage.setScene(statView);
                break;

        }
        primaryStage.show();
    }

    public void setDeckView(Scene deckView) {
        this.deckView = deckView;
    }
    
}

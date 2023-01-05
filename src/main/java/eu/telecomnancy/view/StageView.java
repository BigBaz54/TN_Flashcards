package eu.telecomnancy.view;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.observer.StageObserver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageView extends StageObserver {
    private Stage primaryStage;
    private StageController stageController;
    private Scene globalView;
    private Scene deckView;
    private Scene statView;
    private Scene learningView;

    public StageView(Stage primaryStage, StageModel stageModel, StageController stageController, Scene globalView,
            Scene stsView) {
        super(stageModel);
        this.primaryStage = primaryStage;
        this.stageController = stageController;
        this.globalView = globalView;
        this.stageController.setStageView(this);
        this.statView = stsView;
    }

    public void react() {
        switch (stageModel.getActiveScene()) {
            case 0:
                primaryStage.setScene(globalView);
                break;
            case 1:
                primaryStage.setScene(deckView);
                break;
            case 2:
                primaryStage.setScene(statView);
                break;
            case 3:
                primaryStage.setScene(learningView);
                break;

        }
        primaryStage.show();
    }

    public void setDeckView(Scene deckView) {
        this.deckView = deckView;
    }

    public void setLearningView(Scene learningView) {
        this.learningView = learningView;
    }

}

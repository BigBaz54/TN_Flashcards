package eu.telecomnancy.controller;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.view.DeckView;
import eu.telecomnancy.view.StageView;
import eu.telecomnancy.view.LearningView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class StageController {

    private StageModel stageModel;
    private StageView stageView;

    public StageController(StageModel stageModel) {
        this.stageModel = stageModel;
    }
    public void setActiveScene(int scene) {
        stageModel.setActiveScene(scene);
    }
    public void setGlobalView() {
        stageModel.setActiveScene(0);

    }
    public void setDeckView(DeckModel deckModel, BuildCardStrategy buildCardStrategy, DrawCardStrategy drawCardStrategy) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeckView.fxml"));
        loader.setControllerFactory(ic -> new DeckView(deckModel, new DeckController(deckModel),this,buildCardStrategy,drawCardStrategy));
        try {
            Scene scene = new Scene(loader.load());
            stageView.setDeckView(scene);
            stageModel.setActiveScene(1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setStatsView(){
        stageModel.setActiveScene(2);
    }

    public void setStageView(StageView stageView) {
        this.stageView = stageView;
    }

    public void setLearningView(Learning learning,DeckModel deckModel,BuildCardStrategy buildCardStrategy,DrawCardStrategy drawCardStrategy) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LearningView.fxml"));
        loader.setControllerFactory(ic -> new LearningView(learning,deckModel, new DeckController(deckModel),this,buildCardStrategy,drawCardStrategy));
        try {
            Scene scene = new Scene(loader.load());
            stageView.setLearningView(scene);
            stageModel.setActiveScene(3);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

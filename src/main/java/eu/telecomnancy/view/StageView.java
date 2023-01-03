package eu.telecomnancy.view;

import java.io.IOException;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.observer.StageObserver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class StageView extends StageObserver{

    private GlobalView globalView;
    private DeckView deckView;
    private StageController stageController;

    public StageView(StageModel stage, StageController stageController, GlobalView globalView, DeckView deckView) {
        super(stage);
        this.stageController = stageController;
        this.globalView = globalView;
        this.deckView = deckView;
    }

    @Override
    public void react() {
        switch(stageModel.getActiveScene()) {
            case 0:
                
                break;
            case 1:
                
                break;
        }
    }
    
}

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
    private StageController controller;

    public StageView(StageModel stage, StageController controller, GlobalView globalView, DeckView deckView) {
        super(stage);
        this.controller = controller;
        this.globalView = globalView;
        this.deckView = deckView;
    }

    @Override
    public void react() {
        switch(stage.getActiveScene()) {
            case 0:
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GlobalView.fxml"));
                loader.setController(globalView);
                Scene scene = new Scene(loader.load());
                stage.getPrimaryStage().setScene(scene);
                break;
            case 1:
                
                break;
        }
    }
    
}

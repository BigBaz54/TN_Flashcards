package eu.telecomnancy.view;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.observer.StageObserver;

public class StageView extends StageObserver{

    private GlobalView globalView;
    private DeckView deckView;
    private StageController controller;

    public StageView(StageModel stage, StageController controller) {
        super(stage);
        this.controller = controller;
        

    }

    @Override
    public void react() {
        globalView.react();
        deckView.react();
    }
    
}

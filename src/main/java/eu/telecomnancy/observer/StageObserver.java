package eu.telecomnancy.observer;

import eu.telecomnancy.model.StageModel;
import javafx.stage.Stage;

public abstract class StageObserver implements Observer {

    protected StageModel stage;

    public StageObserver(StageModel stage) {
        stage.addObserver(this);
    }


    @Override
    public abstract void react(); 
    
}

package eu.telecomnancy.observer;

import eu.telecomnancy.model.StageModel;

public abstract class StageObserver implements Observer {
    protected StageModel stageModel;

    protected StageObserver(StageModel stageModel) {
        this.stageModel = stageModel;
        stageModel.addObserver(this);
    }
}

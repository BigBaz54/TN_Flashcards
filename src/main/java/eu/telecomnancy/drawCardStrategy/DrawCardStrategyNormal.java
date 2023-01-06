package eu.telecomnancy.drawCardStrategy;

import eu.telecomnancy.model.DeckModel;

public class DrawCardStrategyNormal implements DrawCardStrategy {

    @Override
    public void handleAnswer(boolean answer, DeckModel deckModel) {
        // Nothing to do
    }

    @Override
    public int nextCard(DeckModel deckModel) {
        int index = deckModel.getActiveCard()+1;
        if (index < deckModel.getCards().size()) {
            return index;
        }else {
            return 0;
        }
    }
    
}

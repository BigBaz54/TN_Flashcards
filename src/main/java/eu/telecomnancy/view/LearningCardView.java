package eu.telecomnancy.view;

import java.io.IOException;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.controller.CardController;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class LearningCardView {

    private CardModel card;
    private CardController cardController;
    private DeckController deckController;


    @FXML
    public BorderPane root;
    @FXML
    private Pane recto;
    @FXML
    private Pane verso;
    @FXML
    private Label rectoLabel;
    @FXML
    private Label versoLabel;


    public LearningCardView(int cardIndex,DeckModel deck, DeckController deckController) {
        this.card = deck.getCard(cardIndex);
        this.deckController = deckController;
        this.cardController = new CardController(card);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardTemplate.fxml"));
        loader.setController(this);
        
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
  
        BuildCardStrategyClassic buildCard = new BuildCardStrategyClassic();
        buildCard.verso.setVisible(false);
        root.setCenter(buildCard.root);
        buildCard.rectoLabel.setText(card.getQuestion());
        buildCard.versoLabel.setText(card.getAnswer());

    }

    
    
}

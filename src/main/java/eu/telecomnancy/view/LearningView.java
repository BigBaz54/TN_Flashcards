package eu.telecomnancy.view;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyTheme2;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyRandom;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyTime;
import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LearningView extends DeckObserver implements Initializable {

    private DeckController deckController;
    private StageController stageController;
    private BuildCardStrategy buildCardStrategy;
    private DrawCardStrategy drawCardStrategy;
    private Learning learning;

    @FXML
    private VBox sidebar;
    @FXML
    private BorderPane cardContainer;
    @FXML
    private Button right;
    @FXML
    private Button wrong;

    @FXML
    private Pane recto;
    @FXML
    private Pane verso;
    @FXML
    public Label rectoLabel;
    @FXML
    public Label versoLabel;


    private CardMode mode;
    private Long time;

    public LearningView(Learning learning, DeckModel deckModel, DeckController deckController, StageController stageController, BuildCardStrategy buildCardStrategy, DrawCardStrategy drawCardStrategy) {
        super(deckModel);
        this.deckController = deckController;
        this.mode = CardMode.RECTO;
        this.stageController = stageController;
        this.buildCardStrategy = buildCardStrategy;
        this.drawCardStrategy = drawCardStrategy;
        this.learning=learning;

    }

    @Override
    public void react() {
        if (learning.isFinished()){
            toDeckView();
            return;
        }
        cardContainer.setCenter(null);
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        if(mode == CardMode.RECTO){
            setNodeVisibility(false, right, wrong);
            cardContainer.setCenter(recto);
            rectoLabel.setText(card.getQuestion());
        }
        else{
            setNodeVisibility(true, right, wrong);
            cardContainer.setCenter(verso);
            versoLabel.setText(card.getAnswer());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Build de la CardView
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        Pane pane = buildCardStrategy.build();
        recto = (Pane) pane.lookup("#recto");
        rectoLabel = (Label) pane.lookup("#rectoLabel");
        rectoLabel.setText(card.getQuestion());
        verso = (Pane) pane.lookup("#verso");
        versoLabel = (Label) pane.lookup("#versoLabel");
        cardContainer.setCenter(recto);
        // Buttons
        setNodeVisibility(false, right, wrong);

    }

    // Card //

    @FXML
    public void returnCard() {
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        if (mode == CardMode.RECTO) {
            mode = CardMode.VERSO;
        } else {
            mode = CardMode.RECTO;
        }
        react();
    }

    @FXML
    public void right() {
        mode = CardMode.RECTO;
        learning.nextCard(true);
    }

    @FXML
    public void wrong() {
        mode = CardMode.RECTO;
        learning.nextCard(false);
    }

    

    // Sidebar //

    @FXML
    public void seeMenu() {
        sidebar.setVisible(!sidebar.isVisible());
    }

    @FXML
    public void toGlobalView() {
        stageController.setGlobalView();
    }

    @FXML
    public void toStatsView() {
        stageController.setStatsView();
    }

    @FXML
    public void toSettingsView() {

    }

    // Retour//

    @FXML
    public void toDeckView() {
        stageController.setDeckView(deckModel,buildCardStrategy,drawCardStrategy);
    }

    private void setNodeVisibility(boolean visible, Node... nodes) {
        for (Node node : nodes) {
            node.setVisible(visible);
        }
    }





    
}

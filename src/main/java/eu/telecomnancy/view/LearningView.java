package eu.telecomnancy.view;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyRandom;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyTime;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class LearningView extends DeckObserver implements Initializable {

    private DeckController deckController;
    private StageController stageController;
    private BuildCardStrategy buildCardStrategy;
    private DrawCardStrategy drawCardStrategy;


    @FXML
    private VBox sidebar;
    @FXML
    private BorderPane cardContainer;

    private CardMode mode;

    private long time;
    


    public LearningView(DeckModel deckModel, DeckController deckController, StageController stageController) {
        super(deckModel);
        this.deckController = deckController;
        this.mode = CardMode.RECTO;
        this.stageController = stageController;
        this.buildCardStrategy = new BuildCardStrategyClassic(deckModel.getCard(deckModel.getActiveCard()));
        this.drawCardStrategy = new DrawCardStrategyRandom();
    }


    @Override
    public void react() {
        time=0;
        cardContainer.setCenter(null);
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        buildCardStrategy = new BuildCardStrategyClassic(card);
        if(mode == CardMode.RECTO){
            cardContainer.setCenter(buildCardStrategy.buildRecto());
        }
        else{
            cardContainer.setCenter(buildCardStrategy.buildVerso());
        }
        
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //LearningCardView activeCard = new LearningCardView(deckModel.getActiveCard(),deckModel, deckController);
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        cardContainer.setCenter(buildCardStrategy.buildRecto());
        
    }

    @FXML
    public void returnCard(){
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        if(mode == CardMode.RECTO){
            mode = CardMode.VERSO;
            cardContainer.setCenter(buildCardStrategy.buildVerso());
        }
        else{
            mode = CardMode.RECTO;
            cardContainer.setCenter(buildCardStrategy.buildRecto());
        }
    }

    @FXML
    public void right(){
        mode = CardMode.RECTO;
        deckController.updateStatCard(true, time);
        deckController.nextCard(drawCardStrategy);
    }
    @FXML
    public void wrong(){
        mode = CardMode.RECTO;
        deckController.updateStatCard(false, time);
        deckController.nextCard(drawCardStrategy);
    }


    @FXML
    public void seeMenu() {
        sidebar.setVisible(!sidebar.isVisible());
    }
    @FXML
    public void toGlobalView(){
        stageController.setGlobalView();
    }
    @FXML
    public void toStatsView(){
        stageController.setStatsView();
    }
    @FXML
    public void toSettingsView(){
        
    }
    @FXML
    public void toDeckView(){
        stageController.setDeckView(deckModel);
    }




    
}

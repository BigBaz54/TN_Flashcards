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
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
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
    private ToggleGroup style = new ToggleGroup();
    @FXML
    private RadioMenuItem classic;
    @FXML
    private RadioMenuItem theme2;


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
        // cardView
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        cardContainer.setCenter(buildCardStrategy.buildRecto());
        // Settings
        classic.setToggleGroup(style);
        theme2.setToggleGroup(style);
        
    }

    // Card //

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

    // Menu //



    @FXML
    public void setBuildClassic(){
        buildCardStrategy = new BuildCardStrategyClassic(deckModel.getCard(deckModel.getActiveCard()));
        react();
    }
    @FXML
    public void setBuildTN(){
        System.out.println("Theme 2");
        buildCardStrategy = new BuildCardStrategyTheme2(deckModel.getCard(deckModel.getActiveCard()));
        react();
    }
    @FXML
    public void setDrawRandom(){
        drawCardStrategy = new DrawCardStrategyRandom();
    }
    @FXML
    public void setDrawTime(){
        drawCardStrategy = new DrawCardStrategyTime();
    }





    // Sidebar //

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


    //Retour//

    @FXML
    public void toDeckView(){
        stageController.setDeckView(deckModel);
    }




    
}

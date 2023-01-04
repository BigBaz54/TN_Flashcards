package eu.telecomnancy.view;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class LearningView extends DeckObserver implements Initializable {

    private DeckController deckController;
    private StageController stageController;

    @FXML
    private VBox sidebar;
    @FXML
    private HBox cardContainer;


    public LearningView(DeckModel deckModel, DeckController deckController, StageController stageController) {
        super(deckModel);
        this.deckController = deckController;
        this.stageController = stageController;
    }


    @Override
    public void react() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        LearningCardView activeCard = new LearningCardView(deckModel.getActiveCard(),deckModel, deckController);
        cardContainer.getChildren().add(activeCard.root);
        
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

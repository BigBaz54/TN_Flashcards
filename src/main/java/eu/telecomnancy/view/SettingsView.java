package eu.telecomnancy.view;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategyClassic;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategyTheme2;
import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyNormal;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyRandom;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategyWeighted;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SettingsView extends DeckListObserver implements Initializable{

    private DeckListController deckListController;
    private StageController stageController;

    @FXML   
    private VBox sidebar;

    @FXML
    private ToggleButton classicTheme;
    @FXML
    private ToggleButton theme2;
    @FXML
    private ToggleButton randomDraw;
    @FXML
    private ToggleButton weightDraw;
    @FXML
    private ToggleButton normalDraw;

    @FXML
    private ToggleGroup theme;
    @FXML
    private ToggleGroup draw;

    public SettingsView(DeckListController deckListController, StageController stageController) {
        super(deckListController.getDeckListModel());
        this.deckListController = deckListController;
        this.stageController = stageController;
    }

    @Override
    public void react() {
        
    }


    // Sidebar Menu
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classicTheme.setSelected(true);
        weightDraw.setSelected(true);
        theme.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (new_toggle==null) {
                old_toggle.setSelected(true);
            }
        });
        draw.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (new_toggle==null) {
                old_toggle.setSelected(true);
            }
        });

        
    }

    // Menu /
    
    
    @FXML
    public void setBuildClassic(){
        deckListController.setBuildCardStrategy(new BuildCardStrategyClassic());
    }
    @FXML
    public void setBuildTN(){
        deckListController.setBuildCardStrategy(new BuildCardStrategyTheme2());
    }
    @FXML
    public void setDrawRandom(){
        deckListController.setDrawCardStrategy(new DrawCardStrategyRandom());
    }
    @FXML
    public void setDrawWeight(){
        deckListController.setDrawCardStrategy(new DrawCardStrategyWeighted());
    }
    @FXML
    public void setDrawNormal(){
        deckListController.setDrawCardStrategy(new DrawCardStrategyNormal());
    }
     
}

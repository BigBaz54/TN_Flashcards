package eu.telecomnancy.view;

import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class SettingsView extends DeckListObserver{

    private DeckListController deckListController;
    private StageController stageController;

    @FXML   
    private VBox sidebar;

    @FXML
    private CheckBox classicTheme;
    @FXML
    private CheckBox theme2;
    @FXML
    private CheckBox randomDraw;
    @FXML
    private CheckBox weightDraw;
    @FXML
    private CheckBox timeDraw;
    @FXML
    private CheckBox normalDraw;

    public SettingsView(DeckListController deckListController, StageController stageController) {
        super(deckListController.getDeckListModel());
        this.deckListController = deckListController;
        this.stageController = stageController;
    }

    @Override
    public void react() {
        // TODO Auto-generated method stub
        
    }


    // Sidebar Menu
    @FXML
    public void seeMenu() {
        sidebar.setVisible(!sidebar.isVisible());
    }

    @FXML
    public void toGlobalView() {

    }

    @FXML
    public void toStatsView() {
        stageController.setStatsView();
    }

    @FXML
    public void toSettingsView() {

    }

    // Menu /
    /*
     * 
     * @FXML
     * public void setBuildClassic(){
     * deckListController.setBuildCardStrategy(new BuildCardStrategyClassic());
     * }
     * 
     * @FXML
     * public void setBuildTN() {
     * deckListController.setBuildCardStrategy(new BuildCardStrategyTheme2());
     * }
     * 
     * @FXML
     * public void setDrawRandom() {
     * deckListController.setDrawCardStrategy(new DrawCardStrategyRandom());
     * }
     * 
     * @FXML
     * public void setDrawTime() {
     * deckListController.setDrawCardStrategy(new DrawCardStrategyTime());
     * }
     * 
     */

}

package eu.telecomnancy.view;

import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GlobalView extends DeckListObserver implements Initializable {

    @FXML
    private ListView<DeckModel> deckListView;

    private StageController stageController;
    private DeckListController deckListController;

    @FXML
    private VBox sidebar;
    @FXML
    private HBox hbox;
    @FXML
    private VBox vbox;

    private Mode mode;

    public GlobalView(DeckListModel deckList, DeckListController deckListController, StageController stageController) {
        super(deckList);
        this.deckListController = deckListController;
        this.stageController = stageController;
        this.mode = Mode.VIEW;
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        deckListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        deckListView.setCellFactory(param -> new DeckCell(this, deckListController, stageController, deckListModel.getBuildCardStrategy(), deckListModel.getDrawCardStrategy()));
        deckListView.getItems().addAll(deckListModel.getDecks());
        // vbox.setAlignment(Pos.CENTER);
        deckListView.setMinWidth(1200);

    }

    // Top Menu

    @FXML
    public void createDeck() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
        loader.setControllerFactory(ic -> new PopUpView(deckListModel, deckListController));
        try {
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchMode() {
        if (mode == Mode.EDIT)
            mode = Mode.VIEW;
        else
            mode = Mode.EDIT;
        deckListView.refresh();
    }

    public Mode getMode() {
        return mode;
    }

    @FXML
    public void exportDeck() {
        if (mode == Mode.EXPORT)
            mode = Mode.VIEW;
        else
            mode = Mode.EXPORT;
        deckListView.refresh();
    }

    @FXML
    public void importDeck() {
    }

    @Override
    public void react() {
        deckListView.getItems().clear();
        deckListView.getItems().addAll(deckListModel.getDecks());

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

    public void setNodeVisibility(boolean visible, Node... node) {
        for (Node n : node) {
            n.setVisible(visible);
            n.setManaged(visible);
        }
    }

}

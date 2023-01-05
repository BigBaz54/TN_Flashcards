package eu.telecomnancy.view;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.learning.LearningXCards;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeckView extends DeckObserver implements Initializable {

    @FXML
    private VBox sidebar;
    @FXML
    private VBox sidebar2;
    @FXML
    private Label pageName;

    private DeckController deckController;
    private StageController stageController;
    private Mode mode;
    private BuildCardStrategy buildCardStrategy;
    private DrawCardStrategy drawCardStrategy;

    @FXML
    private GridPane gridpane;
    @FXML
    private ScrollPane scrollpane;

    public DeckView(DeckModel deckModel, DeckController deckController, StageController stageController, BuildCardStrategy buildCardStrategy,DrawCardStrategy drawCardStrategy) {
        super(deckModel);
        this.deckController = deckController;
        this.stageController = stageController;
        this.mode = Mode.VIEW;
        this.buildCardStrategy = buildCardStrategy;
        this.drawCardStrategy = drawCardStrategy;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNodeVisibility(false, sidebar);
        gridpane.toBack();

        gridpane.setMinHeight(700);
        gridpane.setMinWidth(1300);
        // fit to width
        scrollpane.setFitToWidth(true);
        scrollpane.setFitToHeight(true);
        scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
        react();

    }

    public void react() {
        gridpane.getChildren().clear();
        int n = deckModel.getCards().size();
        int row = (int) Math.ceil(n / 3) + 1;
        for (int i = 0; i < row; i++) {
            gridpane.addRow(i);
            gridpane.setVgap(5);
            gridpane.setHgap(5);
            gridpane.gridLinesVisibleProperty().setValue(false);
            for (int j = 0; j < 3; j++) {
                gridpane.addColumn(j);
            }
        }
        for (int i = 0; i < n; i++) {
            CardModel card = deckModel.getCards().get(i);
            System.out.println(card.getQuestion());
            CardView cardView = new CardView(card, deckModel, deckController, mode);
            cardView.root.setPrefSize(400, 170);
            gridpane.add(cardView.root, i % 3, i / 3);
        }

    }

    // Méthodes du Top Menu

    @FXML
    public void addCard() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpCard.fxml"));
        loader.setControllerFactory(ic -> new PopUpCardView(deckModel, deckController));
        try {
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toLearningView() {
        Learning learning = new LearningXCards(deckController, drawCardStrategy, 20);
        stageController.setLearningView(learning,deckModel,buildCardStrategy,drawCardStrategy);
    }

    // Méthodes du sidebar Menu
    @FXML
    public void seeMenu() {
        setNodeVisibility(!sidebar.isVisible(), sidebar);
    }

    @FXML
    public void toGlobalView() {
        stageController.setGlobalView();
    }

    @FXML
    public void toStatsView() {
    }

    @FXML
    public void toSettingsView() {
    }

    @FXML
    public void switchMode() {
        if (mode == Mode.VIEW) {
            mode = Mode.EDIT;
        } else {
            mode = Mode.VIEW;
        }
        react();
    }

    public void setNodeVisibility(boolean visible, Node... nodes) {
        for (Node node : nodes) {
            node.setVisible(visible);
            node.setManaged(visible);
        }
    }

}

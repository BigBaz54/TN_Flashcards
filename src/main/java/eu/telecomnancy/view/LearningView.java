package eu.telecomnancy.view;

import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import eu.telecomnancy.learning.Learning;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.MediaType;
import eu.telecomnancy.observer.DeckObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    public VBox mediaContainer;

    private CardMode mode;
    private Long time;

    public LearningView(Learning learning, DeckModel deckModel, DeckController deckController,
            StageController stageController, BuildCardStrategy buildCardStrategy, DrawCardStrategy drawCardStrategy) {
        super(deckModel);
        this.deckController = deckController;
        this.mode = CardMode.RECTO;
        this.stageController = stageController;
        this.buildCardStrategy = buildCardStrategy;
        this.drawCardStrategy = drawCardStrategy;
        this.learning = learning;

    }

    @Override
    public void react() {
        if (learning.isFinished()) {
            toDeckView();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUpStats.fxml"));
            loader.setControllerFactory(ic -> new PopUpStatsView(learning));
            try {
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return;
        }
        cardContainer.setCenter(null);
        mediaContainer.getChildren().clear();
        CardModel card = deckModel.getCard(deckModel.getActiveCard());
        if (mode == CardMode.RECTO) {
            setNodeVisibility(false, right, wrong);
            cardContainer.setCenter(recto);
            rectoLabel.setText(card.getQuestion());
            if(card.getMedia() != null) {
                if(card.getMedia().getType()==MediaType.IMG){
                    Image img = new Image(card.getMedia().getFile().toURI().toString());
                    ImageView imgView = new ImageView(img);
                    imgView.setPreserveRatio(true);
                    imgView.setFitWidth(180);
                    imgView.setFitHeight(250);
                    mediaContainer.getChildren().add(imgView);
                }
            }
        } else {
            setNodeVisibility(true, right, wrong);
            cardContainer.setCenter(verso);
            versoLabel.setText(card.getAnswer());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Build de la CardView
        Pane pane = buildCardStrategy.build();
        recto = (Pane) pane.lookup("#recto");
        rectoLabel = (Label) pane.lookup("#rectoLabel");
        verso = (Pane) pane.lookup("#verso");
        versoLabel = (Label) pane.lookup("#versoLabel");
        mediaContainer = (VBox) pane.lookup("#mediaContainer");
        react();
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
        stageController.setSettingsView();
    }

    // Retour//

    @FXML
    public void toDeckView() {
        stageController.setDeckView(deckModel, buildCardStrategy, drawCardStrategy);
    }

    private void setNodeVisibility(boolean visible, Node... nodes) {
        for (Node node : nodes) {
            node.setVisible(visible);
        }
    }

}

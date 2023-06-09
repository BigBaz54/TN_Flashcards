package eu.telecomnancy.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.DeckTag;
import eu.telecomnancy.buildCardStrategy.BuildCardStrategy;
import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.drawCardStrategy.DrawCardStrategy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DeckCell extends ListCell<DeckModel> implements Initializable {

    @FXML
    private BorderPane deckCell;
    @FXML
    private VBox descriptionBox;
    @FXML
    private VBox nameBox;
    @FXML
    private Label deckName;
    @FXML
    private Text deckDescription;
    @FXML
    private Button deleteButton;
    @FXML
    private Button exportBtn;
    @FXML
    private Label tags;

    private DeckListController controller;
    private StageController stageController;
    private GlobalView view;

    public DeckCell(GlobalView view, DeckListController controller, StageController stageController) {
        this.stageController = stageController;
        this.controller = controller;
        this.view = view;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeckCell.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(DeckModel item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setContentDisplay(ContentDisplay.TOP);
            // Visibilité du bouton delete
            if (this.view.getMode() == Mode.EDIT) {
                deleteButton.setVisible(true);
            } else {
                deleteButton.setVisible(false);
            }

            if (this.view.getMode() == Mode.EXPORT) {
                exportBtn.setVisible(true);
            } else {
                exportBtn.setVisible(false);
            }

            // Updates des infos
            DeckModel deck = getItem();
            deckName.setText(deck.getName());
            deckDescription.setText(deck.getDescription());
            String tagString = "";
            for (DeckTag tag : deck.getTags()) {
                tagString += tag.getName() + " ";
            }
            tags.setText(tagString);

            setText(null);
            setGraphic(deckCell);
        } else {
            setText(null);
            setGraphic(null);
        }
    }

    public void setNodeVisibility(boolean visible, Node... node) {
        for (Node n : node) {
            n.setVisible(visible);
            n.setManaged(visible);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Cache la description du deck
        setNodeVisibility(false, descriptionBox);
        descriptionBox.setMaxHeight(0);
        deckCell.setPrefHeight(100);
        // La rend visible lorsqu'on passe sur la cellule
        nameBox.hoverProperty().addListener((observable, oldValue, newValue) -> {
            setNodeVisibility(newValue, descriptionBox);
            descriptionBox.setMaxHeight(newValue ? Double.MAX_VALUE : 0);
            deckCell.setPrefHeight(newValue ? 250 : 100);
            nameBox.getStyleClass().remove(newValue ? "cell" : "cell-top");
            nameBox.getStyleClass().add(newValue ? "cell-top" : "cell");
            if (isLast()) {
                descriptionBox.setManaged(true);
                descriptionBox.setMaxHeight(Double.MAX_VALUE);
                deckCell.setPrefHeight(270);
            }

        });
        // Changement de vue lorsqu'on double clique sur la cellule
        deckCell.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                DeckModel deck = getItem();
                BuildCardStrategy buildCardStrategy = controller.getBuildCardStrategy();
                DrawCardStrategy drawCardStrategy = controller.getDrawCardStrategy();
                stageController.setDeckView(deck,buildCardStrategy,drawCardStrategy);
            }
        });

    }

    @FXML
    public void removeDeck() {
        controller.removeDeck(getIndex());
    }

    @FXML
    public void exportDeck() {
        controller.exportDeck(getIndex());
    }

    private boolean isLast() {
        return controller.getDecks().indexOf(getItem()) == controller.getDecks().size() - 1;
    }
    
}

package eu.telecomnancy.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeckCell extends ListCell<DeckModel> implements Initializable{

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

    private StageController stageController;


    public DeckCell(StageController stageController) {
        this.stageController = stageController;
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
            DeckModel deck = getItem();
            deckName.setText(deck.getName());
            deckDescription.setText(deck.getDescription());
            setText(null);
            setGraphic(deckCell);
        }else {
            setText(null);
            setGraphic(null);
        }
    }

    public void setNodeVisibility(boolean visible, Node... node){
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
        deckCell.setPrefHeight(70);
        // La rend visible lorsqu'on passe sur la cellule
        nameBox.hoverProperty().addListener((observable, oldValue, newValue) -> {
            setNodeVisibility(newValue, descriptionBox);
            descriptionBox.setMaxHeight(newValue ? Double.MAX_VALUE : 0);
            deckCell.setPrefHeight(newValue ? 200 : 70);
            nameBox.getStyleClass().remove(newValue ? "cell" : "cell-top");
            nameBox.getStyleClass().add(newValue ? "cell-top" : "cell");

        });
        // Changement de vue lorsqu'on double clique sur la cellule
        deckCell.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                DeckModel deck = getItem();
                stageController.setDeckView(deck);
            }
        });
        
    }

    
}

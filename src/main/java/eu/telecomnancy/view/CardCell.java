package eu.telecomnancy.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.CardModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CardCell extends ListCell<CardModel> implements Initializable{

    private DeckController controller;
    private StageController stageController;

    @FXML
    private VBox cardCell;
    @FXML
    private Label question;
    @FXML
    private Label answer;
    @FXML
    private TextField questionEdit;
    @FXML
    private TextField answerEdit;

    public CardCell(DeckController controller, StageController stageController) {
        this.controller = controller;
        this.stageController = stageController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardCell.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    @Override
    protected void updateItem(CardModel item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(null);
            setGraphic(cardCell);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

}

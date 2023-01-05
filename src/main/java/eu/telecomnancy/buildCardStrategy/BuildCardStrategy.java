package eu.telecomnancy.buildCardStrategy;

import eu.telecomnancy.model.CardModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class BuildCardStrategy {

    protected CardModel card;

    @FXML
    public Pane recto;
    @FXML
    public Pane verso;
    @FXML
    public Label rectoLabel;
    @FXML
    public Label versoLabel;

    public BuildCardStrategy(CardModel card) {
        this.card = card;
    }

    public Pane buildRecto(){
        return recto;
    }
    public Pane buildVerso(){
        return verso;
    }



}

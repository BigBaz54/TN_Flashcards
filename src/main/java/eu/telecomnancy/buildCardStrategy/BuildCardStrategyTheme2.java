package eu.telecomnancy.buildCardStrategy;

import eu.telecomnancy.model.CardModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BuildCardStrategyTheme2 implements BuildCardStrategy {

    @FXML
    public Pane recto;
    @FXML
    public Pane verso;
    @FXML
    public Label rectoLabel;
    @FXML
    public Label versoLabel;
    @FXML
    public VBox mediaContainer;
    
    public Pane buildRecto(CardModel card) {
        
        return recto;
    }


    public Pane buildVerso(CardModel card) {
        
        return verso;
    }


    @Override
    public Pane build() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoTN.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("cardVersoTN.fxml"));
        loader2.setController(this);
     
        try {
            loader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StackPane root = new StackPane();
        root.getChildren().addAll(recto,verso);
        return root;
    }





}
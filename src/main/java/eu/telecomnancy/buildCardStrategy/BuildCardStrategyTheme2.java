package eu.telecomnancy.buildCardStrategy;

import eu.telecomnancy.model.CardModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class BuildCardStrategyTheme2 implements BuildCardStrategy {

    @FXML
    public Pane recto;
    @FXML
    public Pane verso;
    @FXML
    public Label rectoLabel;
    @FXML
    public Label versoLabel;
    
    public Pane buildRecto(CardModel card) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoTN.fxml"));
        loader.setController(this);
        // loader.setRoot(recto);
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recto;
    }


    public Pane buildVerso(CardModel card) {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("cardVersoTN.fxml"));
        loader2.setController(this);
        // loader2.setRoot(verso);
        try {
            loader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }// TODO Auto-generated method stub
        return verso;
    }


    @Override
    public Pane build() {
        // TODO Auto-generated method stub
        return null;
    }





}
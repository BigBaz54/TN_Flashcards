package eu.telecomnancy.buildCardStrategy;

import eu.telecomnancy.model.CardModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BuildCardStrategyClassic implements BuildCardStrategy {

    @FXML
    public Pane recto;
    @FXML
    public Pane verso;
    @FXML
    public Label rectoLabel;
    @FXML
    public Label versoLabel;

    @Override
    public Pane build() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoClassic.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("cardVersoClassic.fxml"));
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
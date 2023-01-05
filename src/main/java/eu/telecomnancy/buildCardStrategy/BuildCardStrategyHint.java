package eu.telecomnancy.buildCardStrategy;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class BuildCardStrategyHint implements BuildCardStrategy{
    
    @FXML
    public Pane recto;
    @FXML
    public Pane verso;
    @FXML
    public Label rectoLabel;
    @FXML
    public Label versoLabel;
    @FXML
    public Label hintLabel;
    

    @Override
    public Pane build() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoHint.fxml"));
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
        return null;
    }


}

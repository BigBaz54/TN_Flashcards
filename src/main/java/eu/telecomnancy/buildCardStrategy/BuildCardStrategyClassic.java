package eu.telecomnancy.buildCardStrategy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BuildCardStrategyClassic {

    public StackPane root = new StackPane();

    @FXML
    public Pane recto;
    @FXML
    public Pane verso;
    @FXML
    public Label rectoLabel;
    @FXML
    public Label versoLabel;

    public BuildCardStrategyClassic() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoClassic.fxml"));
        loader.setController(this);
        //loader.setRoot(recto);
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("cardVersoClassic.fxml"));
        loader2.setController(this);
        //loader2.setRoot(verso);
        try {
            loader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        root.getChildren().add(recto);
        root.getChildren().add(verso);
        
    }
}
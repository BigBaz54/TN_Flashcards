package eu.telecomnancy.buildCardStrategy;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BuildCardStrategyClassic implements BuildCardStrategy {

    private StackPane root;
    private Pane recto;
    private Pane verso;

    public StackPane buildCard() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoClassic.fxml"));
        loader.setController(this);
        loader.setRoot(recto);
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("cardVersoClassic.fxml"));
        loader2.setController(this);
        loader2.setRoot(verso);
        try {
            loader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        root.getChildren().add(recto);
        root.getChildren().add(verso);

        return root;
    }
}
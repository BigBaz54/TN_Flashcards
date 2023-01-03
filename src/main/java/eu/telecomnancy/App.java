package eu.telecomnancy;

import java.io.IOException;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.model.StageModel;
import eu.telecomnancy.view.GlobalView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StageModel stageModel = new StageModel(primaryStage);
        StageController stageController = new StageController(stageModel);

        // GlobalView
        DeckListModel deckList = new DeckListModel();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GlobalView.fxml"));
        loader.setControllerFactory(ic -> new GlobalView(deckList,stageController));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

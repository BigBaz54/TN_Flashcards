package eu.telecomnancy;

import java.io.IOException;
import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.io.file.FileController;
import eu.telecomnancy.model.*;
import eu.telecomnancy.view.GlobalView;
import eu.telecomnancy.view.SettingsView;
import eu.telecomnancy.view.StageView;
import eu.telecomnancy.view.StatsView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    private FileController fileController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StageModel stageModel = new StageModel();
        StageController stageController = new StageController(stageModel);
        // FileController
        fileController = new FileController();
        // GlobalView
        DeckListModel deckList = new DeckListModel();
        DeckListController deckListController = new DeckListController(deckList, fileController);
        // Load decks after the controller is set
        fileController.loadDecks();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GlobalView.fxml"));
        loader.setControllerFactory(ic -> new GlobalView(deckList, deckListController, stageController));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 900);

            // StatDeck
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("StatsView.fxml"));
            loader2.setControllerFactory(ic -> new StatsView(deckList, stageController));
            Parent root2 = loader2.load();
            Scene scene2 = new Scene(root2, 1200, 900);

            // Settings 
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("SettingsView.fxml"));
            loader3.setControllerFactory(ic -> new SettingsView(deckListController,stageController));
            Parent root3 = loader3.load();
            Scene scene3 = new Scene(root3, 1200, 900);

            new StageView(primaryStage, stageModel, stageController, scene, scene2,scene3);
            stageController.setGlobalView();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        fileController.saveDecks();
    }
}

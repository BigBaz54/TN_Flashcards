package eu.telecomnancy.view;

import java.util.ArrayList;

import eu.telecomnancy.DeckTag;
import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopUpView extends DeckListObserver {

    @FXML
    private TextField deckName;
    @FXML
    private TextArea deckDescription;
    @FXML
    private TextField tag1;
    @FXML
    private TextField tag2;
    @FXML
    private TextField tag3;

    private DeckListController deckListController;

    public PopUpView(DeckListModel deckListModel, DeckListController deckListController) {
        super(deckListModel);
        this.deckListController = deckListController;
    }

    @FXML
    public void createDeck(ActionEvent event) {
        String name = deckName.getText();
        String description = deckDescription.getText();
        if(name.isEmpty() || description.isEmpty())
            return;
        if(tag1.getText().isEmpty() && tag2.getText().isEmpty() && tag3.getText().isEmpty())
            deckListController.createDeck(name, description);
        else {
            ArrayList<DeckTag> tags= new ArrayList<>();
            if(!tag1.getText().isEmpty())
                tags.add(new DeckTag(tag1.getText()));
            if(!tag2.getText().isEmpty())
                tags.add(new DeckTag(tag2.getText()));
            if(!tag3.getText().isEmpty())
                tags.add(new DeckTag(tag3.getText()));
            deckListController.createDeck(name, description, tags);
        }
        Stage stage = (Stage) deckName.getScene().getWindow();
        stage.close();
    }

    @Override
    public void react() {

    }

}

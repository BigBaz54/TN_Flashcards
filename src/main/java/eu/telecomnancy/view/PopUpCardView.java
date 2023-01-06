package eu.telecomnancy.view;

import eu.telecomnancy.CardTag;
import eu.telecomnancy.DeckTag;
import eu.telecomnancy.GenerateQuestion;
import eu.telecomnancy.GenerateResponse;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.Media;
import eu.telecomnancy.model.MediaType;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.observer.DeckObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PopUpCardView extends DeckObserver {

    @FXML
    private TextField questionEdit;
    @FXML
    private TextField answerEdit;
    @FXML
    private TextField nbCardEdit;
    @FXML
    private TextField tag1;
    @FXML
    private TextField tag2;
    @FXML
    private TextField tag3;



    private DeckController deckController;

    private Media media = null;

    public PopUpCardView(DeckModel deckModel, DeckController deckController) {
        super(deckModel);
        this.deckController = deckController;
    }

    @FXML
    public void generateAnswer(ActionEvent actionEvent) throws IOException {
        String question = questionEdit.getText();
        GenerateResponse generateResponse = new GenerateResponse();
        String response = generateResponse.getResponse(question);
        answerEdit.setText(response);
    }

    @FXML
    public void generateCard(ActionEvent actionEvent) throws IOException {
        String nbCard = nbCardEdit.getText();
        int nbCardInt = Integer.parseInt(nbCard);
        GenerateQuestion generateQuestion = new GenerateQuestion(deckController);
        ArrayList<CardModel> newCards = generateQuestion.generateQuestion(nbCardInt);
        for (CardModel card : newCards) {
            deckController.addCard(card.getQuestion(), card.getAnswer());
        }
        Stage stage = (Stage) questionEdit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void createCard(ActionEvent event) {
        String question = questionEdit.getText();
        String answer = answerEdit.getText();
        System.out.println(media);
        Stage stage = (Stage) questionEdit.getScene().getWindow();
        stage.close();
        if(question.isEmpty() || answer.isEmpty())
            return;
        if(tag1.getText().isEmpty() && tag2.getText().isEmpty() && tag3.getText().isEmpty())
            deckController.addCard(question, answer,media);
        else {
            ArrayList<CardTag> tags= new ArrayList<>();
            if(!tag1.getText().isEmpty())
                tags.add(new CardTag(tag1.getText()));
            if(!tag2.getText().isEmpty())
                tags.add(new CardTag(tag2.getText()));
            if(!tag3.getText().isEmpty())
                tags.add(new CardTag(tag3.getText()));
            deckController.addCard(question, answer,media,tags);
        }
    }

    public void react() {

    }

    @FXML
    public void addMedia() {
        FileChooser fileChooser = new FileChooser();

        // Extension filters 
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png","*.gif");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("Audio files", "*.mp3", "*.wav","*.aac");
        FileChooser.ExtensionFilter extFilter3 = new FileChooser.ExtensionFilter("Video files", "*.mp4", "*.avi","*.mov");
        fileChooser.getExtensionFilters().addAll(extFilter1, extFilter2, extFilter3);

        File selectedFile = fileChooser.showOpenDialog(null);
        String name = selectedFile.getPath();
        String fileName = selectedFile.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        MediaType type;
        switch (extension) {
            case "png":
            case "jpg":
            case "gif":
                type = MediaType.IMG;
                break;
            case "wav":
            case "mp3":
            case "aac":
                type = MediaType.AUDIO;
                break;
            case "mp4":
            case "avi":
            case "mov":
                type = MediaType.VIDEO;
                break;
            default:
                type = null;
                break;
        }
        media = new Media(name,type);
        


    }

}

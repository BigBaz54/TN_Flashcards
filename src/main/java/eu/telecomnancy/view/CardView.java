package eu.telecomnancy.view;

import java.io.File;
import java.io.IOException;

import eu.telecomnancy.controller.CardController;
import eu.telecomnancy.controller.DeckController;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.Media;
import eu.telecomnancy.model.MediaType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CardView {

    private CardModel card;
    private DeckModel deck;

    @FXML
    public BorderPane root;

    @FXML
    private Label question;
    @FXML
    private Label answer;
    @FXML
    private TextField questionEdit;
    @FXML
    private TextField answerEdit;
    @FXML
    private Button delete;
    @FXML
    private Button mediaIcon;
    @FXML
    private MenuBar mediaEdit;
    @FXML
    private Button addMedia;

    private DeckController deckController;
    private CardController cardController;

    public CardView(CardModel card, DeckModel deck, DeckController deckController, Mode mode) {
        this.card = card;
        this.deck = deck;
        this.deckController = deckController;
        this.cardController = new CardController(card);

        // Chargement du FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardCell.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(root);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        // Affichage des données
        question.setText(cardController.getQuestion());
        answer.setText(cardController.getAnswer());
        questionEdit.setText(cardController.getQuestion());
        answerEdit.setText(cardController.getAnswer());

        // Changement de vue en fonction du mode
        if (mode == Mode.VIEW) {
            setNodeVisibility(false, delete, answerEdit, questionEdit, mediaEdit, addMedia);
            setNodeVisibility(true, answer, question);
        } else {
            if(card.getMedia()!=null){
                setNodeVisibility(true, mediaEdit);
                setNodeVisibility(false, addMedia);
            }else {
                setNodeVisibility(false, mediaEdit);
                setNodeVisibility(true, addMedia);
            }
            setNodeVisibility(false, answer, question,mediaIcon);
            setNodeVisibility(true, delete, answerEdit, questionEdit);
        }

        // Listeners pour modifier la carte
        questionEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            cardController.setQuestion(newValue);
        });
        answerEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            cardController.setAnswer(newValue);
        });

        // Vue d'un média
        if (card.getMedia() != null) {
            mediaIcon.setVisible(true);
        }else{
            mediaIcon.setVisible(false);
        }

    }
    // Enleve une carte du paquet
    @FXML
    public void removeCard() {
        deckController.removeCard(deck.getCards().indexOf(card));
    }

    public void setNodeVisibility(boolean visible, Node... node) {
        for (Node n : node) {
            n.setVisible(visible);
            n.setManaged(visible);
        }
    }

    // Permet de voir un média lorsqu'on clic sur l'icône
    @FXML
    public void seeMedia() {
        Media media = card.getMedia();
        if (media != null) { // Test si le média existe
            if(media.getType()!=MediaType.IMG){ // Si c'est un audio ou vidéo
                javafx.scene.media.Media m = new javafx.scene.media.Media(media.getFile().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(m);
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaPlayer.play();
                Stage stage = new Stage(); // On ouvre une nouvelle fenêtre
                stage.setTitle("Media");
                BorderPane root = new BorderPane();
                root.setCenter(mediaView);
                stage.setScene(new javafx.scene.Scene(root, 640, 480));
                stage.show();
            }else { // Si c'est une image
                Image img = new Image(media.getFile().toURI().toString());
                ImageView view = new ImageView(img);
                Stage stage = new Stage(); // On ouvre une nouvelle fenêtre
                stage.setTitle("Image");
                BorderPane root = new BorderPane();
                root.setCenter(view);
                stage.setScene(new Scene(root));
                stage.show();
                
            }
        }
        
    }


    @FXML
    public void deleteMedia(){
        card.setMedia(null);
        setNodeVisibility(false, mediaEdit);
        setNodeVisibility(true, addMedia);

    }
    @FXML
    public void updateMedia(){
        deleteMedia();
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
        Media media = new Media(name,type);
        card.setMedia(media);
        setNodeVisibility(false, addMedia);
        setNodeVisibility(true, mediaEdit);
    }
}

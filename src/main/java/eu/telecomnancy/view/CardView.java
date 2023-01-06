package eu.telecomnancy.view;

import java.io.IOException;
import java.net.URL;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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

    private Mode mode;
    private DeckController deckController;
    private CardController cardController;

    public CardView(CardModel card, DeckModel deck, DeckController deckController, Mode mode) {
        this.card = card;
        this.deck = deck;
        this.deckController = deckController;
        this.cardController = new CardController(card);
        this.mode = mode;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardCell.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(root);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        question.setText(card.getQuestion());
        answer.setText(card.getAnswer());
        questionEdit.setText(card.getQuestion());
        answerEdit.setText(card.getAnswer());

        // Changement de vue en fonction du mode
        if (mode == Mode.VIEW) {
            setNodeVisibility(false, delete, answerEdit, questionEdit);
            setNodeVisibility(true, answer, question);
        } else {
            setNodeVisibility(false, answer, question);
            setNodeVisibility(true, delete, answerEdit, questionEdit);
        }

        // Listeners
        questionEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            cardController.setQuestion(newValue);
        });
        answerEdit.textProperty().addListener((observable, oldValue, newValue) -> {
            cardController.setAnswer(newValue);
        });

        // Vue d'un média
        if (card.getMedia() != null) {
            mediaIcon.setVisible(true);
        } else {
            mediaIcon.setVisible(false);
        }

    }

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

    @FXML
    public void seeMedia() {
        Media media = card.getMedia();
        if (media != null) {
            if (media.getType() != MediaType.IMG) {
                javafx.scene.media.Media m = new javafx.scene.media.Media(media.getFile().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(m);
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaPlayer.play();
                Stage stage = new Stage();
                stage.setTitle("Media");
                BorderPane root = new BorderPane();
                root.setCenter(mediaView);
                stage.setScene(new javafx.scene.Scene(root, 640, 480));
                stage.show();
            } else {
                Image img = new Image("resources/images/" + media.getName());
                ImageView view = new ImageView(img);
                Stage stage = new Stage();
                stage.setTitle("Image");
                BorderPane root = new BorderPane();
                root.setCenter(view);
                stage.setScene(new Scene(root));
                stage.show();

            }
        }

    }

}

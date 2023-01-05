package eu.telecomnancy.buildCardStrategy;

import eu.telecomnancy.model.CardModel;
import javafx.fxml.FXMLLoader;

public class BuildCardStrategyClassic extends BuildCardStrategy {

    public BuildCardStrategyClassic(CardModel card) {
        super(card);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoClassic.fxml"));
        loader.setController(this);
        // loader.setRoot(recto);
        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("cardVersoClassic.fxml"));
        loader2.setController(this);
        // loader2.setRoot(verso);
        try {
            loader2.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        rectoLabel.setText(card.getQuestion());
        versoLabel.setText(card.getAnswer());

    }

}
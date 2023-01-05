package eu.telecomnancy.buildCardStrategy;

import eu.telecomnancy.model.CardModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class BuildCardStrategyHint extends BuildCardStrategy{
    
    public BuildCardStrategyHint() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardRectoHint.fxml"));
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

    }

    public Pane buildRecto(CardModel card){
        rectoLabel.setText(card.getQuestion());
        versoLabel.setText(card.getAnswer());
        return recto;
    }
    public Pane buildVerso(CardModel card){
        rectoLabel.setText(card.getQuestion());
        versoLabel.setText(card.getAnswer());
        return verso;
    }


}

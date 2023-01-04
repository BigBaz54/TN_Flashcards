package eu.telecomnancy.view;

import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.StatDeck;
import eu.telecomnancy.model.StatDeckList;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StatsView extends DeckListObserver implements Initializable {
    private StatDeckList statDeckList;
    private ArrayList<StatDeck> statDecks;
    @FXML
    private LineChart<Date,Number> nbDecksOverTime;


    public StatsView(DeckListModel deckListModel) {
        super(deckListModel);
        this.statDeckList=deckListModel.getStatDeck();
        this.statDecks=statDeckList.getDecks();
    }

    public void createLineChart1(){
        XYChart.Series<Date, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");

        HashMap map = new HashMap<Date, Integer>();
        //loop on decksStts
        for(StatDeck deck : statDecks  ){
           if( map.containsKey(deck.getCreationDate())){
               int temp = (int) map.get(deck.getCreationDate());
               map.put(deck.getCreationDate(),temp+1);
           }
           else{
               map.put(deck.getCreationDate(),1);
           }
        }
        //loop on map
        for (Object key : map.keySet()) {
            series1.getData().add(new XYChart.Data<>((Date) key,(Integer) map.get(key)));
        }
        nbDecksOverTime.getData().add(series1);
    }

    @Override
    public void react() {
        createLineChart1();
    }

    public void seeMenu(ActionEvent actionEvent) {
    }

    public void importDeck(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        react();
    }
}

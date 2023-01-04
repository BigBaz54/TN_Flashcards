package eu.telecomnancy.view;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.StatDeck;
import eu.telecomnancy.model.StatDeckList;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StatsView extends DeckListObserver implements Initializable {
    private StatDeckList statDeckList;
    private StageController stageController;
    private ArrayList<StatDeck> statDecks;
    @FXML
    private LineChart<String,Number> nbDecksOverTime;
    @FXML
    private PieChart PieChartPourcentage;



    public StatsView(DeckListModel deckListModel, StageController stageController) {
        super(deckListModel);
        this.statDeckList=deckListModel.getStatDeck();
        this.statDecks=statDeckList.getDecks();
        this.stageController=stageController;
    }

    public void createLineChart1(){
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");

        HashMap map = new HashMap<String, Integer>();
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
        ArrayList<String> dates = new ArrayList<String>(map.keySet());
        dates.sort(String::compareTo);
        for(String date : dates){
            series1.getData().add(new XYChart.Data<>(date,(Integer) map.get(date)));
        }
        nbDecksOverTime.getData().add(series1);
    }
    public void createPieChartPourcentage(){
        ArrayList<Float> pourcentages = statDeckList.getPourcentageTimesSpent();
        ArrayList<String> names = statDeckList.getDecksName();
        for (int i = 0; i < pourcentages.size(); i++) {
            PieChartPourcentage.getData().add(new PieChart.Data("Deck "+ names.get(i),pourcentages.get(i)));
        }

    }


    @Override
    public void react() {
        try {
            createLineChart1();
            createPieChartPourcentage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seeMenu(ActionEvent actionEvent) {
    }

    public void importDeck(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        react();
    }

    @FXML
    public void toGlobalView(){
        System.out.println("toGlobalView");
        stageController.setGlobalView();
    }

}

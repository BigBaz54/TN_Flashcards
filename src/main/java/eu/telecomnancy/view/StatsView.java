package eu.telecomnancy.view;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.model.StatDeck;
import eu.telecomnancy.model.StatDeckList;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StatsView extends DeckListObserver implements Initializable {
    private StatDeckList statDeckList;
    private StageController stageController;
    private ArrayList<StatDeck> statDecks;
    @FXML
    private LineChart<String, Number> nbDecksOverTime;
    @FXML
    private PieChart PieChartPourcentage;

    public StatsView(DeckListModel deckListModel, StageController stageController, StatDeckList statDeckList) {
        super(deckListModel);
        this.statDeckList = statDeckList;
        this.stageController = stageController;
    }

    public void createLineChart1() {
        nbDecksOverTime.getData().clear();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        statDecks = statDeckList.getDecks();

        HashMap<String, Integer> map = new HashMap<>();
        // loop on decksStts
        for (StatDeck deck : statDecks) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(deck.getCreationDate());
            if (map.containsKey(date)) {
                int temp = map.get(date);
                map.put(date, temp + 1);
            } else {
                System.out.println(date);
                map.put(date, 1);
            }
        }
        ArrayList<String> dates = new ArrayList<>(map.keySet());
        dates.sort(String::compareTo);
        int previous = 0;
        for (String date : dates) {
            series1.getData().add(new XYChart.Data<>(date, previous + map.get(date)));
            previous += map.get(date);
        }
        nbDecksOverTime.getData().add(series1);
    }

    public void createPieChartPourcentage() {
        PieChartPourcentage.getData().clear();
        ArrayList<Float> pourcentages = statDeckList.getPourcentageTimesSpent();
        ArrayList<String> names = statDeckList.getDecksName();
        System.out.println("--------------------- bienvenue dans le pie chart ---------------------");
        System.out.println("names : " + names);
        System.out.println("pourcentages : " + pourcentages);
        System.out.println(pourcentages.size());
        for (Float pourcentage : pourcentages) {
            // PieChartPourcentage.getData().add(new PieChart.Data("Deck "+
            // names.get(i),pourcentages.get(i)));
            PieChartPourcentage.getData().add(new PieChart.Data("Deck " + "names.get(i)", pourcentage));
        }
        PieChartPourcentage.setLabelsVisible(true);
        
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
    public void toGlobalView() {
        System.out.println("toGlobalView");
        stageController.setGlobalView();
    }

}

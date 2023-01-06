package eu.telecomnancy.view;

import eu.telecomnancy.controller.StageController;
import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckListModel;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.StatDeck;
import eu.telecomnancy.observer.DeckListObserver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class StatsView extends DeckListObserver implements Initializable {
    private StageController stageController;
    @FXML
    private VBox sidebar;
    @FXML
    private LineChart<String, Number> nbDecksOverTime;
    @FXML
    private PieChart PieChartPourcentage;
    @FXML
    private BarChart<String, Number> barChartPourcentage;
    @FXML
    private BubbleChart<Number, Number> bubbleChart;
    @FXML
    private GridPane deckStats;
    @FXML
    private VBox vBox;


    public StatsView(DeckListModel deckListModel, StageController stageController) {
        super(deckListModel);
        this.stageController = stageController;
    }

    public void createLineChart1() {
        nbDecksOverTime.getData().clear();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");

        ArrayList<StatDeck> statDecks = new ArrayList<>();
        for (DeckModel deck : deckListModel.getDecks()) {
            statDecks.add(deck.getStatDeck());
        }

        HashMap<String, Integer> map = new HashMap<>();
        // loop on decksStts
        for (StatDeck deck : statDecks) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(deck.getCreationDate());
            if (map.containsKey(date)) {
                int temp = map.get(date);
                map.put(date, temp + 1);
            } else {
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
        Long totalTime = 0L;
        for (DeckModel deck : deckListModel.getDecks()) {
            for (CardModel card : deck.getCards()) {
                totalTime += card.getStatCard().getTimesSpentTotal();
            }
        }
        Long finalTotalTime = totalTime;
        float total = finalTotalTime.floatValue();
        if (totalTime != 0) {
            deckListModel.getDecks().forEach(deck -> {
                AtomicReference<Long> time = new AtomicReference<>(0L);
                deck.getCards().forEach(card -> {
                    time.set(card.getStatCard().getTimesSpentTotal());
                });
                float pourcentage = (time.get().floatValue() * 100 / total);
                PieChartPourcentage.getData().add(new PieChart.Data(deck.getName(), pourcentage));
            });
        }
        PieChartPourcentage.setLabelsVisible(true);
    }

    public void createBarChart(){
        barChartPourcentage.getData().clear();
        barChartPourcentage.getXAxis().setLabel("Deck");
        barChartPourcentage.getYAxis().setLabel("Pourcentage");
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("all decks");
        ArrayList<StatDeck> statDecks = new ArrayList<>();
        deckListModel.getDecks().forEach(deck -> {
            AtomicReference<Long> nbjuste = new AtomicReference<>(0L);
            deck.getCards().forEach(card -> {
                nbjuste.set(nbjuste.get() + card.getStatCard().getNbTimesCorrect());
            });
            if (deck.getStatDeck().getNbTimesOpened() == 0) {
                dataSeries1.getData().add(new XYChart.Data<>(deck.getName(), 0));
            }else {
                float pourcentage = (float) (nbjuste.get() * 100 / deck.getStatDeck().getNbTimesOpened());
                dataSeries1.getData().add(new XYChart.Data<>(deck.getName(), pourcentage));
            }

        });
        barChartPourcentage.getData().add(dataSeries1);
    }
    public void createBubbleChart(){
        bubbleChart.getData().clear();
        bubbleChart.getXAxis().setLabel("Temps passé");
        bubbleChart.getYAxis().setLabel("Pourcentage bonne réponse");
        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Nombre de carte");
        deckListModel.getDecks().forEach(deck -> {
            AtomicReference<Long> time = new AtomicReference<>(0L);
            AtomicReference<Integer> nbseen = new AtomicReference<>(0);
            AtomicReference<Integer> nbjuste = new AtomicReference<>(0);
            deck.getCards().forEach(card -> {
                nbseen.set(nbseen.get() + card.getStatCard().getNbTimesSeen());
                time.set(time.get() + card.getStatCard().getTimesSpentTotal());
                nbjuste.set(nbjuste.get() + card.getStatCard().getNbTimesCorrect());
            });
            if (nbseen.get() == 0) {
                dataSeries1.getData().add(new XYChart.Data<>(time.get()/1000, 0, nbseen.get()));
            }else {
                float pourcentage = (float) (nbjuste.get() / deck.getStatDeck().getNbTimesOpened());
                dataSeries1.getData().add(new XYChart.Data<>(time.get()/(1000*60), pourcentage, nbseen.get()));
            }
        });

        bubbleChart.getData().add(dataSeries1);
    }
    public void initDeckPane(){
        deckStats.getChildren().clear();
        double heightvbox = 840;
        double widthvbox = 1004;
        //deckStats.setMinSize(width, height);
        deckStats.addColumn(2);
        for (int i =2; i<deckListModel.getDecks().size()*2+2; i+=2){
            deckStats.addRow(i, new Label(deckListModel.getDecks().get((i-2)/2).getName()));
            PieChart pieChart = new PieChart();
            BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
            HashMap<String, Integer> barvrai = new HashMap<>();
            HashMap<String, Integer> barvue = new HashMap<>();
            deckListModel.getDecks().get((i-2)/2).getCards().forEach(card -> {
                int nbcardcorrect=card.getStatCard().getNbTimesCorrect();
                int nbcardseen=card.getStatCard().getNbTimesSeen();
                card.getTags().forEach(tag -> {
                    if (barvue.containsKey(tag.getName())) {
                        int temp = barvue.get(tag.getName());
                        barvue.put(tag.getName(), temp + nbcardseen);
                    } else {
                        barvue.put(tag.getName(), nbcardseen);
                    }
                    if (barvrai.containsKey(tag.getName())) {
                        int temp = barvrai.get(tag.getName());
                        barvrai.put(tag.getName(), temp + nbcardcorrect);
                    } else {
                        barvrai.put(tag.getName(), nbcardcorrect);
                    }
                });
            });
            XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<>();
            dataSeries1.setName("Pourcentage de bonne reponse");
            barvue.forEach((key, value) -> {
                if (barvrai.containsKey(key)) {
                    float pourcentage = (float) (barvrai.get(key) * 100 / value);
                    dataSeries1.getData().add(new XYChart.Data<>(key, pourcentage));
                }
                else {
                    dataSeries1.getData().add(new XYChart.Data<>(key, 0));
                }
            });
            barChart.getData().add(dataSeries1);

            AtomicReference<Long> nbjuste = new AtomicReference<>(0L);
            AtomicReference<Long> nbvue = new AtomicReference<>(0L);
            deckListModel.getDecks().get((i-2)/2).getCards().forEach(card -> {
                nbjuste.updateAndGet(v -> v + card.getStatCard().getNbTimesCorrect());
                nbvue.updateAndGet(v -> v + card.getStatCard().getNbTimesSeen());

            });
            if(nbvue.get() == 0){
                pieChart.getData().add(new PieChart.Data("no Data", 100));
                pieChart.getData().add(new PieChart.Data("Data", 0));
            }else {
                pieChart.getData().add(new PieChart.Data("Correct", nbjuste.get().floatValue()/nbvue.get().floatValue()));
                pieChart.getData().add(new PieChart.Data("Incorrect", 1-nbjuste.get().floatValue()/nbvue.get().floatValue()));
            }
            deckStats.addRow(i+1, pieChart, barChart);

        }
        vBox.setMinSize(widthvbox, heightvbox+deckListModel.getDecks().size()*2*200);

    }
    @Override
    public void react() {
        try {

            createLineChart1();
            createPieChartPourcentage();
            createBarChart();
            createBubbleChart();
            initDeckPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        react();
    }

    @FXML
    public void toGlobalView() {
        stageController.setGlobalView();
    }
    @FXML
    public void seeMenu() {
        sidebar.setVisible(!sidebar.isVisible());
    }

    @FXML
    public void toSettingsView() {
        stageController.setSettingsView();
    }

}

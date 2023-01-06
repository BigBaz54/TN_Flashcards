package eu.telecomnancy.view;

import eu.telecomnancy.learning.Learning;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PopUpStatsView implements Initializable {

    Learning learning;
    @FXML
    private PieChart pieChartPourcentage;
    @FXML
    private BubbleChart<Number, Number> bubbleChartTest;
    @FXML
    private LineChart<Number,Number> lineChartEvolutionTemps;
    @FXML
    private Label nbCardSeen;
    @FXML
    private Label nbardTrue;
    @FXML
    private Label totalTimes;

    public PopUpStatsView(Learning learning) {
        this.learning = learning;

    }

    public void createPieChart() {
        pieChartPourcentage.getData().clear();
        float pourcentageCorrect = (float) learning.getStatLearning().getNbCorrect() / (float) learning.getStatLearning().getNbPlayed();
        float pourcentageIncorrect = ((float) learning.getStatLearning().getNbPlayed()-learning.getStatLearning().getNbCorrect()) / (float) learning.getStatLearning().getNbPlayed();
        pieChartPourcentage.getData().add(new PieChart.Data("Correct", pourcentageCorrect));
        pieChartPourcentage.getData().add(new PieChart.Data("Incorrect", pourcentageIncorrect));
        pieChartPourcentage.setLegendVisible(true);
    }

    public void createLineChart() {
        lineChartEvolutionTemps.getData().clear();
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        lineChartEvolutionTemps.getXAxis().setLabel("Cartes vues");
        lineChartEvolutionTemps.getYAxis().setLabel("Temps en minutes");
        ArrayList<Long> temps = learning.getStatLearning().getTimeCards();
        for (int i = 0; i < temps.size(); i++) {
            XYChart.Data<Number, Number> data = new XYChart.Data<>(i, temps.get(i)/(1000*60));
            series1.getData().add(new XYChart.Data<>(i, temps.get(i)/(1000*60)));
        }
        lineChartEvolutionTemps.getData().add(series1);
    }
    public void createBubbleChart() {
       // bubbleChartTest.getData().clear();
        //bubbleChartTest.getXAxis().setLabel("Label");
        //bubbleChartTest.getYAxis().setLabel("Pourcentage");
        XYChart.Series<String, Number>  series1 = new XYChart.Series<>();
        series1.setName("Temps moyen");

        for (String tag : learning.getStatLearning().getNbCorrectByTag().keySet()){
            float poucentage = ((float) learning.getStatLearning().getNbCorrectByTag().get(tag))/ ((float) learning.getStatLearning().getNbCorrect());
            float tempsmoyen =  ((float) learning.getStatLearning().getTimePlayedByTag().get(tag))/((float)learning.getStatLearning().getNbPlayedByTag().get(tag));
            series1.getData().add(new XYChart.Data<>(tag,poucentage,tempsmoyen));
        }
       // bubbleChartTest.getData().add(series1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPieChart();
        createLineChart();
        //createBubbleChart();
        String carteVue = "Nombre de cartes vues : "+learning.getStatLearning().getNbPlayed();
        nbCardSeen.setText(carteVue);
        String carteTrue = "Nombre de bonnes réponses : "+learning.getStatLearning().getNbCorrect();
        nbardTrue.setText(carteTrue);
        String temps = "Temps total de la session : "+learning.getStatLearning().getTimePlayed()/(1000*60) + " minutes";
        totalTimes.setText(temps);
    }
}

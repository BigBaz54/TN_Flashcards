package eu.telecomnancy.view;

import eu.telecomnancy.learning.Learning;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PopUpStatsView implements Initializable {

    Learning learning;
    @FXML
    private PieChart pieChartPourcentage;
    @FXML
    private BubbleChart<CategoryAxis, NumberAxis> bubbleChartTest;
    @FXML
    private LineChart<NumberAxis,NumberAxis> lineChartEvolutionTemps;
    @FXML
    private Label nbCardSeen;
    @FXML
    private Label nbardTrue;
    @FXML
    private Label totalTimes;

    public PopUpStatsView(Learning learning) {
        this.learning = learning;
        String carteVue = "Nombre de Carte Vues : "+learning.getStatLearning().getNbPlayed();
        this.nbCardSeen.setText(carteVue);
        String carteTrue = "Nombre de bonne réponse : "+learning.getStatLearning().getNbCorrect();
        this.nbardTrue.setText(carteTrue);
        String temps = "Temps total passé : "+learning.getStatLearning().getTimePlayed() + " ms";
        this.totalTimes.setText(temps);
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
        XYChart.Series<NumberAxis, NumberAxis> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        ArrayList<Long> temps = learning.getStatLearning().getTimeCards();
        for (int i = 0; i < temps.size(); i++) {
            XYChart.Data<NumberAxis, NumberAxis> data = new XYChart.Data<>(i, temps.get(i));
            series1.getData().add(new XYChart.Data<>(i, temps.get(i)));
        }
        lineChartEvolutionTemps.getData().add(series1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPieChart();
    }
}

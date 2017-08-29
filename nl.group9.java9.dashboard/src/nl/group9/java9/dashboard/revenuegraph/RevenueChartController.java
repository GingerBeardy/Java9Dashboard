package nl.group9.java9.dashboard.revenuegraph;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;
import static java.lang.String.format;

/**
 * Controller for JavaFX
 */
public class RevenueChartController implements Initializable {

    @FXML
    private NumberAxis yAxis;
    @FXML
    private LineChart<String, Double> revenueOverTime;

    public void setData(RevenueChartData data) {
        revenueOverTime.getData().add(data.getDataSeries());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return format("%.0f", object);
            }

            @Override
            public Number fromString(String string) {
                return valueOf(string);
            }
        });
    }
}

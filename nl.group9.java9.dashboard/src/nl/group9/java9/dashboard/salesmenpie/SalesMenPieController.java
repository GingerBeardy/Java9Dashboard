package nl.group9.java9.dashboard.salesmenpie;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

/**
 * Controller for JavaFX
 */
public class SalesMenPieController {
    @FXML
    private PieChart salesMenPie;

    public void setData(SalesMenPieData data) {
        salesMenPie.setData(data.getPieChartData());
    }
}

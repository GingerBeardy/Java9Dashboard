package nl.group9.java9.dashboard.revenuegraph;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import nl.group9.java9.service.api.domain.Sale;
import nl.group9.java9.dashboard.observable.SalesObserver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * RevenueChartData contains logic to transform the retrieved list of sales to a data for a Graph with total revenue per minute.
 */
public class RevenueChartData implements SalesObserver {
    private final XYChart.Series<String, Double> dataSeries = new XYChart.Series<>();
    private final Map<Integer, Integer> minuteToDataPosition = new HashMap<>();

    public RevenueChartData() {
        // The first point on the graph is initialized to 0
        int previousMinute = LocalDateTime.now().getMinute() - 1;
        this.initialiseBarToZero(previousMinute);
    }

    @Override
    public void onSalesUpdate(List<Sale> sales) {
        int now = now().getMinute();
        if (!minuteToDataPosition.containsKey(now)) {
            this.initialiseBarToZero(now);
        }

        Integer dataIndex = minuteToDataPosition.get(now);

        // possible TODO: convert to a stream?
        Map<Integer, Integer> aggregatedSalesPerMinute = new HashMap<>();
        for (Sale s : sales) {
            int minute = s.getTimeOfSale().getMinute();
            if (!aggregatedSalesPerMinute.containsKey(minute)) {
                aggregatedSalesPerMinute.put(minute, 0);
            }
            double sum = aggregatedSalesPerMinute.get(minute) + s.getRevenue();
            aggregatedSalesPerMinute.put(minute, Math.toIntExact(Math.round(sum)));
        }

        Data<String, Double> barForNow = this.dataSeries.getData().get(dataIndex);
        barForNow.setYValue(Double.valueOf(aggregatedSalesPerMinute.get(now)));
    }

    public XYChart.Series<String, Double> getDataSeries() {
        return dataSeries;
    }

    private void initialiseBarToZero(int minute) {
        this.dataSeries.getData().add(new Data<>(String.valueOf(minute), 0.0));
        this.minuteToDataPosition.put(minute, dataSeries.getData().size() - 1);
    }
}


package nl.group9.java9.dashboard.revenuegraph;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import nl.group9.java9.api.domain.Sale;
import nl.group9.java9.dashboard.observable.SalesObserver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

public class RevenueChartData implements SalesObserver {
    private final XYChart.Series<String, Double> dataSeries = new XYChart.Series<>();
    private final Map<Integer, Integer> minuteToDataPosition = new HashMap<>();

    public RevenueChartData() {
        // get minute value for right now
        int nowMinute = LocalDateTime.now().getMinute();

        // create an empty bar for every minute for the next ten minutes
        this.initialiseBarToZero(nowMinute);
    }

    @Override
    public void onSalesUpdate(List<Sale> sales) {
        int now = now().getMinute();
        if (!minuteToDataPosition.containsKey(now)) {
            initialiseBarToZero(now);
        }

        Integer dataIndex = minuteToDataPosition.get(now);

        Map<Integer, Integer> aggregatedSalesPerMinute = new HashMap<>();
        for (Sale s : sales) {
            int minute = s.getTimeOfSale().getMinute();
            if (!aggregatedSalesPerMinute.containsKey(minute)) {
                aggregatedSalesPerMinute.put(minute, 0);
            }
            double sum = aggregatedSalesPerMinute.get(minute) + s.getRevenue();
            aggregatedSalesPerMinute.put(minute, Math.toIntExact(Math.round(sum)));
        }

        Data<String, Double> barForNow = dataSeries.getData().get(dataIndex);
        barForNow.setYValue(Double.valueOf(aggregatedSalesPerMinute.get(now)));
    }

    public XYChart.Series<String, Double> getDataSeries() {
        return dataSeries;
    }

    private void initialiseBarToZero(int minute) {
        dataSeries.getData().add(new Data<>(String.valueOf(minute), 0.0));
        minuteToDataPosition.put(minute, dataSeries.getData().size() - 1);
    }
}


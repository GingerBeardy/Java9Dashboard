package nl.group9.java9.dashboard.salesmenpie;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import nl.group9.java9.api.domain.Sale;
import nl.group9.java9.dashboard.observer.SalesListener;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;

public class SalesMenPieData implements SalesListener {

    private final PieChart.Data robPortion = new PieChart.Data("Rob", 0);
    private final PieChart.Data basPortion = new PieChart.Data("Bas", 0);
    private final PieChart.Data liesbethPortion = new PieChart.Data("Liesbeth", 0);
    private final ObservableList<PieChart.Data> pieChartData = observableArrayList(robPortion, basPortion, liesbethPortion);

    public ObservableList<PieChart.Data> getPieChartData() {
        return pieChartData;
    }

    @Override
    public void onSalesUpdate(List<Sale> sales) {
        clearPies();

        Map<String, Sale> aggregatedSalesPerSalesMan = sales.stream()
                .collect(Collectors.groupingBy(Sale::getSalesMan,
                        Collectors.collectingAndThen(
                                Collectors.reducing((a, b) -> new Sale(a.getSalesMan(), "", "", a.getRevenue() + b.getRevenue(), null)
                                ), Optional::get)
                        )
                );

        setPies(aggregatedSalesPerSalesMan);
    }

    private void clearPies() {
        this.basPortion.setPieValue(0);
        this.robPortion.setPieValue(0);
        this.liesbethPortion.setPieValue(0);
    }

    private void setPies(Map<String, Sale> aggregatedSalesPerSalesMan) {
        this.basPortion.setPieValue(aggregatedSalesPerSalesMan.get("Bas").getRevenue());
        this.robPortion.setPieValue(aggregatedSalesPerSalesMan.get("Rob").getRevenue());
        this.liesbethPortion.setPieValue(aggregatedSalesPerSalesMan.get("Liesbeth").getRevenue());
    }


}

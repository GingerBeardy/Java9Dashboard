package nl.group9.java9.dashboard.leaderboard;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.group9.java9.api.domain.Sale;
import nl.group9.java9.dashboard.observer.SalesListener;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LeaderboardData implements SalesListener {

    private static final int NUMBER_OF_LEADERS = 20;

    private ObservableList<Sale> consultants;

    public LeaderboardData() {
        consultants = FXCollections.observableArrayList();
    }

    public ObservableList<Sale> getConsultants() {
        return consultants;
    }

    @Override
    public void onSalesUpdate(List<Sale> sales) {
        consultants.clear();

        Map<String, Sale> aggregatedSalesPerConsultant = sales.stream()
                .collect(Collectors.groupingBy(Sale::getConsultant,
                        Collectors.collectingAndThen(
                                Collectors.reducing((a, b) -> new Sale("", a.getConsultant(), "", roundToTwoDecimals(a.getRevenue() + b.getRevenue()), null)
                                ), Optional::get)
                        )
                );

        List<Sale> consultantsOrderedByRevenue = aggregatedSalesPerConsultant.values().stream()
                .sorted(Comparator.comparing(Sale::getRevenue).reversed())
                .filter(distinctByKey(Sale::getConsultant))
                .limit(NUMBER_OF_LEADERS)
                .collect(Collectors.toList());

        Platform.runLater(() -> consultants.setAll(consultantsOrderedByRevenue));
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private static double roundToTwoDecimals(double number) {
        number *= 100;
        number = Math.round(number);
        return number / 100;
    }
}

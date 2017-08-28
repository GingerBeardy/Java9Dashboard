package nl.group9.java9.dashboard.observable;

import nl.group9.java9.api.domain.Sale;

import java.util.List;

public interface SalesObserver {

    void onSalesUpdate(List<Sale> sale);
}

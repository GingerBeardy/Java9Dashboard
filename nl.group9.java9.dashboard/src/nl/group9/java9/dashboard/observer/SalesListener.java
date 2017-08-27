package nl.group9.java9.dashboard.observer;

import nl.group9.java9.api.domain.Sale;

import java.util.List;

public interface SalesListener {

    void onSalesUpdate(List<Sale> sale);
}

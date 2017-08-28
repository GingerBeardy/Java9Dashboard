package nl.group9.java9.dashboard.observable;

import nl.group9.java9.api.domain.Sale;
import nl.group9.java9.service.client.SalesClient;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class RunnableSalesObservable implements Runnable {

    private List<Sale> sales;
    private List<SalesObserver> salesObservers;
    private SalesClient salesClient;

    public RunnableSalesObservable() throws URISyntaxException {
        this.salesObservers = new LinkedList<>();
        this.salesClient = new SalesClient();
    }

    public List<Sale> getSales() {
        return sales;
    }

    @Override
    public void run() {
        this.sales = this.salesClient.getSales();
        if (sales.size() > 1) {
            this.salesObservers.forEach(salesObserver -> salesObserver.onSalesUpdate(sales));
        }
    }

    public void registerSalesListener(SalesObserver salesObserver) {
        this.salesObservers.add(salesObserver);
    }
}

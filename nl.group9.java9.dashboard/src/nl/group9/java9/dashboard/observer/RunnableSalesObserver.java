package nl.group9.java9.dashboard.observer;

import nl.group9.java9.api.domain.Sale;
import nl.group9.java9.service.client.SalesClient;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class RunnableSalesObserver implements Runnable {

    private List<Sale> sales;
    private List<SalesListener> salesListeners;
    private SalesClient salesClient;

    public RunnableSalesObserver() throws URISyntaxException {
        this.salesListeners = new LinkedList<>();
        this.salesClient = new SalesClient();
    }

    public List<Sale> getSales() {
        return sales;
    }

    @Override
    public void run() {
        this.sales = this.salesClient.getSales();
        if (sales.size() > 1) {
            this.salesListeners.forEach(salesListener -> salesListener.onSalesUpdate(sales));
        }
    }

    public void registerSalesListener(SalesListener salesListener) {
        this.salesListeners.add(salesListener);
    }
}

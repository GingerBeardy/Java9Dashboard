package nl.group9.java9.revenuegenerator.sales;

import nl.group9.java9.service.api.domain.Sale;
import nl.group9.java9.service.client.SalesClient;

import java.net.URISyntaxException;

public class SalesRunnable implements Runnable {

    private SalesClient salesClient;
    private SalesGenerator salesGenerator;

    public SalesRunnable() throws URISyntaxException {
        this.salesClient = new SalesClient();
        this.salesGenerator = new SalesGenerator();
    }

    @Override
    public void run() {
        Sale sale = this.salesGenerator.generateSale();
        this.salesClient.postSale(sale);
    }
}

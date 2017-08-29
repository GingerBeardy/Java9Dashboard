package nl.group9.java9.service.client;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import nl.group9.java9.service.api.Config;
import nl.group9.java9.service.api.Paths;
import nl.group9.java9.service.api.domain.Sale;
import nl.group9.java9.service.util.InputStreamMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesClient {

    private static final Logger logger = Logger.getGlobal();

    private HttpClient client;
    private URI salesEndpointUri;

    public SalesClient() throws URISyntaxException {
        this.client = HttpClient.newHttpClient();
        this.salesEndpointUri = new URI("http://localhost:" + Config.PORT_NUMBER + Paths.SALE_PATH);
    }

    public void postSale(Sale sale) {
        try {
            logger.log(Level.INFO, "POST sale: " + sale.toString());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(this.salesEndpointUri)
                    .POST(HttpRequest.BodyProcessor.fromString(sale.toString()))
                    .build();
            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandler.discard(null));
            logger.log(Level.INFO, "Result POST sale: " + response.statusCode());
        } catch (Exception e) {
            logger.log(Level.INFO, "Couldn't post sales: " + e.getLocalizedMessage());
        }
    }

    public List<Sale> getSales() {
        List<Sale> sales = null;
        try {
            logger.log(Level.INFO, "GET sales ");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(this.salesEndpointUri)
                    .GET()
                    .build();
            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandler.asString());
            sales = InputStreamMapper.retrieveSalesListFromString(response.body());
            logger.log(Level.INFO, "Result GET sales: " + response.statusCode() + " - " + sales);
            return sales;
        } catch (Exception e) {
            logger.log(Level.INFO, "Couldn't retrieve sales: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return sales;
    }

}

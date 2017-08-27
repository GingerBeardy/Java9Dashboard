package nl.group9.java9.service.handlers;

import com.sun.net.httpserver.HttpExchange;
import nl.group9.java9.api.Paths;
import nl.group9.java9.api.domain.Sale;
import nl.group9.java9.service.datastorage.DataStore;
import nl.group9.java9.util.InputStreamMapper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleHandler extends AbstractHandler {

    private static final String SAVED_SALE_MSG = "POST: Successfully saved sale: ";
    private static final String RETRIEVED_SALES_MSG = "GET: retrieved sales";

    private Logger logger = Logger.getLogger(SaleHandler.class.getName());

    public SaleHandler(DataStore dataStore) {
        super(Paths.SALE_PATH, dataStore);
    }

    void onGet(HttpExchange exchange) throws IOException {
        List<Sale> sales = dataStore.getSales();
        sendResponse(exchange, STATUS_OK, sales.toString());
        logger.log(Level.INFO, RETRIEVED_SALES_MSG);
    }

    void onPost(HttpExchange exchange) throws IOException {
        Sale sale = InputStreamMapper.retrieveSaleFromInputStream(exchange.getRequestBody());

        dataStore.saveSale(sale);
        logger.log(Level.INFO, SAVED_SALE_MSG + sale.toString());

        sendResponse(exchange, STATUS_CREATED, "");
    }
}
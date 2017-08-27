package nl.group9.java9.service.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import nl.group9.java9.service.datastorage.DataStore;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class AbstractHandler implements HttpHandler {
    static final int STATUS_OK = 200;
    static final int STATUS_CREATED = 201;

    private static final String INCOMING_REQUEST_MSG = "Incoming request at: ";
    private static final String PROCESSED_REQUEST_MSG = "Processed request for: ";
    private static final String NON_PROCESSABLE_METHOD_MSG = "Received a non processable method: ";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String path;

    DataStore dataStore;

    AbstractHandler(String path, DataStore dataStore) {
        this.path = path;
        this.dataStore = dataStore;
    }

    public void handle(HttpExchange exchange) throws IOException {
        this.logger.log(Level.INFO, INCOMING_REQUEST_MSG + this.path);

        String requestMethod = exchange.getRequestMethod();
        switch (requestMethod) {
            case "GET":
                this.onGet(exchange);
                break;
            case "POST":
                this.onPost(exchange);
                break;
            default:
                this.logger.log(Level.INFO, NON_PROCESSABLE_METHOD_MSG + requestMethod);
                break;
        }

        this.logger.log(Level.INFO, PROCESSED_REQUEST_MSG + this.path);
    }

    void sendResponse(HttpExchange exchange, int statusCode, String msg) throws IOException {
        exchange.sendResponseHeaders(statusCode, msg.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(msg.getBytes());
        os.close();
    }

    abstract void onGet(HttpExchange httpExchange) throws IOException;

    abstract void onPost(HttpExchange httpExchange) throws IOException;


}

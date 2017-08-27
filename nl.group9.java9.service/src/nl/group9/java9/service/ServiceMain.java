package nl.group9.java9.service;

import com.sun.net.httpserver.HttpServer;
import nl.group9.java9.api.Config;
import nl.group9.java9.api.Paths;
import nl.group9.java9.service.datastorage.DataStore;
import nl.group9.java9.service.handlers.SaleHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ServiceMain {

    public static void main(String[] args) throws IOException {
        DataStore dataStore = new DataStore();

        HttpServer server = HttpServer.create(new InetSocketAddress(Config.PORT_NUMBER), 0);
        server.createContext(Paths.SALE_PATH, new SaleHandler(dataStore));
        server.start();
    }

}

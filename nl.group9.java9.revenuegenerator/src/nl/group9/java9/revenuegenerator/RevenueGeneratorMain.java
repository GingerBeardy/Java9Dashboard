package nl.group9.java9.revenuegenerator;

import nl.group9.java9.revenuegenerator.sales.SalesRunnable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The Revenue Generator Module will randomly generate sales at fixed interval and post them to the Service.
 */
public class RevenueGeneratorMain {

    public static void main(String[] args) throws Exception {

        SalesRunnable salesRunnable = new SalesRunnable();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(salesRunnable, 0, 500, TimeUnit.MILLISECONDS);
    }
}

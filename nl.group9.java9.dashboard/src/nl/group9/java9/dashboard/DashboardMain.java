package nl.group9.java9.dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.group9.java9.dashboard.leaderboard.LeaderboardData;
import nl.group9.java9.dashboard.observable.RunnableSalesObservable;
import nl.group9.java9.dashboard.revenuegraph.RevenueChartData;
import nl.group9.java9.dashboard.salesmenpie.SalesMenPieData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The Dashboard Module retrieves the total list of Sales from the Service, calculates some statistics, and displays them.
 */
public class DashboardMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize the observable and observers
        RunnableSalesObservable observable = new RunnableSalesObservable();
        LeaderboardData leaderboardData = new LeaderboardData();
        SalesMenPieData salesMenPieData = new SalesMenPieData();
        RevenueChartData revenueChartData = new RevenueChartData();
        observable.registerSalesListener(leaderboardData);
        observable.registerSalesListener(salesMenPieData);
        observable.registerSalesListener(revenueChartData);

        // Let the observable retrieve data at fixed interval
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(observable, 0, 1000, TimeUnit.MILLISECONDS);

        // Initialise the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        primaryStage.setTitle("GROUP9 Dashboard");
        Scene scene = new Scene(loader.load(), 1024, 1024);
        scene.getStylesheets().add("dashboard.css");

        // Wire up the models to the controllers
        DashboardController dashboardController = loader.getController();
        dashboardController.getLeaderboardController().setData(leaderboardData);
        dashboardController.getSalesMenPieController().setData(salesMenPieData);
        dashboardController.getRevenueChartController().setData(revenueChartData);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

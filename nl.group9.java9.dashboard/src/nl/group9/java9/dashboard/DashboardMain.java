package nl.group9.java9.dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.group9.java9.dashboard.leaderboard.LeaderboardData;
import nl.group9.java9.dashboard.observer.RunnableSalesObserver;
import nl.group9.java9.dashboard.salesmenpie.SalesMenPieData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DashboardMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        RunnableSalesObserver observer = new RunnableSalesObserver();
        LeaderboardData leaderboardData = new LeaderboardData();
        SalesMenPieData salesMenPieData = new SalesMenPieData();
        observer.registerSalesListener(leaderboardData);
        observer.registerSalesListener(salesMenPieData);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(observer, 0, 1000, TimeUnit.MILLISECONDS);

        // initialise the UI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        primaryStage.setTitle("GROUP9 Dashboard");
        Scene scene = new Scene(loader.load(), 1024, 1024);
        scene.getStylesheets().add("dashboard.css");

        // wire up the models to the controllers
        DashboardController dashboardController = loader.getController();
        dashboardController.getLeaderboardController().setData(leaderboardData);
        dashboardController.getSalesMenPieController().setData(salesMenPieData);

        // let's go!
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

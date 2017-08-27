package nl.group9.java9.dashboard;

import javafx.fxml.FXML;
import nl.group9.java9.dashboard.leaderboard.LeaderboardController;
import nl.group9.java9.dashboard.salesmenpie.SalesMenPieController;

public class DashboardController {

    @FXML
    private LeaderboardController leaderboardController;
    @FXML
    private SalesMenPieController salesMenPieController;

    public LeaderboardController getLeaderboardController() {
        return leaderboardController;
    }

    public SalesMenPieController getSalesMenPieController() {
        return salesMenPieController;
    }
}

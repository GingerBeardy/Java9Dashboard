package nl.group9.java9.dashboard.leaderboard;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import nl.group9.java9.service.api.domain.Sale;

/**
 * Controller for JavaFX
 */
public class LeaderboardController {
    @FXML
    private TableView<Sale> topConsultants;

    public void setData(LeaderboardData data) {
        topConsultants.setItems(data.getConsultants());
    }
}

module nl.group9.java9.dashboard {
    requires jdk.incubator.httpclient;
    requires java.logging;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    requires nl.group9.java9.service;

    opens nl.group9.java9.dashboard;
    opens nl.group9.java9.dashboard.leaderboard;
    opens nl.group9.java9.dashboard.salesmenpie;
    opens nl.group9.java9.dashboard.revenuegraph;
}

module nl.group9.java9.dashboard {
    requires jdk.incubator.httpclient;
    requires nl.group9.java9.api;
    requires nl.group9.java9.util;
    requires nl.group9.java9.service;
    requires java.logging;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports nl.group9.java9.dashboard;
    opens nl.group9.java9.dashboard;
    exports nl.group9.java9.dashboard.leaderboard;
    opens nl.group9.java9.dashboard.leaderboard;
    exports nl.group9.java9.dashboard.salesmenpie;
    opens nl.group9.java9.dashboard.salesmenpie;
    exports nl.group9.java9.dashboard.revenuegraph;
    opens nl.group9.java9.dashboard.revenuegraph;
}
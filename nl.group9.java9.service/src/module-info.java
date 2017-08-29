module nl.group9.java9.service {
    requires jdk.httpserver;
    requires java.logging;
    requires jdk.incubator.httpclient;

    exports nl.group9.java9.service.client;
    exports nl.group9.java9.service.api.domain;
}
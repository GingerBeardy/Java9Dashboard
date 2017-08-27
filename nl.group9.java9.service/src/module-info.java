module nl.group9.java9.service {
    requires jdk.httpserver;
    requires java.logging;
    requires nl.group9.java9.api;
    requires nl.group9.java9.util;
    requires jdk.incubator.httpclient;


    exports nl.group9.java9.service;
    exports nl.group9.java9.service.client;
}
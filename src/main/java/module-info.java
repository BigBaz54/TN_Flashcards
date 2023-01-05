module eu.telecomnancy {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive com.google.gson;
    requires freetts;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires json;

    opens eu.telecomnancy to javafx.controls, javafx.fxml, com.google.gson;
    opens eu.telecomnancy.view to javafx.fxml;
    opens eu.telecomnancy.io to com.google.gson;
    opens eu.telecomnancy.buildCardStrategy to javafx.fxml;

    exports eu.telecomnancy;
    exports eu.telecomnancy.view;
    exports eu.telecomnancy.io;
    exports eu.telecomnancy.model;
    exports eu.telecomnancy.controller;
    exports eu.telecomnancy.buildCardStrategy;
    exports eu.telecomnancy.io.json;
}

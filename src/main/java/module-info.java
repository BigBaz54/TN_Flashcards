module eu.telecomnancy {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive com.google.gson;
    requires freetts;

    opens eu.telecomnancy to javafx.controls, javafx.fxml, com.google.gson;
    opens eu.telecomnancy.view to javafx.fxml;
    opens eu.telecomnancy.io to com.google.gson;

    exports eu.telecomnancy;
    exports eu.telecomnancy.view;
    exports eu.telecomnancy.io;
    exports eu.telecomnancy.model;
}

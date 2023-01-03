module eu.telecomnancy {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;

    opens eu.telecomnancy to javafx.controls, javafx.fxml;
    opens eu.telecomnancy.view to javafx.fxml;

    exports eu.telecomnancy;
    exports eu.telecomnancy.view;
}

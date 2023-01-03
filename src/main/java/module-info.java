module eu.telecomnancy {
    requires javafx.controls;
    requires javafx.fxml;

    opens eu.telecomnancy to javafx.controls, javafx.fxml;

    exports eu.telecomnancy;
}

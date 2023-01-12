module com.example.appv1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.appv1 to javafx.fxml;
    exports com.example.appv1;
}
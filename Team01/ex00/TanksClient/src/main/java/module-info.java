module edu.school.tanks {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens edu.school.tanks to javafx.fxml;
    exports edu.school.tanks;
    exports edu.school.tanks.models;
    opens edu.school.tanks.models to javafx.fxml;
}
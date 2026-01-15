module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens com.example to javafx.fxml;
    exports com.example;

    opens com.example.presentation to javafx.fxml;
    exports com.example.presentation;
}

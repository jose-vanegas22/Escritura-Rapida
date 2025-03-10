module com.example.escriturarapida3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.escriturarapida3 to javafx.fxml;
    exports com.example.escriturarapida3;
    exports com.example.escriturarapida3.controlador;
    exports com.example.escriturarapida3.modelo;
    opens com.example.escriturarapida3.controlador to javafx.fxml;
}
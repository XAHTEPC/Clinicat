module com.example.clinicat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.clinicat to javafx.fxml;
    exports com.example.clinicat;
}
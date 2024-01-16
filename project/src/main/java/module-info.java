module com.example.flight_system_ticket {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires com.google.gson;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.flight_system_ticket to javafx.fxml;
    exports com.example.flight_system_ticket;
}
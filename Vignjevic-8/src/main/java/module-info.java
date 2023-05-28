module com.example.vignjevic7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.java.vjezbe.controller to javafx.fxml;
    opens hr.java.vjezbe.entitet to javafx.base;
    exports hr.java.vjezbe.controller;
}
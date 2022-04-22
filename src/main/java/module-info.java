module pmf.projekat {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.mail;
    requires java.activation;

    opens pmf.projekat.controller to javafx.controls, javafx.graphics, javafx.fxml, java.activation, java.mail;
    opens pmf.projekat.database to java.sql;

    exports pmf.projekat;
}
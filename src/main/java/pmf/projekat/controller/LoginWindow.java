//
//	Glavni tred prozora
//
package pmf.projekat.controller;

import java.io.IOException;

import pmf.projekat.database.BazaPodataka;
import pmf.projekat.database.UcitavanjeBaze;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindow extends Application {
    private static Stage loginStage;

    public static Stage getStage() {
        return loginStage;
    }

    public static void promjeniScenu(String fxmlPutanja, String naslov, int sirina, int visina) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginWindow.class.getResource(fxmlPutanja));
            Scene scene = new Scene(fxmlLoader.load(), sirina, visina);
            getStage().setResizable(false);
            getStage().setTitle(naslov);
            getStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        loginStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        loginStage.setTitle("Prijava");
        loginStage.setScene(scene);
        loginStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("Prekid veze sa bazom podataka!");
            BazaPodataka db = UcitavanjeBaze.db;
            db.prekidVeze();
        });
    }

}

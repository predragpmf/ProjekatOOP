//
//	Glavni tred prozora
//
package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindow extends Application{
	private static Stage loginStage;
	
    public static Stage getStage() {
        return loginStage;
    }
	
	public void start(Stage primaryStage) throws Exception {
		loginStage = primaryStage;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login/Login.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 600, 400);
		loginStage.setResizable(false);
		loginStage.setTitle("Prijava");
		loginStage.setScene(scene);
		loginStage.show();
	}
	
	public static void main(String[] args) {
		login.LoginKontroler.args = args;
		launch(args);
	}
	
}

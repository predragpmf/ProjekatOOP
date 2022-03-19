package login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginKontroler implements Initializable {
	
	@FXML
    private TextField korisnikUnos;
    @FXML
    private PasswordField lozinkaUnos;
    @FXML
    private Button prijavaTipka;
    @FXML
    private MenuItem exitMeni;
    @FXML
    private MenuItem aboutMeni;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exitMeni.setOnAction(event -> {
			System.exit(0);
		});
		aboutMeni.setOnAction(event -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("OOP projekat");
			alert.setContentText("v1.0");
			alert.showAndWait();
		});
		prijavaTipka.setOnAction(event -> {
            String korisnickoIme = prijavaTipka.getText();
            String lozinka = prijavaTipka.getText();
            // TODO: Cita sviProfesori i sviUcenici i poziva odgovarajucu metodu klase Profesor ili Ucenik.
            // Mozda i validiraj unos.
            
		});
	}
	
}

//
//	Kontroler login prozora
//
package login;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import application.PristupniPodaci;
import application.Profesor;
import application.Ucenik;
import application.Korisnik;

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
            String korisnickoIme = korisnikUnos.getText().strip();
            String lozinka = lozinkaUnos.getText().strip();
            for(PristupniPodaci pp : PristupniPodaci.sviPristupniPodaci) {
            	if(pp.getKorisnickoIme().equals(korisnickoIme)) {
            		if(pp.getLozinka().equals(hesirajLozinku(lozinka))) {
            			System.out.println("Postoji korisnik!");
                		for(Korisnik k : Korisnik.sviKorisnici) {
                			if(pp.getId() == k.getPristupniPodaciId()) {
                				if(k instanceof Ucenik) {
                					Korisnik.prijavljeniKorisnik = k;
                					System.out.println("Korisnik je ucenik!");
                					LoginWindow.promjeniScenu("/login/Ucenik.fxml", "Ucenik", 800, 600);
                					return;
                				} else {
                					Korisnik.prijavljeniKorisnik = k;
                					System.out.println("Korisnik je profesor!");
                					LoginWindow.promjeniScenu("/login/Profesor.fxml", "Profesor", 800, 600);
                					return;
    							}
                			}
                		}
            		}
            		obavjestenjeProzor("Pogrešna loznika!");
            		return;
				}
            	
            }
            obavjestenjeProzor("Korisnik ne postoji!");
		});
	}
	
	private String hesirajLozinku(String lozinka) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(lozinka.getBytes());
	        BigInteger no = new BigInteger(1, messageDigest);
	        String hashtext = no.toString(16);
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }
	        return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void obavjestenjeProzor(String poruka) {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Greška!");
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        korisnikUnos.clear();
        lozinkaUnos.clear();
        alert.showAndWait();
	}
	
}

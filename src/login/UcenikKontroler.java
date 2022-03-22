//
//	Kontroler za prozor ucenika.
//
package login;

import java.net.URL;
import java.util.ResourceBundle;

import application.Korisnik;
import application.Ucenik;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class UcenikKontroler implements Initializable {
	@FXML
    private TextField imePolje;
	@FXML
    private TextField prezimePolje;
	@FXML
    private TextField polPolje;
	@FXML
    private TextField skolaPolje;
	@FXML
	private TextField razredPolje;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imePolje.setText(Korisnik.prijavljeniKorisnik.getIme());
		prezimePolje.setText(Korisnik.prijavljeniKorisnik.getPrezime());
		polPolje.setText(Korisnik.prijavljeniKorisnik.getPol());
		skolaPolje.setText(Korisnik.prijavljeniKorisnik.getSkola());
		razredPolje.setText(Korisnik.prijavljeniKorisnik.getRazred());
	}
	
}

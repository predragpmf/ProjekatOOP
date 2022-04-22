//
//	Kontroler login prozora
//
package pmf.projekat.controller;

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
import pmf.projekat.application.PristupniPodaci;
import pmf.projekat.application.Ucenik;
import pmf.projekat.application.Korisnik;

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

    public static String hesirajLozinku(String lozinka) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // File -> Exit:
        exitMeni.setOnAction(event -> {
            System.exit(0);
        });

        // Help -> About:
        aboutMeni.setOnAction(event -> {
            obavjestenjeProzor("About", "OOP projekat", "v1.0");
        });

        // Prijava tipka:
        prijaviSeTipka();

    }

    private void prijaviSeTipka() {

        prijavaTipka.setOnAction(event -> {
            String korisnickoIme = korisnikUnos.getText().strip();
            String lozinka = lozinkaUnos.getText();
            for (PristupniPodaci pp : PristupniPodaci.sviPristupniPodaci) {
                if (pp.getKorisnickoIme().equals(korisnickoIme)) {
                    if (pp.getLozinka().equals(hesirajLozinku(lozinka))) {
                        System.out.println("Postoji korisnik!");
                        for (Korisnik k : Korisnik.sviKorisnici) {
                            if (pp.getId() == k.getPristupniPodaciId()) {
                                if (k instanceof Ucenik) {
                                    Korisnik.prijavljeniKorisnik = k;
                                    System.out.println("Korisnik je ucenik!");
                                    LoginWindow.promjeniScenu("Ucenik.fxml", "Ucenik", 800, 600);
                                    return;
                                } else {
                                    Korisnik.prijavljeniKorisnik = k;
                                    System.out.println("Korisnik je profesor!");
                                    LoginWindow.promjeniScenu("Profesor.fxml", "Profesor", 800, 600);
                                    return;
                                }
                            }
                        }
                    }
                    obavjestenjeProzor("Greska!", null, "Pogrešna loznika!");
                    lozinkaUnos.clear();
                    return;
                }

            }
            obavjestenjeProzor("Greska!", null, "Korisnik ne postoji!");
            korisnikUnos.clear();
            lozinkaUnos.clear();
        });

    }

    private void obavjestenjeProzor(String naslov, String zaglavlje, String sadrzaj) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(zaglavlje);
        alert.setContentText(sadrzaj);
        alert.showAndWait();

    }
}

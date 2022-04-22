//
//	Kontroler za prozor ucenika.
//
package pmf.projekat.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import pmf.projekat.database.IzmjenaBaze;
import pmf.projekat.application.Izostanci;
import pmf.projekat.application.Korisnik;
import pmf.projekat.application.Ocjena;
import pmf.projekat.application.OcjenaPredmeta;
import pmf.projekat.application.PredmetUSkoli;
import pmf.projekat.application.PristupniPodaci;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
    @FXML
    private TextField profPredPolje;
    @FXML
    private TextField pitanjePrvo;
    @FXML
    private TextField pitanjeDrugo;
    @FXML
    private TextField pitanjeTrece;
    @FXML
    private TextField pitanjeCetvrto;
    @FXML
    private TextField novaLozinka;
    @FXML
    private TextField emailPolje;
    @FXML
    private TextArea tekstVelikoPolje;
    @FXML
    private TextArea tekstVelikoPolje2;
    @FXML
    private Button ocjeniProf;
    @FXML
    private Button odjaviSeTipka;
    @FXML
    private Button promjeniLozinku;
    @FXML
    private ChoiceBox<String> odaberiPredmet;
    @FXML
    private ChoiceBox<String> odaberiPredProfBox;
    @FXML
    private PasswordField emailLozinkaPolje;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Informacije o uceniku:
        ispisiInfo();

        // Ocjene:
        ispisiOcjene();

        // Izostanci:
        ispisiIzostanke();

        // Profesor:
        ocjeniProfesora();

    }

    private void ispisiInfo() {

        imePolje.setText(Korisnik.prijavljeniKorisnik.getIme());
        prezimePolje.setText(Korisnik.prijavljeniKorisnik.getPrezime());
        polPolje.setText(Korisnik.prijavljeniKorisnik.getPol());
        skolaPolje.setText(Korisnik.prijavljeniKorisnik.getSkola());
        razredPolje.setText(Korisnik.prijavljeniKorisnik.getRazred());

        odjaviSeTipka.setOnAction((event) -> {
            LoginWindow.promjeniScenu("Login.fxml", "Prijava", 800, 600);
        });
        promjeniLozinku.setOnAction((event) -> {
            String lozinka = novaLozinka.getText();
            if (novaLozinka.getText().isEmpty()) {
                obavjestenjeProzor("Niste unijeli lozinku!");
                return;
            }
            String korisnickoIme = Korisnik.prijavljeniKorisnik.getPristupniPodaci().getKorisnickoIme();
            IzmjenaBaze.posaljiPromjenuLozinke(korisnickoIme, lozinka);
            PristupniPodaci.promjeniLozinku(korisnickoIme, LoginKontroler.hesirajLozinku(lozinka));
            obavjestenjeProzor("Uspjesna promjena lozinke!");
            Email.posalji(emailPolje.getText(), emailLozinkaPolje.getText(), Korisnik.prijavljeniKorisnik.getPristupniPodaci().getEmail(), "Lozinka promjenjena", "Lozinka za korisnicko ime: " + korisnickoIme + " je uspjesno promjenjena!");
        });

    }

    private void ispisiOcjene() {

        Collections.sort(Korisnik.prijavljeniKorisnik.ocjeneKorisnika, new Sortiranje());
        Set<String> ocjene = new HashSet<>();
        for (Ocjena o : Korisnik.prijavljeniKorisnik.ocjeneKorisnika) {
            tekstVelikoPolje.appendText(o.getDatum() + ", " + o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
            ocjene.add(o.getPredmetUSkoli().getPredmet().getNaziv());
        }
        odaberiPredmet.getItems().addAll(ocjene);
        odaberiPredmet.getItems().add("Svi");
        odaberiPredmet.setOnAction((event) -> {
            String nazivOdabranogPredmeta = (String) odaberiPredmet.getValue();
            tekstVelikoPolje.clear();
            for (Ocjena o : Korisnik.prijavljeniKorisnik.ocjeneKorisnika) {
                if (o.getPredmetUSkoli().getPredmet().getNaziv().equals(nazivOdabranogPredmeta)) {
                    tekstVelikoPolje.appendText(o.getDatum() + ", " + o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
                } else if (nazivOdabranogPredmeta.equals("Svi")) {
                    tekstVelikoPolje.appendText(o.getDatum() + ", " + o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
                    ocjene.add(o.getPredmetUSkoli().getPredmet().getNaziv());
                }
            }
        });

    }

    private void ispisiIzostanke() {

        for (Izostanci i : Korisnik.prijavljeniKorisnik.sviIzostanci) {
            tekstVelikoPolje2.appendText(i.getDatum() + ", " + i.getPredmetUSkoli().getPredmet().getNaziv() + "\n");
        }

    }

    private void ocjeniProfesora() {
        Set<PredmetUSkoli> predmeti = new HashSet<>();
        for (Ocjena o : Ocjena.sveOcjene) {
            if (o.getUcenik().getId() == Korisnik.prijavljeniKorisnik.getId()) {
                predmeti.add(o.getPredmetUSkoli());
            }
        }
        for (Izostanci i : Izostanci.sviIzostanci) {
            if (i.getUcenik().getId() == Korisnik.prijavljeniKorisnik.getId()) {
                predmeti.add(i.getPredmetUSkoli());
            }
        }
        for (PredmetUSkoli p : predmeti) {
            System.out.println(p.getId());
        }
        for (PredmetUSkoli pus : predmeti) {
            odaberiPredProfBox.getItems().add(pus.getPredmet().getNaziv() + "," + pus.getPredmet().getRazred());
        }
        odaberiPredProfBox.setOnAction((event) -> {
            profPredPolje.clear();
            String naziv = odaberiPredProfBox.getValue().split(",")[0];
            int razred = Integer.parseInt(odaberiPredProfBox.getValue().split(",")[1]);
            for (PredmetUSkoli pus : predmeti) {
                if (pus.getPredmet().getNaziv().equals(naziv) && pus.getPredmet().getRazred() == razred) {
                    profPredPolje.appendText(pus.getProfesor().getPristupniPodaci().getKorisnickoIme());
                }
            }
        });
        ocjeniProf.setOnAction((event) -> {
            ArrayList<Integer> ocjene = new ArrayList<>();
            ocjene.add(Integer.parseInt(pitanjePrvo.getText()));
            ocjene.add(Integer.parseInt(pitanjeDrugo.getText()));
            ocjene.add(Integer.parseInt(pitanjeTrece.getText()));
            ocjene.add(Integer.parseInt(pitanjeCetvrto.getText()));

            int idUcenika = Korisnik.prijavljeniKorisnik.getId();
            int idPredmetaUSkoli = 0;
            String naziv = odaberiPredProfBox.getValue().split(",")[0];
            int razred = Integer.parseInt(odaberiPredProfBox.getValue().split(",")[1]);
            for (PredmetUSkoli pus : predmeti) {
                if (pus.getPredmet().getNaziv().equals(naziv) && pus.getPredmet().getRazred() == razred) {
                    idPredmetaUSkoli = pus.getId();
                }
            }
            for (int i = 1; i <= 4; i++) {
                int idOcjenePredmeta = IzmjenaBaze.posaljiOcjenuPredmeta(idUcenika, idPredmetaUSkoli, i, ocjene.get(i - 1));
                new OcjenaPredmeta(idOcjenePredmeta, idUcenika, idPredmetaUSkoli, i, ocjene.get(i - 1));
            }
            obavjestenjeProzor("Profesor je uspješno ocjenjen!");
        });

    }

    private void obavjestenjeProzor(String poruka) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ocjena!");
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();

    }

}
